package com.example.psikoappws.domain.useCase

import com.example.psikoappws.data.model.Diary
import com.example.psikoappws.data.model.InvalidDiaryException
import com.example.psikoappws.domain.repository.DiaryRepository

class AddDiary(
    private val repo: DiaryRepository
) {

    suspend operator fun invoke(diary : Diary){
        if(diary.content.isBlank()){
            throw InvalidDiaryException("The Content cannot be empty.")
        }
        repo.insertDiary(diary)
    }

}