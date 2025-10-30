package com.example.danil

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.danil.ui.theme.DanilTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DanilTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                ) {

                    MainLayout()


                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun MainLayout() {
    Image(
        painter = painterResource(R.drawable.background),
        contentDescription = null,
        contentScale = ContentScale.FillBounds,
        modifier = Modifier.fillMaxSize()
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Transparent),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        var userInput by remember { mutableStateOf("") }
        var gameFinished by remember { mutableStateOf(false) }
        var target by remember { mutableStateOf((0..100).random()) };
        var message by remember { mutableStateOf(R.string.try_to_guess) }
        var btnText by remember { mutableStateOf("Press!") }

        Text(
            text = stringResource(message),
            fontSize = 23.sp,
            color = Color.White
        )

        Spacer(modifier = Modifier.size(25.dp))

        TextField(
            value = userInput,
            onValueChange = { newValue -> userInput = newValue },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.size(25.dp))


        Button(
            onClick = {
                if (gameFinished) {
                    target = (0..100).random()
                    gameFinished = false
                    userInput = ""
                    message = R.string.try_to_guess
                } else {
                    val number = userInput.toIntOrNull()
                    when {
                        number == null -> {
                            message = R.string.error
                        }

                        number > target -> {
                            message = R.string.ahead
                        }

                        number < target -> {
                            message = R.string.behind
                        }

                        number == target -> {
                            message = R.string.hit
                            gameFinished = true
                            btnText = "Try again!"
                        }
                    }
                }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Gray,
            )
        ) {

            Text(
                text = btnText
            )
        }
    }
}

