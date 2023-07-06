package com.example.psikoappws.presenter.view

import android.annotation.SuppressLint
import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.psikoappws.presenter.features.component.DiaryItem
import com.example.psikoappws.presenter.features.component.OrderSection
import com.example.psikoappws.presenter.features.diary.DiaryEvents
import com.example.psikoappws.presenter.features.viewModel.DiaryViewModel
import com.example.psikoappws.presenter.graph.FeatureScreens
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MyDiaryScreen(
    navController: NavController,
    viewModel : DiaryViewModel = hiltViewModel()
) {

    //a reference to state we can get from the viewmodel
    val state = viewModel.state.value
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()


    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate(FeatureScreens.DiaryScreen.route)
            },
                backgroundColor = Color.LightGray, modifier = Modifier.padding(0.dp,0.dp,0.dp,64.dp)
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Diary")
            }
        },
        scaffoldState = scaffoldState
    ){

        Column(
            modifier = Modifier
                .fillMaxSize().background(Color.White)
                .padding(16.dp)
                .padding(0.dp,0.dp,0.dp,64.dp)
        ){
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    text = "My Diaries",
                    style = MaterialTheme.typography.h4
                )
                IconButton(onClick = {
                    viewModel.onEvent(DiaryEvents.ToggleOrderSection)
                },) {
                    Icon(
                        imageVector = Icons.Default.List,
                        contentDescription ="Sort" )
                }
            }
            Divider(color = Color.Gray, thickness = 1.dp, modifier = Modifier.padding(8.dp,8.dp,8.dp,4.dp))

            //Animation
            AnimatedVisibility(
                visible = state.isOrderSectionVisible,
                enter = fadeIn() + slideInVertically(),
                exit = fadeOut() + slideOutVertically()
            ) {
                OrderSection(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    noteOrder = state.diaryOrder,
                    onOrderChange = {
                        viewModel.onEvent(DiaryEvents.Order(it))
                    })
            }

            Spacer(modifier = Modifier.height(16.dp))
            LazyColumn(modifier = Modifier.fillMaxSize()){
                items(state.diary){diary ->
                    DiaryItem(
                        diary = diary,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                navController.navigate(
                                    FeatureScreens.DiaryScreen.route +
                                            "?dairyId=${diary.id}&dairyColor=${diary.color}"
                                )

                            },

                        onDeleteClick = {
                            viewModel.onEvent(DiaryEvents.DeleteDiary(diary))
                            scope.launch {
                                val result = scaffoldState.snackbarHostState.showSnackbar(
                                    message = "Note deleted",
                                    actionLabel = "Undo"
                                )
                                //we clicked on the snackbar
                                if(result == SnackbarResult.ActionPerformed){
                                    viewModel.onEvent(DiaryEvents.RestoreDiary)

                                }
                            }
                        }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }

}