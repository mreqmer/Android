package com.example.ppt2

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import kotlinx.coroutines.delay


@Composable
fun juegoView(navController: NavHostController) {
    //contexto
    val context = LocalContext.current
    //controla el popup
    var isDialogVisible by remember { mutableStateOf(false) }
    //imagenes del jugador y de la maquina
    var imagenJ by remember { mutableStateOf("incognita") }
    var imagenM by remember { mutableStateOf("incognita") }
    //puntuaciones
    var ronda by remember { mutableStateOf(0) }
    var puntuacionJ by remember { mutableStateOf(0) }
    var puntuacionM by remember { mutableStateOf(0) }
    //estado del juego
    var finJuego by remember { mutableStateOf(false) }
    //delay de los botones
    var enabled by remember { mutableStateOf(true) }

    //se llama cada vez que se hace click en un boton de seleccion. Hace que se juegue cada ronda
    //region Ronda
    fun ronda(eleccionJ: String) {
        //lista de opciones de la maquina
        val options = listOf("piedra", "papel", "tijera")
        //desactiva los botones durante un tiempo para que no se pueda spamear
        enabled = false
        //seleccion del jugador
        imagenJ = eleccionJ
        //coge una posicion aleatoria de la lista de opciones para la maquina y se la asigna a la maquina
        imagenM = options.random()

        //Muestra toast dependiendo de quien gano la ronda
        if (ganadorRonda(imagenJ, imagenM) == "ganar") {
            puntuacionJ++
            ronda++
            Toast.makeText(context, "Jugador ganó la ronda!", Toast.LENGTH_SHORT).show()
        } else if (ganadorRonda(imagenJ, imagenM) == "perder") {
            puntuacionM++
            ronda++
            Toast.makeText(context, "Máquina ganó la ronda!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Empate!!", Toast.LENGTH_SHORT).show()
        }

        //si se llega a 5 rondas (los empates no sumas rondas) se comprueba quien gano y se va a la
        //pantalla correspondiente
        if (ronda == 5) {
            finJuego = true
            if (puntuacionJ > puntuacionM) {
                navController.navigate("finPartidaGanar")
            } else {
                navController.navigate("finPartidaPerder")
            }
        }
    }
    //endregion

    //delay de los botones
    LaunchedEffect(enabled) {
        if (enabled) return@LaunchedEffect
        else delay(2000)
        enabled = true
    }

    //region interfaz
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                WindowInsets.systemBars.asPaddingValues()
            ),

        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        //Fila con el boton ayuda
        Row(
            Modifier
                .fillMaxWidth()
                .height(80.dp)
                .padding(30.dp),
            horizontalArrangement = Arrangement.End
        ) {
            Image(
                modifier = Modifier
                    .size(20.dp)
                    .clickable { isDialogVisible = true },
                painter = painterResource(id = R.drawable.question),
                contentDescription = "ayuda",
            )
        }
        //Dialog con la ayuda del juego
        if (isDialogVisible) {
            AyudaDialog(onDismiss = { isDialogVisible = false })
        }
        //Fila con la puntuacion de los jugadores
        Row(
            Modifier
                .fillMaxWidth()
                .height(40.dp),

            horizontalArrangement = Arrangement.Center,
        )
        {
            Text(
                text = "${puntuacionJ}-${puntuacionM}",
                fontSize = 40.sp
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Imagen del jugador
            Image(
                painter = painterResource(getImageResource(imagenJ)),
                contentDescription = "Imagen jugador",
                modifier = Modifier
                    .weight(1f)
                    .aspectRatio(1f)
            )

            Spacer(modifier = Modifier.width(16.dp))

            // Imagen de la máquina
            Image(
                painter = painterResource(getImageResource(imagenM)),
                contentDescription = "Imagen máquina",
                modifier = Modifier
                    .weight(1f)
                    .aspectRatio(1f)
            )
        }
        // Barra inferior con los iconos para jugar
        Row(
            Modifier
                .fillMaxWidth()
                .height(80.dp)
                .padding(horizontal = 40.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(getImageResource("piedra")),
                contentDescription = "piedra",
                modifier = Modifier
                    .size(60.dp)
                    .clickable(enabled = enabled) {
                        ronda("piedra")
                        enabled = false
                    }
            )

            Image(
                painter = painterResource(getImageResource("papel")),
                contentDescription = "papel",
                modifier = Modifier
                    .size(60.dp)
                    .clickable(enabled = enabled) {
                        ronda("papel")
                        enabled = false
                    }
            )
            Image(
                painter = painterResource(getImageResource("tijera")),
                contentDescription = "tijera",
                modifier = Modifier
                    .size(60.dp)
                    .clickable(enabled = enabled) {
                        ronda("tijera")
                        enabled = false
                    }
            )


        }
    }
    //endregion
}
//Muestra un dialog con la imagen con las reglas del juego
@Composable
fun AyudaDialog(onDismiss: () -> Unit) {
    Dialog(onDismissRequest = onDismiss) {
        Box(
            modifier = Modifier
                .size(300.dp)
                .background(Color.White)
        ) {
            Image(
                painter = painterResource(R.drawable.gana),
                contentDescription = "AyudaJuego",
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

//dependiendo de la imagen que se seleccione le entra un string que devuelve la imagen correspondiente
@Composable
fun getImageResource(choice: String): Int {
    var imagen = when (choice) {
        "piedra" -> R.drawable.piedra
        "papel" -> R.drawable.papel
        "tijera" -> R.drawable.tijera
        else -> R.drawable.noselect
    }
    return imagen
}

//comprueba quien ha ganado la ronda
private fun ganadorRonda(jugador: String, maquina: String): String {
    var res: String

    if(jugador == maquina){
        res = "empato"
    }else if((jugador == "piedra" && maquina == "tijera") ||
        (jugador == "papel" && maquina == "piedra") ||
        (jugador == "tijera" && maquina == "papel")){
        res = "ganar"
    }else {
        res = "perder"
    }
    return res
}

