package com.example.psikoappws.presenter.features.viewModel

import androidx.compose.ui.focus.FocusState

sealed class AddEditDiaryEvent
{
    data class EnteredContent(val value: String) : AddEditDiaryEvent()
    data class ChangeContentFocus(val focusState: FocusState) : AddEditDiaryEvent()


    data class ChangeColor(val color : Int ) : AddEditDiaryEvent()

    object SaveDiary: AddEditDiaryEvent()


}