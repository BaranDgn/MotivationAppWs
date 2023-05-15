package com.example.psikoappws.presenter.opening
/*
import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import kotlinx.coroutines.delay
import com.example.psikoappws.R

@Composable
fun SplashScreen(
    navController : NavHostController
) {
    val scale  = remember{
        Animatable(0f)
    }

    //to run just once
    LaunchedEffect(key1 = true){
        scale.animateTo(
            targetValue = 0.8f,
            animationSpec = tween(
                durationMillis = 500,
                easing = {
                    OvershootInterpolator(2f).getInterpolation(it)
                }
            )
        )
        delay(3000L)
        navController.popBackStack()
        navController.navigate("home_screen")
    }
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ){
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){


            Image(
                painter = painterResource(id = R.drawable.psikologo),
                contentDescription = "PsikoApp",
                modifier = Modifier.scale(scale.value)
            )
            Spacer(modifier = Modifier.height(80.dp))

            Text(text = "WELCOME!",
                style = TextStyle(
                    fontSize = 35.sp,
                    shadow = Shadow(
                        color= Color.Black , offset = Offset(1.0f, 2.0f)
                    )
                ),
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
            )
        }

    }

}

*/