package com.example.psikoappws.data.dataSource

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.psikoappws.data.model.Diary

@Database(
    entities = [Diary::class],
    version = 1
)
abstract class DiaryDatabase () : RoomDatabase(){
    abstract val diaryDao: DiaryDao

    companion object{
        const val DATABASE_NAME = "diary_db"
    }

}