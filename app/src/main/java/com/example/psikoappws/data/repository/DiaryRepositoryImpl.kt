package com.example.psikoappws.data.repository

import com.example.psikoappws.data.dataSource.DiaryDao
import com.example.psikoappws.data.model.Diary
import com.example.psikoappws.domain.repository.DiaryRepository
import kotlinx.coroutines.flow.Flow

class DiaryRepositoryImpl(
    private val dao: DiaryDao
): DiaryRepository{
    override fun getDiary(): Flow<List<Diary>> {
        return dao.getDiary()
    }

    override suspend fun getDiaryById(id: Int): Diary? {
        return dao.getDiaryById(id)
    }

    override suspend fun insertDiary(diary: Diary) {
         dao.insertDiary(diary)
    }

    override suspend fun deleteDiary(diary: Diary) {
         dao.deleteDiary(diary)
    }
}