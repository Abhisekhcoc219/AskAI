package com.example.askai.feature.chat

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.askai.R
import com.example.askai.data.ChatMessage
import com.example.askai.data.ROLE
import com.example.askai.ui.theme.fontFamilys
import com.example.askai.ui.theme.grey
import com.example.askai.ui.theme.lightPink

@OptIn(ExperimentalMaterial3Api::class)
//@Preview(showBackground = true)
@Composable
fun HomeScreen(
    messages: List<ChatMessage>,
    onSendMessage: (String) -> Unit = {},
) {
    Scaffold(
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = Color.Black,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    )
                    {
                        Text(
                            "TalkAI",
                            fontFamily = fontFamilys,
                            modifier = Modifier.padding(top = 4.dp, bottom = 5.dp),
                            color = Color.White
                        )
                        Row(
                            modifier = Modifier
                                .padding(bottom = 3.dp)
                                .background(Color.White, RoundedCornerShape(30)),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .padding(start = 5.dp)
                                    .size(8.dp)
                                    .background(Color.Green, CircleShape)
                            )
                            Spacer(
                                modifier = Modifier
                                    .width(4.dp)
                                    .padding(start = 4.dp)
                            )
                            Text(
                                text = "Online",
                                color = Color.Green,
                                fontSize = 16.sp,
                                modifier = Modifier.padding(end = 4.dp),
                                fontFamily = fontFamilys
                            )
                        }
                    }
                }
            )
        },
    ) { innerPadding ->
        ChatScreen(
            paddingValue = innerPadding,
            color = Color.White,
            drawable = R.drawable.send_dark_theme,
            messages = messages,
            onSendMessage = onSendMessage
        )
    }
}


@Composable
fun ChatListAiResponse(text: String) {
    Row(
        modifier = Modifier
            .padding(horizontal = 20.dp, vertical = 10.dp)
            .fillMaxWidth()
            .background(lightPink, RoundedCornerShape(10)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(13.dp),
            fontFamily = fontFamilys,
            color = Color.Black
        )
    }
}

@Composable
fun ChatListUser(message: String) {
    Row(
        modifier = Modifier
            .padding(start = 100.dp, end = 20.dp, top = 10.dp)
            .background(grey, RoundedCornerShape(20)),
    ) {
        Text(
            text = message, modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp), fontFamily = fontFamilys, color = Color.White
        )
    }
}

@Composable
fun MainScreen(
    viewModel: ChatBotViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    HomeScreen(messages = uiState.messages, onSendMessage = {
        viewModel.sendMsg(it)
    })
}


@Composable
fun ChatScreen(
    messages: List<ChatMessage>,
    paddingValue: PaddingValues,
    color: Color,
    drawable: Int,
    onSendMessage: (String) -> Unit
) {

    Log.e("ERRORSS", "outside " + messages.toString())
    var text by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = paddingValue.calculateTopPadding())
            .imePadding()
    )
    {
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .background(Color.Black)
                .padding(horizontal = 4.dp)
        ) {


            items(messages) { it ->
                Log.e("ERRORSS", "inside " + it.toString())
                if (it.role == ROLE.USER.role) {
                    ChatListUser(message = it.message)
                } else {
                    ChatListAiResponse(it.message)
                }
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 30.dp)
        ) {
            OutlinedTextField(
                value = text,
                onValueChange = { text = it },
                placeholder = { Text("Chat with TalkAI", fontFamily = fontFamilys) },
                shape = RoundedCornerShape(15.dp),
                modifier = Modifier
                    .width(300.dp)
                    .padding(horizontal = 10.dp)
                    .align(Alignment.Bottom),
                // Adjust the radius as needed
            )
            Box(
                modifier = Modifier
                    .size(50.dp) // Adjust height as needed
                    .background(
                        color, shape = RoundedCornerShape(
                            7.dp
                        )
                    )
            ) {
                Image(
                    painter = painterResource(id = drawable), // Replace with your image
                    contentDescription = "Image",
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(35.dp)
                        .clickable {
                            if (text.isNotEmpty()) {
                                onSendMessage(text)
                                text = ""
                            }
                        }
                )
            }
        }
    }
}
