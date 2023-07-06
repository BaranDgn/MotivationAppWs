package com.example.psikoappws.data.service


import com.example.psikoappws.data.model.Quote
import com.example.psikoappws.domain.util.Resource
import retrofit2.http.GET
import retrofit2.http.POST

interface QuoteService {
    //https://type.fit/api/quotes
    @GET("quotes")
    suspend fun getQuote() : Quote


}