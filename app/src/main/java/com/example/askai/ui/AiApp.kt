package com.example.askai.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.askai.feature.chat.ChatRoute
import com.example.askai.feature.welcome.WelcomeRoute
import com.example.askai.ui.theme.fontFamilys
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AiApp(
    navController: NavHostController = rememberNavController()
) {

    val scope = rememberCoroutineScope()

    val currentBackStackEntry by navController.currentBackStackEntryAsState()

    val currentRoute = currentBackStackEntry?.destination?.route

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            if (currentRoute == Screen.Chat.route) {
                TopAppBar(colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    titleContentColor = MaterialTheme.colorScheme.onSurface
                ), title = {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            "TalkAI",
                            fontFamily = fontFamilys,
                            modifier = Modifier.padding(bottom = 2.dp),
                            color = MaterialTheme.colorScheme.primary
                        )
                        Row(
                            modifier = Modifier.background(Color.White, RoundedCornerShape(30)),
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
                })
            }
        }
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
                        navController.navigate(Screen.Chat.route) {
                            popUpTo(Screen.Welcome.route) {
                                inclusive = true
                            }
                        }
                    }
                }
            }
            composable(Screen.Chat.route) {
                ChatRoute()
            }
        }

    }

}