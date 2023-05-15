package com.example.psikoappws.domain.repository

import android.content.Context
import com.example.psikoappws.data.model.StoreFavQuote
import com.example.psikoappws.domain.util.Resource
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.CoroutineScope

interface StoreQuoteRepository {

    val firestore : FirebaseFirestore
   // val storage : FirebaseStorage

    suspend fun uploadQuote(ctx : Context, text: String, author : String)

    suspend fun readQuote() : Resource<List<StoreFavQuote>>

    suspend fun deleteMyQuote(storeFavQuote : StoreFavQuote)


}