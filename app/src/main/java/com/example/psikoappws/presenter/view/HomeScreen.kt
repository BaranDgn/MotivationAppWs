package com.example.psikoappws.presenter.view

import android.annotation.SuppressLint
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*


import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.graphics.Color

import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.psikoappws.R
import com.example.psikoappws.data.model.QuoteItem
import com.example.psikoappws.presenter.graph.FeatureScreens
import com.example.psikoappws.presenter.viewModel.HomeViewModel
import com.google.accompanist.pager.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.yield
import java.util.*


@OptIn(ExperimentalPagerApi::class, ExperimentalCoilApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    navController: NavHostController = rememberNavController(),
    homeViewModel : HomeViewModel = hiltViewModel()

) {

    val qList by remember{
        homeViewModel.quoteDailyList
    }
    val textOne = qList.shuffled().takeLast(1).takeLast(2).takeLast(3)
    

    val scrollState = rememberScrollState()

    val sliderPage = listOf(
        HomeSlider.One,
        HomeSlider.Two,
        HomeSlider.Three
    )

    val context = LocalContext.current
    val pages = ArrayList<QuoteItem>()
    val pagerState = rememberPagerState(
       // initialPage = 1
    )
    LaunchedEffect(Unit){
        while (true){
            yield()
            delay(20000)
            tween<Float>(600)
            tween<Float>(600)
            pagerState.animateScrollToPage(
                page = (pagerState.currentPage + 1) % (pagerState.pageCount)
            )
        }
    }

    Column(modifier = Modifier
        .fillMaxSize().height(900.dp)  //fillMaxSize()
        .background(Color.White).verticalScroll(scrollState)
        .padding(0.dp, 0.dp, 0.dp, 64.dp)

    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(380.dp)
                //.padding(16.dp)

        ) {

            HorizontalPager(
                state = pagerState,
                count = 3,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    //.padding(0.dp, 20.dp, 0.dp, 20.dp)//.height(380.dp)
            ) { page ->

                SliderPage(homeSlider = sliderPage[page])
                HorizontalPagerIndicator(
                         modifier = Modifier
                             .align(Alignment.BottomCenter)
                             .padding(0.dp, 0.dp, 0.dp, 24.dp),
                         pagerState = pagerState
                     )
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 20.dp, 0.dp, 0.dp),
                contentAlignment = Alignment.TopCenter

            ){
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = "PSIKO APP",
                    fontSize = MaterialTheme.typography.h5.fontSize,
                    textAlign = TextAlign.Center,
                    color = Color(0xffFBFCF8),
                    fontWeight = FontWeight.Normal
                )
            }

        }
        //Quotes
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "QUOTEs",
                fontSize = 24.sp,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(16.dp, 4.dp, 4.dp, 0.dp)

            )
            //CardView
            CardView(
                painter = rememberImagePainter(data = R.drawable.quoteone),
                contentDescription = "Quote",
                title = "Quotes will put a spotlight on you..",
                onClick = {  navController.navigate(FeatureScreens.QuoteScreen.route)}
            )
        }

        //Dairy
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "DIARY",
                fontSize = 24.sp,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(16.dp, 4.dp, 4.dp, 0.dp)

            )
            //CardView
            CardView(
                painter = rememberImagePainter(data = R.drawable.diaryhome),
                contentDescription = "Diary",
                title = "The more writing what you think, The more feeling better",
                onClick = {
                    navController.navigate(FeatureScreens.DiaryScreen.route)
                }
            )
        }

        //Chat
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "TALK IF YOU NEED",
                fontSize = 24.sp,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(16.dp, 4.dp, 4.dp, 0.dp)
            )
            //CardView
            CardView(
                painter = rememberImagePainter(data = R.drawable.talktorobot),
                contentDescription = "Chat",
                title = "There's a friend here to talk",
                onClick = { navController.navigate(FeatureScreens.ChatScreen.route) }
            )
        }

    }
}


@Composable
fun SliderPage(
    homeSlider: HomeSlider,
    homeViewModel: HomeViewModel = hiltViewModel()
) {

    val qList = remember{ homeViewModel.quoteDailyList }

    val textOne = qList.value.shuffled().takeLast(1).takeLast(2).takeLast(3)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .height(380.dp)
            .padding(0.dp, 0.dp, 0.dp, 16.dp),
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier,
            painter = painterResource(id = homeSlider.image),
            contentDescription = "Pager Image",
            contentScale = ContentScale.FillBounds
        )

        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = textOne.mapIndexed { index, dailyQuote ->
                dailyQuote.text?.replaceFirst(".$".toRegex(), "")
            }.toString(),
            fontSize = MaterialTheme.typography.h6.fontSize,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Light,
            color = Color(0xffFBFCF8)
        )

    }
}
