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
        val postMap = hashMapOf<String, Any>()
        postMap.put("text", text)
        postMap.put("author", author)
        postMap.put("date", Timestamp.now())

        firestore.collection("quote").add(postMap).addOnSuccessListener {
            it

        }.addOnFailureListener {
            Toast.makeText(ctx, it.localizedMessage, Toast.LENGTH_LONG).show()
        }
    }

    override suspend fun readQuote() :Resource<List<StoreFavQuote>>
    {
        /*
        firestore.collection("quote").addSnapshotListener { value, error ->
            if(error != null){
              //  Toast.makeText(ctx, error.localizedMessage, Toast.LENGTH_LONG).show()
            }else{
                if(value != null){
                    if(!value.isEmpty){
                        val document = value!!.documents
                        for(doc in document){
                            val text = doc.get("text") as String
                            val author = doc.get("author") as String
                            val date = doc.getTimestamp("date")?.toDate()

                            val favQuotes = StoreFavQuote(text, author, date.toString())
                            quoteArrayList?.add(favQuotes)

                        }

                    }
                }
            }
        }
        return Resource.Success(quoteArrayList)*/

        firestore.collection("quote")
            .orderBy("date", Query.Direction.DESCENDING).get()
            .addOnSuccessListener { result ->
                if (result != null) {
                    if(!result.isEmpty){
                for (doc in result) {
                    val text = doc.get("text") as String
                    val author = doc.get("author") as String
                    val date = doc.getTimestamp("date")?.toDate()

                    val favQuotes = StoreFavQuote(text, author, date.toString())
                    quoteArrayList?.add(favQuotes)
                }
            }}
            }.addOnFailureListener { Log.d(TAG, "Error getting documnets") }
        return Resource.Success(quoteArrayList)

    }

    override suspend fun deleteMyQuote(storeFavQuote: StoreFavQuote){

        val quoteRef = Firebase.firestore.collection("quote")

        val favQuote = quoteRef
            .whereEqualTo("text", storeFavQuote.text)
            .whereEqualTo("author", storeFavQuote.author)
            .get().await()

        if(favQuote?.documents!!.isNotEmpty()){
            for(doc in favQuote){
                try{
                    quoteRef.document(doc.id).delete().await()
                    //quoteRef.document(doc.id).update(
                    //    mapOf("text" to FieldValue.delete())
                    //)


                }catch (e: Exception){
                    withContext(Dispatchers.Main){
                        //Toast.makeText()
                    }
                }
            }
        }


    }


}


/*  var storageRef = FirebaseStorage.getInstance().reference

        var desertRef = storageRef.child("quote/${storeFavQuote}")
        desertRef.delete().addOnSuccessListener {
            // File deleted successfully
        }.addOnFailureListener {
            // Uh-oh, an error occurred!
        }*/