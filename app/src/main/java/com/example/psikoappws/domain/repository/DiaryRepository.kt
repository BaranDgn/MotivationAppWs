package com.example.psikoappws.domain.repository

import com.example.psikoappws.data.model.Diary
import kotlinx.coroutines.flow.Flow

interface DiaryRepository {

    fun getDiary(): Flow<List<Diary>>

    suspend fun getDiaryById(id: Int): Diary?

    suspend fun insertDiary(diary: Diary)

    suspend fun deleteDiary(diary: Diary)
}