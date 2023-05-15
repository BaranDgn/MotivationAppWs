package com.example.psikoappws.presenter.opening

import androidx.annotation.DrawableRes
import com.example.psikoappws.R

sealed class OnBoardingPage(
    @DrawableRes
    val image: Int,
    val title:String,
    val description: String
) {
    object First : OnBoardingPage(
        image = R.drawable.quote,
        title = "Quotes",
        description = "You can read plenty of Quotes of people who has an idea of feelings"
    )
    object Second : OnBoardingPage(
        image = R.drawable.diary,
        title = "Diary",
        description = "You can write what you think"
    )
    object Third : OnBoardingPage(
        image = R.drawable.chat,
        title = "Chat to Psiko",
        description = "You can talk to Psiko who will help you to feel better with it's suggestion"
    )

}
