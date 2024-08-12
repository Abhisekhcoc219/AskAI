package com.example.askai.feature.welcome

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.askai.R
import com.example.askai.ui.theme.AskAITheme
import com.example.askai.ui.theme.fontFamilys

@Composable
fun WelcomeRoute(
    navigateToNextScreen: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            Text(
                text = "TalkAI",
                color = Color.White,
                fontFamily = fontFamilys,
                fontSize = 30.sp,
                modifier = Modifier.padding(20.dp, 40.dp, 0.dp, 0.dp)
            )
            Text(
                text = "Best personal \n AI assistant",
                color = Color.White,
                fontFamily = fontFamilys,
                fontSize = 30.sp,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(0.dp, 90.dp, 0.dp, 0.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.airobert),
                contentDescription = "Robet",
                modifier = Modifier
                    .size(300.dp)
                    .align(Alignment.CenterHorizontally)
                    .padding(0.dp, 80.dp, 0.dp, 0.dp)
                    .fillMaxWidth()
            )
        }

        Button(
            onClick = {
                navigateToNextScreen()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 50.dp, horizontal = 30.dp)
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(16.dp)
                )
                .align(Alignment.CenterHorizontally),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
                contentColor = Color.White
            )
        ) {
            Text(
                text = "Start a new chat",
                color = Color.Black,
                fontFamily = fontFamilys,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Medium
            )
        }
    }

}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PreviewWelcomeRoute() {
    AskAITheme {
        WelcomeRoute({})
    }
}