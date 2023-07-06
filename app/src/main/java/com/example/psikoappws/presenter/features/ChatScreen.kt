package com.example.psikoappws.presenter.view


import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import com.example.psikoappws.R
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.psikoappws.presenter.features.ChatViewModel
import java.text.SimpleDateFormat
import java.time.format.TextStyle
import java.util.*
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.psikoappws.data.model.ChatResponse


@Composable
fun ChatScreen(
    navController: NavController,
    chatViewModel: ChatViewModel = viewModel()
) {
    val scrollState = rememberScrollState()
    //val chatMessages = chatViewModel.getChatMessages().value ?: emptyList()


   // Log.e("response", chatResponseList.toString())

    Scaffold(
        topBar = {
            TopAppBar(
                title = {Text(text = "Chat Robot", color = Color(0xff2F5061))},
                backgroundColor = Color.White,
                contentColor = Color(0xff2F5061),
                elevation = 10.dp,
                modifier = Modifier.height(80.dp)
            )
        }
    )  {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
            //verticalArrangement = Arrangement.Center,
        ){

            Box(Modifier.weight(1f)) {
                ChatSection(modifier = Modifier.fillMaxSize())
            }

            MessageSection(chatViewModel = chatViewModel)
        }
        it
    }
}


data class Message(
    var text : String?= null,
    var recieveId : String,
   // var time : Long = Calendar.getInstance().timeInMillis,
    var isOut : Boolean = false
)



//var messageOfChat = mutableStateOf<List<Message>>(listOf())
var messageOfChat = mutableStateListOf<String>()

