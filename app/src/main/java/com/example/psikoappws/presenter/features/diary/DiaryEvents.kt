package com.example.psikoappws.presenter.features.diary

import com.example.psikoappws.data.model.Diary
import com.example.psikoappws.domain.util.DiaryOrder

sealed class DiaryEvents{
    data class Order(val diaryOrder: DiaryOrder) : DiaryEvents()
    data class DeleteDiary(val diary: Diary) : DiaryEvents()
    object RestoreDiary: DiaryEvents()
    object ToggleOrderSection: DiaryEvents()
}
