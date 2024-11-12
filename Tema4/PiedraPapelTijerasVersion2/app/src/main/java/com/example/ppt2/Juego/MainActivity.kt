package com.example.ppt2.Juego

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.ppt2.dal.JugadorEntity
import com.example.ppt2.dal.JugadoresDatabase
import com.example.ppt2.ui.theme.PPT2Theme
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


class MainActivity : ComponentActivity() {
    companion object{
        lateinit var basedatos: JugadoresDatabase
        lateinit var todas: List<JugadorEntity>
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        basedatos = Room.databaseBuilder(
            this, JugadoresDatabase::class.java, "tareas-db"
        ).build()
        runBlocking {
            launch{
                todas = basedatos.jugadorDao().getAllJugadores()
            }
        }
        enableEdgeToEdge()
        setContent {
            PPT2Theme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "login"
                ) {
                    composable("login"){ loginView(navController) }
                    composable(route="juego/{nombre}") { backStackEntry ->
                        juegoView(
                            navController,
                            backStackEntry.arguments?.getString("nombre")
                        )
                    }
                    composable("finPartidaGanar/{nombre}") {backStackEntry ->
                        finPartidaGanar(
                            navController,
                            backStackEntry.arguments?.getString("nombre")
                        )
                    }
                    composable("finPartidaPerder/{nombre}") {backStackEntry ->
                        finPartidaPerder(
                            navController,
                            backStackEntry.arguments?.getString("nombre")
                        )
                    }
                    composable("puntuaciones/{nombre}") {backStackEntry ->
                        puntuacionesView(
                            navController,
                            backStackEntry.arguments?.getString("nombre")
                        )
                    }

                }
            }
        }
    }
}

