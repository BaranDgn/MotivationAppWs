package com.example.psikoappws.domain.repository

import com.example.psikoappws.data.model.QuoteRoom
import kotlinx.coroutines.flow.Flow

interface QuoteRoomRepository {

    fun getQuotes() : Flow<List<QuoteRoom>>

    suspend fun getQuoteById(id : Int) : QuoteRoom?

    suspend fun insertQuote(quote : QuoteRoom)

    suspend fun deleteQuote(quote: QuoteRoom)
}