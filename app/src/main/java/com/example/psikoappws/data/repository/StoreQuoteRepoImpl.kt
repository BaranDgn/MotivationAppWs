package com.example.psikoappws.data.repository

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import com.example.psikoappws.data.model.StoreFavQuote
import com.example.psikoappws.domain.repository.StoreQuoteRepository
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.example.psikoappws.domain.util.Resource
import com.example.psikoappws.domain.util.await
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.Query
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class StoreQuoteRepoImpl@Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : StoreQuoteRepository {

    override val firestore: FirebaseFirestore = Firebase.firestore

    var quoteArrayList: ArrayList<StoreFavQuote> = ArrayList()
    override suspend fun uploadQuote(ctx : Context, text: String, author: String) {
        firestore.collection("quote")
            .whereEqualTo("text", text)
            .whereEqualTo("author", author)
            .get()
            .addOnSuccessListener { querySnapshot ->
                if (querySnapshot.isEmpty) {
                    // Quote does not exist, proceed with upload
                    val postMap = hashMapOf<String, Any>()
                    postMap.put("text", text)
                    postMap.put("author", author)
                    postMap.put("date", Timestamp.now())

                    firestore.collection("quote")
                        .add(postMap)
                        .addOnSuccessListener {
                            // Quote uploaded successfully
                        }
                        .addOnFailureListener {
                            Toast.makeText(ctx, it.localizedMessage, Toast.LENGTH_LONG).show()
                        }
                } else {
                    // Quote already exists
                    Toast.makeText(ctx, "Quote already exists", Toast.LENGTH_LONG).show()
                }
            }
            .addOnFailureListener {
                Toast.makeText(ctx, it.localizedMessage, Toast.LENGTH_LONG).show()
            }
    }

    override suspend fun readQuote() : Resource<List<StoreFavQuote>>
    {

    return try {
        //quoteArrayList.clear()
        val querySnapShot = firestore.collection("quote")
            .orderBy("date", Query.Direction.DESCENDING)
            .get().await()
                val quoteList = mutableListOf<StoreFavQuote>()

        if (querySnapShot != null) {
            for (doc in querySnapShot) {
                val text = doc.getString("text") ?: ""
                val author = doc.getString("author") ?: ""
                val date = doc.getTimestamp("date")?.toDate()?.toString() ?: ""

                val favQuotes = StoreFavQuote(text, author, date)
                quoteList.add(favQuotes)
            }
        }

        Resource.Success(quoteList)
    } catch (e: Exception) {
        Resource.Error(e.localizedMessage ?: "Failed to fetch quotes")
    }

    }

    override suspend fun deleteMyQuote(storeFavQuote: StoreFavQuote){

        val quoteRef = Firebase.firestore.collection("quote")

        val favQuote = quoteRef
            .whereEqualTo("text", storeFavQuote.text)
            .whereEqualTo("author", storeFavQuote.author)
            .get().await()

        if (favQuote != null) {
            if (!favQuote.isEmpty) {
                for (document in favQuote.documents) {
                    try {
                        quoteRef.document(document.id).delete().await()
                        // Alternatively, you can use update with FieldValue.delete() to only delete the "text" field
                        // quoteRef.document(document.id).update("text", FieldValue.delete())

                    } catch (e: Exception) {
                        // Handle exception
                    }
                }
            }
        }
    }
}

