package com.example.askai.ui

sealed class Screen(val route: String) {
    data object Welcome : Screen("welcome")
    data object Chat : Screen("chat")

    companion object {
        fun fromRoute(route: String?): Screen {
            return when {
                route.isNullOrEmpty() -> throw IllegalArgumentException("Invalid route")
                route.startsWith(Welcome.route) -> Welcome
                route.startsWith(Chat.route) -> Chat
                else -> throw IllegalArgumentException("Invalid route")
            }
        }
    }

}