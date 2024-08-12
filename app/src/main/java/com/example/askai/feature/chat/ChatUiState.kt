package com.example.askai.feature.chat

import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateListOf
import com.example.askai.data.ChatMessage

@Stable
class ChatUiState {
    private val _messages: MutableList<ChatMessage> = mutableStateListOf()
    val messages: List<ChatMessage> = _messages


    fun addMessage(message: ChatMessage) {
        _messages.add(message)
    }

}