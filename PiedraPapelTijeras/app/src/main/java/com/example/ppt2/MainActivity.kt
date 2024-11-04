package com.example.ppt2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ppt2.ui.theme.PPT2Theme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PPT2Theme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "juego"
                ) {
                    composable("juego") { juegoView(navController) }
                    composable("finPartidaGanar") { finPartidaGanar(navController) }
                    composable("finPartidaPerder") { finPartidaPerder(navController) }
                }
            }
        }
    }
}

