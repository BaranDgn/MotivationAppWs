package com.example.psikoappws.presenter.viewModel

import androidx.compose.animation.core.tween
import androidx.compose.runtime.State
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





    init{
        getDailyQuote()
    }

    //Repo dan gelen loadQuotes() suspend bir corountine fonksiyonu oldugu icin
    //duz bir fonksiyon icersinde kullanamayız.
    //2 yol var bunu kullanabilmek için.
    //1- buradaki fonksiyonu suspend fun yapar geri kalan işlemleri view da yaparız.
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
}