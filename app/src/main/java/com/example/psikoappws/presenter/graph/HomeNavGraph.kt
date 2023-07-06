package com.example.psikoappws.presenter.graph

import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import com.example.psikoappws.BottomBarScreen
import com.example.psikoappws.presenter.view.*

@Composable
fun HomeNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = Graph.HOME,
        startDestination = BottomBarScreen.Home.route
    ) {

        composable(route = BottomBarScreen.Home.route) {
            HomeScreen(navController,
            //onClick = {navController.navigate(Graph.FEATURE)}
            )
        }
        composable(route = BottomBarScreen.MyQuote.route) {
           MyQuoteScreen(navController = navController)
        }
        composable(route = BottomBarScreen.MyDiary.route) {
            MyDiaryScreen(navController = navController)
        }
        composable(route = BottomBarScreen.Setting.route) {
            SettingScreen()
        }
        featureNavGraph(navController = navController)
        authNavGraph(navController, AuthScreen.Login.route)
    }
}

fun NavGraphBuilder.featureNavGraph(
    navController: NavHostController
) {
    navigation(
        route = Graph.FEATURE,
        startDestination = FeatureScreens.QuoteScreen.route
    ) {
        composable(route = FeatureScreens.QuoteScreen.route) {
           QuoteScreen(navController)
        }
        composable(route = FeatureScreens.DiaryScreen.route+
                "?dairyId={dairyId}&dairyColor={dairyColor}",
            arguments = listOf(
                navArgument(
                    name = "dairyId"
                ){
                    type = NavType.IntType
                    defaultValue = -1
                },
                navArgument(
                    name = "dairyColor"
                ){
                    type = NavType.IntType
                    defaultValue = -1
                },
            )
        ) {
            val color = it.arguments?.getInt("diaryColor")?:-1
           DiaryScreen(
               navController = navController,
               diaryColor = color

           )
        }


        composable(route = FeatureScreens.ChatScreen.route){
            ChatScreen(navController = navController)
        }
    }
}

sealed class FeatureScreens(val route: String){
    object DiaryScreen : FeatureScreens(route = "diary_screen")
    object QuoteScreen : FeatureScreens(route = "quote_screen")
    object ChatScreen : FeatureScreens(route ="chat_screen")
}
