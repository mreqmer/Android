package com.example.ppt2.Juego

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.ppt2.Juego.MainActivity.Companion.basedatos
import com.example.ppt2.dal.JugadorDao
import com.example.ppt2.dal.JugadorEntity
import com.example.ppt2.dal.JugadoresDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun loginView(navController: NavController) {
    val coroutineScope = rememberCoroutineScope()
    var nombreJ by remember { mutableStateOf(TextFieldValue("")) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Campo de texto con borde
        TextField(
            value = nombreJ,
            onValueChange = { nombreJ = it },
            placeholder = { Text("Nombre") },
            modifier = Modifier
                .border(2.dp, Color.Gray) // Borde gris
                .fillMaxWidth()
                .padding(16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))
        // Botón
        Button(
            onClick = {

                coroutineScope.launch {
                    //wipeo()
                    btnLogin(nombreJ.text.toString(), navController)

                }


            },
                Modifier.fillMaxWidth()
                ) {
                    Text("Enviar")
                }

    }
}

suspend fun btnLogin(nombre: String, navController: NavController){

    LoginJugador(nombre)
    navController.navigate("juego/${nombre}")

}

private suspend fun LoginJugador(nombre: String) {
    withContext(Dispatchers.IO) {
        val jugador = basedatos.jugadorDao().getJugadorByName(nombre)

        if (jugador == null) {
            // Si no existe, inserta el nuevo jugador
            val nuevoJugador = JugadorEntity(name = nombre)  // Asegúrate de que tienes el constructor adecuado
            basedatos.jugadorDao().insert(nuevoJugador)

            Log.d(TAG, nuevoJugador.name)
        }
    }
}

private suspend fun wipeo() {

    basedatos.jugadorDao().deleteAllJugadores()
}
