package com.example.psikoappws.data.repository

import com.example.psikoappws.data.model.Quote
import com.example.psikoappws.data.service.QuoteService
import com.example.psikoappws.domain.util.Resource
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class QuoteRepository@Inject constructor(
    private val api : QuoteService
) {
    suspend fun loadQuotes() : Resource<Quote>{
        val response = try{
            api.getQuote()
        }catch (e: Exception){
            return Resource.Error("Error")
        }
        return Resource.Success(response)
    }


}