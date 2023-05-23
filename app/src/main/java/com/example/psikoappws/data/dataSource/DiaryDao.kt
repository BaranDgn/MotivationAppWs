package com.example.psikoappws.data.dataSource

import androidx.room.*
import com.example.psikoappws.data.model.Diary
import kotlinx.coroutines.flow.Flow

@Dao
interface DiaryDao {

    @Query("SELECT * FROM diary")
    fun getDiary() : Flow<List<Diary>>

    @Query("SELECT * FROM diary WHERE id = :id")
    suspend fun getDiaryById(id: Int) : Diary

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDiary(diary: Diary)

    @Delete
    suspend fun deleteDiary(diary: Diary)

}

