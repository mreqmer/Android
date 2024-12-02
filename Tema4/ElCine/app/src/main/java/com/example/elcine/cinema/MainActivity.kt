package com.example.elcine.cinema

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.elcine.dal.CinemaDatabase
import com.example.elcine.dal.ClienteEntity
import com.example.elcine.dal.ConfiguracionEntity
import com.example.elcine.ui.theme.ElCineTheme
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainActivity : ComponentActivity() {
    companion object{
        lateinit var basedatos: CinemaDatabase
        lateinit var listaClientes: List<ClienteEntity>
        lateinit var listaConfiguracion: List<ConfiguracionEntity>
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        basedatos = Room.databaseBuilder(
            this, CinemaDatabase::class.java, "cinema-db"
        ).build()
        runBlocking {
            launch{
                listaClientes = basedatos.cinemaDao().getAllClientes()
                listaConfiguracion = basedatos.cinemaDao().getAllConfiguraciones()
            }
        }
        enableEdgeToEdge()
        setContent {
            ElCineTheme {
                val navController = rememberNavController()
                NavHost(
                    modifier = Modifier.padding(top = 60.dp).fillMaxSize(),
                    navController = navController,
                    startDestination = "Actividad01"
                ){
                    composable("Actividad01") { primeraActividadView(navController) }
                    composable(route="Actividad02/{idConfig}") { backStackEntry ->
                        segundaActividadView(
                            navController,
                            backStackEntry.arguments?.getString("idConfig")
                        )
                    }
                    composable("Actividad03") { terceraActividadView(navController) }
                }
                Box(
                    contentAlignment = Alignment.TopCenter,
                    modifier = Modifier.fillMaxSize()
                ){

                    BarraNavegacion(navController)
                }

            }
        }
    }
}

