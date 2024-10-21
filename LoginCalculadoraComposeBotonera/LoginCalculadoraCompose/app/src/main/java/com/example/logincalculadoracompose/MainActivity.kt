package com.example.logincalculadoracompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = "login"
            ) {
                composable(route="login") { loginView(navController) }
                composable("calculadora/{nombre}") { backStackEntry ->
                    calculadoraView(
                        navController,
                        backStackEntry.arguments?.getString("nombre"),
                    )
                }
                composable(route="ResultadoSuma/{numero1}/{numero2}") { backStackEntry ->
                    ResultadoSuma(
                        navController,
                        backStackEntry.arguments?.getString("numero1"),
                        backStackEntry.arguments?.getString("numero2")
                    ) }
                composable(route="ResultadoResta/{numero1}/{numero2}") { backStackEntry ->
                    ResultadoResta(
                        navController,
                        backStackEntry.arguments?.getString("numero1"),
                        backStackEntry.arguments?.getString("numero2")
                    )
                }
                composable(route="ResultadoMultiplica/{numero1}/{numero2}") { backStackEntry ->
                    ResultadoMultiplica(
                        navController,
                        backStackEntry.arguments?.getString("numero1"),
                        backStackEntry.arguments?.getString("numero2")
                    ) }
                composable(route="ResultadoDivide/{numero1}/{numero2}") { backStackEntry ->
                    ResultadoDivide(
                        navController,
                        backStackEntry.arguments?.getString("numero1"),
                        backStackEntry.arguments?.getString("numero2")
                    ) }



            }
        }
    }
}

