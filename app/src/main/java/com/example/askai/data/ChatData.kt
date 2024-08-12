package com.example.askai.data

data class ChatMessage(val message: String, val role: String)


enum class ROLE(val role: String) {
    USER("user"), MODEL("model")
}