@Composable
fun ChatSection(
    modifier: Modifier = Modifier,
    chatViewModel: ChatViewModel= viewModel()
) {
    val chatMessages = messageOfChat

    val chatResponseList = remember{
        chatViewModel.chatBotReply
    }
    chatMessages.add(chatResponseList.value)

   // Log.e("response : ", chatMessages[0].toString())
   // Log.e("response chatBotReply : ", chatResponseList.value.toString())


    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp),
        reverseLayout = false
    ) {
        itemsIndexed(chatMessages) { index, chat ->
            MessageItem(
                messageText = chat.toString(),
                isOut = index % 2 != 0
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

private val BotChatBubbleShape = RoundedCornerShape(0.dp, 8.dp,8.dp,8.dp)
private val AuthorChatBubbleShape = RoundedCornerShape(8.dp, 0.dp,8.dp,8.dp)

@Composable
fun MessageItem(
    messageText: String?,
  //  time: String,
    isOut : Boolean = false
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = if(isOut) Alignment.End else Alignment.Start
    ) {
        if(messageText != null){
            if (messageText != ""){
                Box(
                    modifier = Modifier
                        .background(
                            if (isOut) MaterialTheme.colors.primary else Color.Green,
                            shape = if (isOut) AuthorChatBubbleShape else BotChatBubbleShape
                        )
                        .padding(
                            vertical = 8.dp,
                            horizontal = 16.dp
                        )
                ){
                    Text(text = messageText,
                        fontSize = 20.sp,
                        color = Color.White)
                }
            }
        }
      //  Text(text = time, fontSize = 12.sp, modifier = Modifier.padding(start = 8.dp))
    }
}

val message = mutableStateOf("")
@Composable
fun MessageSection(
    //messageOfChat: MutableList<Message>
    chatViewModel: ChatViewModel
) {
    val viewModel: ChatViewModel = viewModel()
    val message = remember { mutableStateOf("") }


    Card(
        modifier = Modifier.fillMaxWidth(),
        backgroundColor = Color.White,
        elevation = 10.dp
    ) {

            OutlinedTextField(
                placeholder = { Text(text = "Message")},
                value = message.value,
                onValueChange = { message.value = it },
                shape = RoundedCornerShape(15.dp),
                trailingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.sendingicon),
                        contentDescription = "",
                        tint = MaterialTheme.colors.primary,
                        modifier = Modifier.clickable {
                            messageOfChat.add(
                                message.value
                            )
                            chatViewModel.chatWithBot(message.value)

                             message.value = ""
                            //viewModel.chatWithBot(message.value)
                            //viewModel.chatBotReply.value =  message.value
                            //chatViewModel.loadChatBot(message.value.toString())
                        }
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )
    }
}

/*
* @Composable
fun ChatScreen(
    navController: NavController,
    chatViewModel: ChatViewModel = viewModel()
) {
    val scrollState = rememberScrollState()
    val chatMessages = chatViewModel.getChatMessages().value ?: emptyList()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {Text(text = "Chat Robot", color = Color(0xff2F5061))},
                backgroundColor = Color.White,
                contentColor = Color(0xff2F5061),
                elevation = 10.dp,
                modifier = Modifier.height(80.dp)
            )
        }
    )  {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
            //verticalArrangement = Arrangement.Center,
        ){

            Box(Modifier.weight(1f)) {
                ChatSection(modifier = Modifier.fillMaxSize())
            }

            MessageSection(chatViewModel = chatViewModel)
        }
        it
    }

}


data class Message(
    var text : String?= null,
    var recieveId : String,
   // var time : Long = Calendar.getInstance().timeInMillis,
    var isOut : Boolean = false
)

var messagedummy = listOf(
    Message(
        text = "Great",
        recieveId = "psikoBot",
        isOut = false
    ),
    Message(
        text = "I'm good.",
        recieveId = "User",
        isOut = true
    )
)

//var messageOfChat = mutableStateOf<List<Message>>(listOf())
var messageOfChat = mutableStateListOf<Message>()

@Composable
fun ChatSection(modifier: Modifier = Modifier) {
    val chatMessages = messageOfChat

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp),
        reverseLayout = false
    ) {
        items(chatMessages) { chat ->
            MessageItem(
                messageText = chat.text,
                isOut = chat.isOut
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

private val BotChatBubbleShape = RoundedCornerShape(0.dp, 8.dp,8.dp,8.dp)
private val AuthorChatBubbleShape = RoundedCornerShape(8.dp, 0.dp,8.dp,8.dp)

@Composable
fun MessageItem(
    messageText: String?,
  //  time: String,
    isOut : Boolean
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = if(isOut) Alignment.End else Alignment.Start
    ) {
        if(messageText != null){
            if (messageText != ""){
                Box(
                    modifier = Modifier
                        .background(
                            if (isOut) MaterialTheme.colors.primary else Color.Green,
                            shape = if (isOut) AuthorChatBubbleShape else BotChatBubbleShape
                        )
                        .padding(
                            vertical = 8.dp,
                            horizontal = 16.dp
                        )
                ){
                    Text(text = messageText,

                        color = Color.White)

                }
            }
        }
      //  Text(text = time, fontSize = 12.sp, modifier = Modifier.padding(start = 8.dp))

    }

}

val message = mutableStateOf("")
@Composable
fun MessageSection(
    //messageOfChat: MutableList<Message>
    chatViewModel: ChatViewModel
) {
    val viewModel: ChatViewModel = viewModel()
    val message = remember { mutableStateOf("") }


    Card(
        modifier = Modifier.fillMaxWidth(),
        backgroundColor = Color.White,
        elevation = 10.dp
    ) {

            OutlinedTextField(
                placeholder = { Text(text = "Message")},
                value = message.value,
                onValueChange = { message.value = it },
                shape = RoundedCornerShape(15.dp),
                trailingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.sendingicon),
                        contentDescription = "",
                        tint = MaterialTheme.colors.primary,
                        modifier = Modifier.clickable {
                            chatViewModel.sendMessage(message.value)


                            message.value = ""
                            //viewModel.chatWithBot(message.value)
                            //viewModel.chatBotReply.value =  message.value
                            //chatViewModel.loadChatBot(message.value.toString())
                        }
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )
    }
}*/