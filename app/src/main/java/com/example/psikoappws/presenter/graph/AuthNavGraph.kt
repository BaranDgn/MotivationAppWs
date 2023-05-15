package com.example.psikoappws.presenter.graph

import androidx.compose.runtime.Composable
import androidx.navigation.*
import androidx.navigation.compose.composable
import com.example.psikoappws.Screens
import com.example.psikoappws.presenter.authenticaiton.LoginScreen
import com.example.psikoappws.presenter.authenticaiton.SignUpScreen
import com.example.psikoappws.presenter.opening.WelcomeScreen


fun NavGraphBuilder.authNavGraph(
    navController: NavHostController,
    startDestination : String

) {
    navigation(
        route = Graph.AUTHENTICATION,
        startDestination = startDestination//startDestination//AuthScreen.Welcome.route
    ){
        composable(route = AuthScreen.Login.route){
            LoginScreen(navController = navController,

                signUpClick = {
                    navController.navigate(AuthScreen.SignUp.route)
                }
            )
        }
        composable(route = AuthScreen.SignUp.route){
            SignUpScreen(navController = navController)
        }


        composable(route = AuthScreen.Welcome.route){
            WelcomeScreen(navController = navController)
        }

    }
}

sealed class AuthScreen(val route: String){
    object Login: AuthScreen(route = "LOGIN")
    object SignUp : AuthScreen(route = "SIGN_UP")
    object Welcome : AuthScreen(route = "welcome")
 //   object Splash : AuthScreen(route = "splash")
}

