package com.example.ppt2.Juego

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.ppt2.Juego.MainActivity.Companion.basedatos
import com.example.ppt2.dal.JugadorEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


@Composable
fun puntuacionesView(navController: NavHostController, nombre: String?) {
    // Estado para almacenar la lista de jugadores
    var jugadores by remember { mutableStateOf<List<JugadorEntity>>(emptyList()) }

    // LaunchedEffect se ejecuta solo una vez cuando el Composable es creado
    LaunchedEffect(Unit) {
        jugadores = cargaJugadores()
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(WindowInsets.systemBars.asPaddingValues())

    ) {
        // Fila superior con botón de retroceder
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back"
                )
            }
        }
        // Mostrar la lista de jugadores
        JugadoresList(jugadores = jugadores)
    }
    Log.d(TAG, jugadores.toString())
}

//Listado de los jugadores en un LazyView
@Composable
fun JugadoresList(jugadores: List<JugadorEntity>) {
    LazyColumn(modifier = Modifier.padding(16.dp)) {
        items(jugadores) { jugador ->
            JugadorItem(jugador = jugador)
        }
    }
}

//Cada elemento de la lista del LazyView con los datos de la entidad Jugador
@Composable
fun JugadorItem(jugador: JugadorEntity) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .border(1.dp, Color.Gray),
        horizontalAlignment = Alignment.Start
    ) {
        Text(text = "Nombre: ${jugador.name}")
        Text(text = "Jugadas: ${jugador.jugadas}")
        Text(text = "Ganadas: ${jugador.ganadas}")
        Text(text = "Rondas Ganadas: ${jugador.rondasGanadas}")
    }
}

// Función suspendida que carga los jugadores de la base de datos
suspend fun cargaJugadores(): List<JugadorEntity> {
    return withContext(Dispatchers.IO) {
        // Cargar los jugadores desde la base de datos
        basedatos.jugadorDao().getAllJugadores()
    }
}