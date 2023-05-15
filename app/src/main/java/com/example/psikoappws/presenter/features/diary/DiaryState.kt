package com.example.psikoappws.presenter.features.diary

import com.example.psikoappws.data.model.Diary
import com.example.psikoappws.domain.util.DiaryOrder
import com.example.psikoappws.domain.util.OrderType

data class DiaryState(
    val diary: List<Diary> = emptyList(),
    val diaryOrder: DiaryOrder = DiaryOrder.Date(OrderType.Descending),
    val isOrderSectionVisible: Boolean = false
)
