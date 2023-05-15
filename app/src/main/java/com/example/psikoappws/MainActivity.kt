package com.example.psikoappws

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.example.psikoappws.presenter.graph.RootNavigationGraph
import com.example.psikoappws.presenter.viewModel.SplashViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var splashViewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //implement splash screen to our app
        installSplashScreen().setKeepOnScreenCondition{
            !splashViewModel.isLoading.value
        }

        setContent {
            val screen by splashViewModel.startDestination

            RootNavigationGraph(navController = rememberNavController(), startDestination = screen)
        }
    }
}



@Preview(showBackground = true)
@Composable
fun DefaultPreview() { }