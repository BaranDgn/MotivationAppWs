package com.example.psikoappws.presenter.viewModel

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.psikoappws.data.model.StoreFavQuote
import com.example.psikoappws.domain.repository.StoreQuoteRepository
import com.example.psikoappws.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyQuoteViewModel@Inject constructor(
    private val read : StoreQuoteRepository
): ViewModel() {

    var listOfFavoriteQuotes = mutableStateOf<List<StoreFavQuote>>(listOf())

    var getFavJob : Job?=null


    //val context = LocalContext.current
    init{
        getFavQuotes()
    }

    fun deleteMyQuote(deleted: StoreFavQuote){
        viewModelScope.launch {
            read.deleteMyQuote(deleted)
        }
    }

    fun getFavQuotes(){
        getFavJob?.cancel()
        getFavJob = viewModelScope.launch {
            getFavJob?.cancel()

            val result = read.readQuote()

            when(result){
                is Resource.Success ->{
                    val quote = result.data!!.mapIndexed { index, storeF ->
                        StoreFavQuote(storeF.text,storeF.author,storeF.date)
                    }
                    listOfFavoriteQuotes.value += quote
                }
                is Resource.Error ->{

                }
                else -> Unit
            }

        }
    }

}