package com.example.psikoappws.domain.useCase

import com.example.psikoappws.data.model.Diary
import com.example.psikoappws.domain.repository.DiaryRepository

class LoadDiaryById(
    private val repo: DiaryRepository
) {
    suspend operator fun invoke(id: Int): Diary?{
        return repo.getDiaryById(id)
    }

}