package com.example.psikoappws.presenter.view

import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.graphics.ColorUtils
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.psikoappws.data.model.DailyQuote
import com.example.psikoappws.presenter.viewModel.HomeViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.delay
import kotlinx.coroutines.yield

@OptIn(ExperimentalPagerApi::class)
@Composable
fun DailyQuoteItem(
    modifier: Modifier,
    cornerRadius: Dp = 10.dp,
    cutCornerSize: Dp = 30.dp,
   //quote: DailyQuote,
    //dailyQuote: List<DailyQuote>
    //homeViewModel: HomeViewModel = hiltViewModel()
    textq:String,
    author: String

) {


    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ){
        Canvas(
            //matchParentSize: This will give our canvas the size
            //parent measured the constraints so after this box knows how much
            //width and height it occupies. Then this will retrun the size to the canvas

            modifier = Modifier.matchParentSize()
        ){
            val clipPath= Path().apply{
                lineTo(size.width - cutCornerSize.toPx(), 0f)
                lineTo(size.width, cutCornerSize.toPx())
                lineTo(size.width, size.height)
                lineTo(0f, size.height)
                close()
            }
            clipPath(clipPath){
                drawRoundRect(
                    color= Color(0xFF738580),
                   // color= Color(dailyQuote.color),
                    size = size,
                    cornerRadius = CornerRadius(cornerRadius.toPx())
                )
            }
            clipPath(clipPath){
                drawRoundRect(
                   color = Color(0xFF738580),
                  //  color = Color(dailyQuote.color),
                    size = size,
                    cornerRadius = CornerRadius(cornerRadius.toPx())
                )
                drawRoundRect(
                    color = Color(
                        ColorUtils.blendARGB(Color(0xFF738580).toArgb(), 0xE2DED0, 0.2f)
                    ),
                    topLeft = Offset(size.width - cutCornerSize.toPx(),-100f),
                    size = Size(cutCornerSize.toPx() + 100f, cutCornerSize.toPx() + 100f ),
                    cornerRadius = CornerRadius(cornerRadius.toPx())
                )
            }

        }

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(16.dp)
                .padding(end = 32.dp)

        ) {

            //val textQ = dailyQuote.shuffled().last()
            Text(
                text = textq,//textQ.toString(),//dailyQuote.shuffled()[1].text.toString(),
                style = MaterialTheme.typography.h6,
                color =  Color(0xFFE2DED0),
                maxLines = 4,
                //this will cut off when it gets too long
                overflow = TextOverflow.Ellipsis,
             //   modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = author,//dailyQuote.shuffled()[1].author.toString(),//dailyQuote.author,//"${dailyQuote.forEach { it.author.toString() }}",
                style = MaterialTheme.typography.body1,
                color = Color(0xFFE2DED0),
                maxLines = 10,
                //this will cut off when it gets too long
                overflow = TextOverflow.Ellipsis,
              //  modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}

