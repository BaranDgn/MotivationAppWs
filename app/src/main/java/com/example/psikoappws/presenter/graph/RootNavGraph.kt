package com.example.psikoappws.presenter.graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.psikoappws.presenter.bottombarNav.BottomBarNavigation
import com.example.psikoappws.presenter.opening.WelcomeScreen


@Composable
fun RootNavigationGraph(navController: NavHostController, startDestination: String) {
    NavHost(
        navController = navController ,
        route = Graph.ROOT,
        startDestination = Graph.AUTHENTICATION
    ){
        authNavGraph(navController = navController, startDestination)

        composable(route = Graph.HOME){
            BottomBarNavigation()
           // HomeScreen()
        }
       // composable(route = AuthScreen.Welcome.route){
        //    WelcomeScreen(navController = navController)
        //}

    }
}

object Graph{
    const val ROOT = "root_graph"
    const val AUTHENTICATION = "auth_graph"
    const val HOME = "home_graph"
    const val FEATURE = "feature_graph"
   // const val WELCOME = "welcome_graph"
}

