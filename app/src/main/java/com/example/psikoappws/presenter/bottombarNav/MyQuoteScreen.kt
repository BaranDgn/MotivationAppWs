package com.example.psikoappws.presenter.view

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.psikoappws.data.model.StoreFavQuote
import com.example.psikoappws.presenter.viewModel.MyQuoteViewModel

@Composable
fun MyQuoteScreen(
    navController: NavController
) {

    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)
        .padding(12.dp, 16.dp, 12.dp, 64.dp)
    ) {
        Text(
            text = "My Quotes",
            style = MaterialTheme.typography.h4

        )
        Divider(color = Color.Gray, thickness = 1.dp, modifier = Modifier.padding(8.dp,8.dp,8.dp,4.dp))
        Spacer(modifier = Modifier.height(16.dp))
        QuoteFavList()
    }

}


@SuppressLint("RememberReturnType")
@Composable
fun QuoteFavItem(
    favQuote: StoreFavQuote,
    viewModel: MyQuoteViewModel = hiltViewModel()
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 2.dp, 2.dp, 16.dp),
        shape = RoundedCornerShape(6.dp),
        elevation = 5.dp,
        contentColor = Color(0xFFFFC107)

    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .height(250.dp)
                .background(Color(0xffB7C8C2))
                .padding(16.dp)

            , verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(text = favQuote.text,
                style = TextStyle(
                    color = Color.DarkGray,
                    fontSize = 18.sp,
                fontWeight = FontWeight.Medium
                )
            )
            Spacer(modifier = Modifier.height(12.dp))

            Text(text = favQuote.author,
                style = TextStyle(color = Color.DarkGray, fontSize = 16.sp)
            )
            Spacer(modifier = Modifier.height(12.dp))

            val context = LocalContext.current
            Box(
                modifier = Modifier
                   .fillMaxWidth(),
                contentAlignment = Alignment.BottomEnd
            ){
                DisplayToggleButtonForFav(
                    onDelete = {
                        viewModel.deleteMyQuote(
                            favQuote
                        )
                    }
                )
            }
        }

    }
}


@Composable
fun QuoteList(
    favQuote: List<StoreFavQuote>
) {

    LazyColumn(contentPadding = PaddingValues(5.dp)){
        items(favQuote){favQuotes->
            QuoteFavItem(favQuote = favQuotes)
        }
    }
}

@Composable
fun QuoteFavList(
    viewModel: MyQuoteViewModel = hiltViewModel()
) {
    val qList by remember{
        viewModel.listOfFavoriteQuotes
    }


    QuoteList(favQuote = qList)


}