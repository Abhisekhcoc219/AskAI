package com.example.askai.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.askai.feature.chat.MainScreen
import com.example.askai.feature.welcome.WelcomeRoute
import kotlinx.coroutines.launch

@Composable
fun AiApp(
    navController: NavHostController = rememberNavController()
) {

    val scope = rememberCoroutineScope()

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
    ) { innerPadding ->

        NavHost(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background)
                .padding(innerPadding),
            navController = navController,
            startDestination = Screen.Welcome.route
        ) {
            composable(Screen.Welcome.route) {
                WelcomeRoute {
                    scope.launch {
                        navController.navigate(Screen.Chat.route)
                    }
                }
            }
            composable(Screen.Chat.route) {
                MainScreen()
            }
        }

    }

}