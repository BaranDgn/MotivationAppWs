package com.example.psikoappws.presenter.features.viewModel.quote

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.psikoappws.data.model.QuoteItem
import com.example.psikoappws.data.model.StoreFavQuote
import com.example.psikoappws.data.repository.QuoteRepository
import com.example.psikoappws.domain.repository.StoreQuoteRepository
import com.example.psikoappws.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuoteViewModel@Inject constructor(
    private val repo : QuoteRepository,
    private val storeQuote: StoreQuoteRepository
) :ViewModel(){

    //var quoteOfList = mutableListOf<List<QuoteItem>>(listOf())
    var quoteOfList = mutableStateOf<List<QuoteItem>>(listOf())
    val errorMessage = mutableStateOf("")
    val isLoading = mutableStateOf(false)


    var favText = mutableStateOf<String>("")
    var favAuthor = mutableStateOf<String>("")

    init{
        loadQuotes()
    }

    fun storeFavQuote(ctx: Context, text: String?, author: String?){
        viewModelScope.launch {
            storeQuote.uploadQuote(ctx, favText.value.toString(), favAuthor.value.toString())
        }
    }
    fun deleteMyQuote(deleted: StoreFavQuote){
        viewModelScope.launch {
            storeQuote.deleteMyQuote(deleted)
        }
    }


    fun loadQuotes(){
        viewModelScope.launch{
            isLoading.value = true

            val result = repo.loadQuotes()

            when(result){
                is Resource.Success ->{
                    val quoteItem = result.data!!.mapIndexed { index, quoteItem ->
                        QuoteItem(
                            quoteItem.text,
                            quoteItem.author,

                        )
                    }
                    isLoading.value = false
                    errorMessage.value = ""
                    quoteOfList.value += quoteItem

                }
                is Resource.Error ->{
                    errorMessage.value = result.message!!
                    isLoading.value = false
                }
                else -> Unit
            }
        }
    }
}