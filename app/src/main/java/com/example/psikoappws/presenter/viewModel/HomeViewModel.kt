package com.example.psikoappws.presenter.viewModel

import androidx.compose.animation.core.tween
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.psikoappws.data.model.DailyQuote
import com.example.psikoappws.data.model.QuoteItem
import com.example.psikoappws.data.repository.QuoteRepository
import com.example.psikoappws.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repo : QuoteRepository
) : ViewModel(){

    var quoteDailyList = mutableStateOf<List<DailyQuote>>(listOf())
    var errorMessage = mutableStateOf("")
    var isLoading = mutableStateOf(false)

    //val color = mutableStateOf<List<DailyQuote>>(listOf())


    init{
        getDailyQuote()
    }

    //Repo dan gelen loadQuotes() suspend bir corountine fonksiyonu oldugu icin
    //duz bir fonksiyon icersinde kullanamayız.
    //2 yol var bunu kullanabilmek için.
    //1- buradaki fonksiyonu suspend fun yapar geri kalan işlemleri view da yaparaız.
    //2- viewmodelScope.launch(){} içinde çağırabiliriz.
    //Her 2 yolunda belli bedelleri var.



    fun getDailyQuote(){
        viewModelScope.launch {
            val result = repo.loadQuotes()

            when(result){
                is Resource.Success ->{
                    val dailyItem = result.data!!.mapIndexed { index, item ->
                        DailyQuote(
                            item.text,
                        )

                    }

                    isLoading.value = false
                    errorMessage.value =""
                    quoteDailyList.value += dailyItem
                }
                is Resource.Error ->{
                    errorMessage.value = result.message!!
                    isLoading.value = false
                }
                else -> Unit
            }

        }
    }


    /*
    fun loadDAilyQuote(){
        viewModelScope.launch {
            isLoading.value = true

            val result = repo.loadQuotes()

            //mapIndexed --> kullanılacak seyde bir liste varsa, ve bu liste tek tek elemana çevirmek için kullanılır. foreach{} benzer.
            when(result){
                is Resource.Success -> {
                   val quoteItems = result.data!!.mapIndexed { index, quoteItem ->
                       DailyQuote(
                            quoteItem.text,
                           quoteItem.author
                       )
                   } as List<DailyQuote>


                    errorMessage.value = ""
                    isLoading.value = false
                    quoteDailyList.value += quoteItems

                }
                is Resource.Error ->{
                    errorMessage.value = result.message!!
                    isLoading.value = false
                }
                else -> Unit
            }
        }
    }*/
}