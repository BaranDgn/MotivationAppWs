package com.example.psikoappws.presenter.features.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.psikoappws.data.model.Diary
import com.example.psikoappws.data.model.InvalidDiaryException
import com.example.psikoappws.domain.useCase.DiaryUseCases
import com.example.psikoappws.presenter.features.diary.DiaryTextFieldState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AddEditDiaryViewModel@Inject constructor(
    private val diaryUseCases: DiaryUseCases,
    saveStateHandle: SavedStateHandle
) : ViewModel(){



    private val _diaryContent = mutableStateOf(DiaryTextFieldState(
        hint = "Write what you feel..."
    ))
    val diaryContent : State<DiaryTextFieldState> = _diaryContent

    private val _diaryColor = mutableStateOf<Int>(Diary.diaryColors.random().toArgb())
    val diaryColor : State<Int> = _diaryColor

    private val _eventFlow= MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var currentDiaryId: Int ?=null

    init {
        saveStateHandle.get<Int>("dairyId")?.let {dairyId ->
        if(dairyId != -1){
            viewModelScope.launch {
                diaryUseCases.loadDiaryById(dairyId)?.also {dairy ->
                    currentDiaryId = dairy.id
                    _diaryContent.value = diaryContent.value.copy(
                        text = dairy.content,
                        isHintVisible = false

                    )
                    _diaryColor.value = dairy.color
                }
            }
        }

        }
    }

    fun onEvent(event: AddEditDiaryEvent){
        when(event){
            is AddEditDiaryEvent.EnteredContent ->{
                _diaryContent.value = diaryContent.value.copy(
                    text = event.value
                )
            }
            is AddEditDiaryEvent.ChangeContentFocus ->{
                _diaryContent.value = diaryContent.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            diaryContent.value.text.isBlank()
                )
            }
            is AddEditDiaryEvent.ChangeColor ->{
                _diaryColor.value = event.color
            }
            is AddEditDiaryEvent.SaveDiary ->{
               viewModelScope.launch {
                   try{
                       diaryUseCases.addDiary(
                           Diary(
                               content = diaryContent.value.text,
                               timeStamp = System.currentTimeMillis(),
                               color = diaryColor.value,
                               id = currentDiaryId
                           )
                       )
                       _eventFlow.emit(UiEvent.SaveDiary)

                   }catch (e: InvalidDiaryException){
                       _eventFlow.emit(
                           UiEvent.ShowSnackBar(
                               message = e.message ?: "Couldn't save the Diary"
                           )
                       )

                   }
               }
            }


        }

    }

    sealed class UiEvent{
        data class ShowSnackBar(val message: String): UiEvent()
        object SaveDiary: UiEvent()
    }




}