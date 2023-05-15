package com.example.psikoappws.domain.useCase

import com.example.psikoappws.data.model.Diary
import com.example.psikoappws.domain.repository.DiaryRepository
import com.example.psikoappws.domain.util.DiaryOrder
import com.example.psikoappws.domain.util.OrderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetDiary(
    private val repo : DiaryRepository
) {

    operator fun invoke(
        diaryOrder: DiaryOrder = DiaryOrder.Date(OrderType.Descending)
    ): Flow<List<Diary>>{
        return repo.getDiary().map{ diary->
            when(diaryOrder.orderType){
                is OrderType.Ascending ->{
                    when(diaryOrder){
                        is DiaryOrder.Title ->{diary.sortedBy { it.content.lowercase() }}
                        is DiaryOrder.Date -> {diary.sortedBy { it.timeStamp } }
                    }
                }
                is OrderType.Descending ->{
                    when(diaryOrder){
                        is DiaryOrder.Title ->{diary.sortedByDescending { it.content.lowercase() }}
                        is DiaryOrder.Date -> {diary.sortedByDescending { it.timeStamp } }
                    }
                }
            }

        }

    }

}