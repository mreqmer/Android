package com.example.contador

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.contador.ui.theme.ContadorTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ContadorTheme {
                BasicCountdownTimer()
                }
            }
        }
    }


@Composable
fun BasicCountdownTimer() {
    var timeLeft by remember { mutableStateOf(60) }
    var isPaused by remember { mutableStateOf(false) }

    Column(

        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        LaunchedEffect(key1 = timeLeft, key2 = isPaused) {
            while (timeLeft > 0 && !isPaused) {
                delay(1000L)
                timeLeft--
            }
        }

        fun resetTimer() {
            timeLeft = 60
            isPaused = false
        }

        Column {
            Text(text = "Time left: $timeLeft")
            Button(onClick = { isPaused = !isPaused }) {
                Text(text = if (isPaused) "Resume" else "Pause")
            }
            Button(onClick = { resetTimer() }) {
                Text(text = "Reset")
            }
        }
    }
}