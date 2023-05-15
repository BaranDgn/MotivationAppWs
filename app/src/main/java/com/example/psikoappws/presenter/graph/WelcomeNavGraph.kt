package com.example.psikoappws.presenter.graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.psikoappws.BottomBarScreen
import com.example.psikoappws.presenter.authenticaiton.LoginScreen
import com.example.psikoappws.presenter.authenticaiton.SignUpScreen
import com.example.psikoappws.presenter.opening.WelcomeScreen
import com.example.psikoappws.presenter.view.HomeScreen
import com.example.psikoappws.presenter.view.MyDiaryScreen
import com.example.psikoappws.presenter.view.MyQuoteScreen
import com.example.psikoappws.presenter.view.SettingScreen

/*
fun NavGraphBuilder.welcomeNavGraph(
    navController: NavHostController,
    // startDestination : String

) {
    navigation(
        route = Graph.WELCOME,
        startDestination = WelcomeScreen.Welcome.route//startDestination//AuthScreen.Welcome.route
    ){

        composable(route = WelcomeScreen.Welcome.route){
            WelcomeScreen(navController = navController)
        }


        //  composable(route = AuthScreen.Welcome.route){
        //     WelcomeScreen(navController = navController)
        // }

    }
}

sealed class WelcomeScreen(val route: String){
    //object Login: AuthScreen(route = "LOGIN")
    //object SignUp : AuthScreen(route = "SIGN_UP")
       object Welcome : AuthScreen(route = "welcome")
    //object Splash : AuthScreen(route = "splash")
}
*/
