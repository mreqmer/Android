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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
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
        OutlinedTextField(
            value = nombreJ,
            onValueChange = { nombreJ = it },
            label = { Text("Jugador") },
            placeholder = { Text("Jugador") },
            singleLine = true,
        )

        Spacer(modifier = Modifier.height(25.dp))
        // Botón
        Button (
            onClick = {
                coroutineScope.launch {
                    //mucho cuidao que esto esta solo para errores
                    //wipeo()
                    btnLogin(nombreJ.text.toString(), navController)
                }
            },
            Modifier
                .width(140.dp)
                .height(42.dp)
        ){
           Text("Entrar")
        }

    }
}

//Boton para entrar a la aplicacion si se ha escrito un nombre en el entry
//crea un usuario si no existe ya uno asi y si no entra con ese
suspend fun btnLogin(nombre: String, navController: NavController){
    if(nombre!= "" && nombre != null ){
        LoginJugador(nombre)
        //navega al juego mandandole el nombre del jugador
        navController.navigate("juego/${nombre}")
    }


}
//crea un usuario si no existe ya uno asi
private suspend fun LoginJugador(nombre: String) {
    withContext(Dispatchers.IO) {
        val jugador = basedatos.jugadorDao().getJugadorByName(nombre)

        if (jugador == null) {
            // Si no existe, inserta el nuevo jugador
            val nuevoJugador = JugadorEntity(name = nombre)
            basedatos.jugadorDao().insert(nuevoJugador)

            Log.d(TAG, nuevoJugador.name)
        }
    }
}
//esto es solo para errores
//private suspend fun wipeo() {
//    basedatos.jugadorDao().deleteAllJugadores()
//}
