package com.example.psikoappws


sealed class Screens(val route: String){
    //Main Screen
    object HomeScreen : Screens("home_screen")

    //Feature Screens
    object QuoteScreen : Screens("quote_screen")
    object DairyScreen : Screens("dairy_screen")
    object ChatScreen : Screens("chat_screen")

    // BottomBar Screens
    object MyListOfQuotes: Screens("list_quote_screen")
    object MyListOfDairy: Screens("list_dairy_screen")

    //Login-Signup
    object LoginScreen : Screens("login_screen")
    object SignupScreen: Screens("signup_screen")

    //Splash-onBoarding
    object Welcome: Screens("welcome_screen")
    object SplashScreen : Screens("splash_screen")

}
