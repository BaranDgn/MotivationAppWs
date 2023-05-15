package com.example.psikoappws.presenter.view

import androidx.annotation.DrawableRes
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.psikoappws.R
import com.example.psikoappws.data.model.DailyQuote
import com.example.psikoappws.presenter.view.HomeSlider.One.dailyQuote
import com.example.psikoappws.presenter.viewModel.HomeViewModel

sealed class HomeSlider(
    @DrawableRes
    val image: Int,
    val dailyQuote: String,

){
    object One: HomeSlider(
        image = R.drawable.quoteeeleven,
        dailyQuote = "dailyQuote",
    )
    object Two: HomeSlider(
        image = R.drawable.quotetwenty,
        dailyQuote = "dailyQuote",
    )
    object Three: HomeSlider(
        image = R.drawable.quotesix,
        dailyQuote = "dailyQuote",
    )

}
