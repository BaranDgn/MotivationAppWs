package com.example.psikoappws.presenter.features.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.psikoappws.data.model.Diary
import com.example.psikoappws.domain.useCase.DiaryUseCases
import com.example.psikoappws.domain.util.DiaryOrder
import com.example.psikoappws.domain.util.OrderType
import com.example.psikoappws.presenter.features.diary.DiaryEvents
import com.example.psikoappws.presenter.features.diary.DiaryState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DiaryViewModel@Inject constructor(
    private val diaryUseCases: DiaryUseCases
) : ViewModel(){

    //contains the state, UI will observe
    private val _state = mutableStateOf<DiaryState>(DiaryState())
    val state : State<DiaryState> = _state

    private var recentlyDeletedDiary: Diary? = null

    private var getDiaryJob: Job?= null

    init {
        getDiary(DiaryOrder.Date(OrderType.Descending))
    }

    fun onEvent(event: DiaryEvents){
        when(event){
            is DiaryEvents.Order ->{
                if(state.value.diaryOrder::class == event.diaryOrder::class &&
                        state.value.diaryOrder.orderType == event.diaryOrder.orderType){
                    return
                }
                getDiary(event.diaryOrder)

            }
            is DiaryEvents.DeleteDiary ->{
                viewModelScope.launch {
                    diaryUseCases.deleteDiary(event.diary)
                    recentlyDeletedDiary = event.diary
                }
            }
            is DiaryEvents.RestoreDiary ->{
                viewModelScope.launch {
                    diaryUseCases.addDiary(recentlyDeletedDiary ?: return@launch)
                    recentlyDeletedDiary = null //not to add recently deleted diary more than one
                }
            }
            is DiaryEvents.ToggleOrderSection ->{
                _state.value = state.value.copy(
                    isOrderSectionVisible = !state.value.isOrderSectionVisible
                )
            }
        }
    }

    private fun getDiary(diaryOrder: DiaryOrder){
        getDiaryJob?.cancel()
        getDiaryJob = diaryUseCases.getDiary(diaryOrder)
            .onEach {diary ->
                _state.value = state.value.copy(
                    diary = diary,
                    diaryOrder = diaryOrder
                )
            }
            .launchIn(viewModelScope)
    }
}