package com.example.psikoappws.data.dataSource

import androidx.room.*
import com.example.psikoappws.data.model.Diary
import com.example.psikoappws.data.model.QuoteRoom
import kotlinx.coroutines.flow.Flow

@Dao
interface QuoteDao {

    @Query("SELECT * FROM quote")
    fun insertQuote(): Flow<List<QuoteRoom>>

    @Query("SELECT * FROM quote WHERE id = :id")
    suspend fun getQuoteById(id: Int) : QuoteRoom

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuote(quote: QuoteRoom)

    @Delete
    suspend fun deleteQuote(quote: QuoteRoom)


}