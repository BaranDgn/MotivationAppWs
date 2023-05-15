package com.example.psikoappws.presenter.view

import android.annotation.SuppressLint
import android.window.OnBackInvokedCallback
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.psikoappws.BottomBarScreen
import com.example.psikoappws.data.model.Diary
import com.example.psikoappws.presenter.features.component.DiaryItem
import com.example.psikoappws.presenter.features.component.OrderSection
import com.example.psikoappws.presenter.features.diary.DiaryEvents
import com.example.psikoappws.presenter.features.diary.TransparentHintTextField
import com.example.psikoappws.presenter.features.viewModel.AddEditDiaryEvent
import com.example.psikoappws.presenter.features.viewModel.AddEditDiaryViewModel
import com.example.psikoappws.presenter.features.viewModel.DiaryViewModel
import com.example.psikoappws.presenter.graph.FeatureScreens
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DiaryScreen(
    navController: NavController,
    viewModel : AddEditDiaryViewModel = hiltViewModel(),
    diaryColor : Int,
    callback: () -> Unit = {}

){
   /* BackHandler(enabled = true){
        if()
        viewModel.onEvent(AddEditDiaryEvent.SaveDiary)
    }*/

    val contentState = viewModel.diaryContent.value

    val scaffoldState = rememberScaffoldState()
    val scrollState = rememberScrollState()

    val dairyBackgroundAnimatable = remember{
        Animatable(
            Color(if(diaryColor != -1) diaryColor else viewModel.diaryColor.value)
        )

    }

    //val scope = rememberCoroutineScope()

    //For showing snackbar
    LaunchedEffect(key1 = true){
        viewModel.eventFlow.collectLatest { event ->
            when(event){
                is AddEditDiaryViewModel.UiEvent.ShowSnackBar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }
                is AddEditDiaryViewModel.UiEvent.SaveDiary -> {
                    navController.navigateUp()
                }
                else -> {}
            }
        }
    }

    Scaffold(
       /* floatingActionButton = {
            FloatingActionButton(onClick = {
                viewModel.onEvent(AddEditDiaryEvent.SaveDiary) },
                backgroundColor = Color.Gray
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Save Diary")
            }

        },*/
        scaffoldState = scaffoldState
    ) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
            .verticalScroll(scrollState)
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp).padding(4.dp,0.dp,0.dp,0.dp)
        ){

            IconButton(
                onClick = {
                    navController.popBackStack()
                },
                // navController.na
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription ="Back Note"
                )
            }

            Text(
                text = "DONE",
                modifier = Modifier.padding(horizontal = 8.dp).clickable {
                    viewModel.onEvent(AddEditDiaryEvent.SaveDiary)
                },
                style = TextStyle(color = Color(0xff54627B), fontSize = 20.sp)

            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(dairyBackgroundAnimatable.value)
                .padding(16.dp)
        ) {

            TransparentHintTextField(
                text = contentState.text,
                hint = contentState.hint,
                onValueChange = {
                    viewModel.onEvent(AddEditDiaryEvent.EnteredContent(it))
                },
                onFocusChange ={
                    viewModel.onEvent(AddEditDiaryEvent.ChangeContentFocus(it))
                },
                isHintVisible = contentState.isHintVisible,
                textStyle = MaterialTheme.typography.body1,
                modifier = Modifier
                    .fillMaxHeight()
                    .background(Color(0xffFBFCF8)).verticalScroll(scrollState)
            )


        }
    }


    }




}
@Composable
public fun BackHandler(
    enabled: Boolean = true,
    onBack: () -> Unit){

}