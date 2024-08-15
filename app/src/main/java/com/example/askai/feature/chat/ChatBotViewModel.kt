package com.example.askai.feature.chat

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.askai.data.ChatMessage
import com.example.askai.data.ROLE
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.content
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatBotViewModel @Inject constructor() : ViewModel() {
    private val api_key = "AIzaSyBde7Vss4f3TnGhI6fv3HRUeaLbblysI4I"

    private val _uiState: MutableStateFlow<ChatUiState> = MutableStateFlow(ChatUiState())
    val uiState = _uiState.asStateFlow()

    private val genAi by lazy {
        GenerativeModel(
            // The Gemini 1.5 models are versatile and work with most use cases
            modelName = "gemini-1.5-flash",
            // Access your API key as a Build Configuration variable (see "Set up your API key" above)
            apiKey = api_key
        )
    }

    fun sendMsg(msgResponse: String) = viewModelScope.launch {
        try {
            _uiState.value.addMessage(ChatMessage(msgResponse, ROLE.USER.role))
            genAi.startChat().sendMessage(content(ROLE.USER.role) { text(msgResponse) })
                .text?.let {
                    Log.e("ERRORSS", "viewModel " + it.toString())
                    _uiState.value.addMessage(ChatMessage(it, ROLE.MODEL.role))
                }
        } catch (e: Exception) {
            Log.e("ERRORSS", e.localizedMessage.toString())
        }
    }
}