package com.example.psikoappws.domain.useCase

import com.example.psikoappws.data.model.Diary
import com.example.psikoappws.domain.repository.DiaryRepository

class DeleteDiary(
    private val repo : DiaryRepository
){
    suspend operator fun invoke(diary: Diary){
        repo.deleteDiary(diary)
    }


}