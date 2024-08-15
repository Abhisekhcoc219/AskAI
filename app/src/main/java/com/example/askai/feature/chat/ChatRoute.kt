package com.example.askai.feature.chat

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.askai.R
import com.example.askai.data.ChatMessage
import com.example.askai.data.ROLE
import com.example.askai.ui.theme.fontFamilys
import com.example.askai.ui.theme.grey
import com.example.askai.ui.theme.lightPink


@Composable
fun UserPrompt(message: String) {
    Row(
        modifier = Modifier
            .padding(start = 100.dp, end = 20.dp, top = 10.dp)
            .background(grey, RoundedCornerShape(20)),
    ) {
        Text(
            text = message,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            fontFamily = fontFamilys,
            color = Color.White
        )
    }
}


@Composable
fun ModelResponse(text: String) {
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
fun ChatRoute(
    viewModel: ChatBotViewModel = hiltViewModel()
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    ChatScreen(
        messages = uiState.messages,
        onSendMessage = {
            viewModel.sendMsg(it)
        }
    )

}

@Composable
private fun ChatScreen(
    messages: List<ChatMessage>, onSendMessage: (String) -> Unit
) {

    var text by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
            .imePadding()
    ) {
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surfaceDim)
                .padding(horizontal = 4.dp)
        ) {


            items(messages) { it ->
                Log.e("ERRORSS", "inside " + it.toString())
                if (it.role == ROLE.USER.role) {
                    UserPrompt(message = it.message)
                } else {
                    ModelResponse(it.message)
                }
            }
        }

        Column(
            modifier = Modifier
                .background(color = MaterialTheme.colorScheme.surface)
        ) {

            HorizontalDivider(
                modifier = Modifier
                    .padding(top = 8.dp)
            )


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.Center
            ) {
                OutlinedTextField(
                    value = text,
                    onValueChange = { text = it },
                    placeholder = { Text("Chat with TalkAI", fontFamily = fontFamilys) },
                    shape = RoundedCornerShape(15.dp),
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 10.dp)
                        .background(color = MaterialTheme.colorScheme.surfaceContainer)
                        .align(Alignment.Bottom),
                )
                Box(
                    modifier = Modifier
                        .size(50.dp) // Adjust height as needed
                        .background(
                            color = MaterialTheme.colorScheme.surface,
                            shape = RoundedCornerShape(
                                7.dp
                            )
                        )
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_send), // Replace with your image
                        contentDescription = "Image",
                        modifier = Modifier
                            .align(Alignment.Center)
                            .size(35.dp)
                            .clickable {
                                if (text.isNotEmpty()) {
                                    onSendMessage(text)
                                    text = ""
                                }
                            },
                        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurface)
                    )
                }
            }
        }
    }
}


//@Preview(showSystemUi = true)
//@Composable
//fun preview(){
////    Log.e("ERRORSS", "outside " + messages.toString())
//    var text by remember { mutableStateOf("") }
//    Column(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding()
//            .imePadding()
//    ) {
//        LazyColumn(
//            modifier = Modifier
//                .weight(1f)
//                .fillMaxWidth()
//                .background(MaterialTheme.colorScheme.background)
//                .padding(horizontal = 4.dp)
//        ) {
//
//
//            items(20) { it ->
//                Log.e("ERRORSS", "inside " + it.toString())
//               Text(text = "kldsdslk")
//            }
//        }
//        Row(
//            modifier = Modifier.fillMaxWidth().padding(top = 30.dp),
//            verticalAlignment = Alignment.Bottom,
//            horizontalArrangement = Arrangement.Center
//        ) {
//            OutlinedTextField(
//                value = text,
//                onValueChange = { text = it },
//                placeholder = { Text("Chat with TalkAI", fontFamily = fontFamilys) },
//                shape = RoundedCornerShape(15.dp),
//                modifier = Modifier
//                    .width(300.dp)
//                    .padding(horizontal = 10.dp)
//                    .align(Alignment.Bottom),
//            )
//            Box(
//                modifier = Modifier
//                    .size(50.dp) // Adjust height as needed
//                    .background(
//                        white, shape = RoundedCornerShape(
//                            7.dp
//                        )
//                    )
//            ) {
//                Image(painter = painterResource(id = R.drawable.send_dark_theme), // Replace with your image
//                    contentDescription = "Image",
//                    modifier = Modifier
//                        .align(Alignment.Center)
//                        .size(35.dp)
//                        .clickable {
//                            if (text.isNotEmpty()) {
////                                onSendMessage(text)
//                                text = ""
//                            }
//                        })
//            }
//        }
//    }
//}
