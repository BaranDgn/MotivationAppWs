package com.example.psikoappws.presenter.view

import android.widget.Space
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.psikoappws.R
import com.example.psikoappws.presenter.authenticaiton.AuthViewModel
import com.example.psikoappws.presenter.graph.AuthScreen
import com.example.psikoappws.presenter.graph.Graph

@Composable
fun SettingScreen(
    navController: NavController,
    viewModel: AuthViewModel = hiltViewModel()
) {
    val ctx = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = {Text(text = "Profile", color = Color(0xff2F5061))},
                actions={
                    IconButton(onClick = {
                        viewModel.logOut()
                       navController.navigate(Graph.AUTHENTICATION)
                        Toast.makeText(ctx, "It's logged out", Toast.LENGTH_SHORT).show()
                    })
                    {
                        Icon(ImageVector.vectorResource(id = R.drawable.log_out_icon), null)
                    }

                },
                backgroundColor = Color.White,
                contentColor = Color(0xff2F5061),
                elevation = 10.dp,
                modifier = Modifier.height(80.dp)
            )
        }
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {

            Row(//horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(32.dp)) {
                Image(
                    painter = painterResource(id = R.drawable.psikologo),
                    contentDescription = "Logo",
                    modifier = Modifier
                        .width(120.dp)
                        .height(120.dp)
                        .shadow(5.dp, CircleShape)
                )

                Spacer(modifier = Modifier.width(16.dp))

                Text(
                    text = "Name",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }
           // Divider(color = Color.Gray, thickness = 1.dp, modifier = Modifier.padding(16.dp,0.dp,16.dp,2.dp))


            Spacer(modifier = Modifier.height(32.dp))

            //Activies of User

        }
        it
    }

}