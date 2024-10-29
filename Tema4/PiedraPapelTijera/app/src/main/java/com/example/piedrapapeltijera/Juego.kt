package com.example.piedrapapeltijera

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.Popup

@Preview
@Composable
fun juegoView(){

    var isDialogVisible by remember { mutableStateOf(false) }
    var piedra = painterResource(R.drawable.piedra)
    var papel = painterResource(R.drawable.papel)
    var tijera = painterResource(R.drawable.tijera)
    var incognita = painterResource(id = R.drawable.noselect)
    var eleccionJ by remember { mutableStateOf( incognita) }
    var eleccionM by remember { mutableStateOf(incognita) }
    //TODO Hacer que la imagen del jugador vaya dependiendo de lo que haya seleccionado
    var imagenJugador = painterResource(id = R.drawable.noselect)
    //TODO implementar una funcion que seleccione random una opcion
    var imagenMaquina = painterResource(id = R.drawable.noselect)

    var puntuacionJugador by remember { mutableStateOf(0) }
    var puntuacionMaquina by remember { mutableStateOf(0) }
    Column(
        
        modifier = Modifier 
            .fillMaxSize()
            .padding(
                WindowInsets.systemBars.asPaddingValues()
            ),

        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ){
        //Fila con el boton ayuda
        Row(
            Modifier
                .fillMaxWidth()
                .height(80.dp)
                .padding(30.dp),
            horizontalArrangement = Arrangement.End
        ){
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
        Row (
            Modifier
                .fillMaxWidth()
                .height(40.dp),

            horizontalArrangement = Arrangement.Center,
        )
        {
            //TODO implementar funcionalidad
            Text(
                text = "${puntuacionJugador}-${puntuacionMaquina}",
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
                painter = cambiaImagen(eleccionJ),
                contentDescription = "Imagen jugador",
                modifier = Modifier
                    .weight(1f)
                    .aspectRatio(1f)
            )

            Spacer(modifier = Modifier.width(16.dp))

            // Imagen de la máquina
            Image(
                painter = eleccionM,
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
                painter = piedra,
                contentDescription = "piedra",
                modifier = Modifier.size(60.dp)
            )
            Image(
                painter = papel,
                contentDescription = "papel",
                modifier = Modifier.size(60.dp)
            )
            Image(
                painter = tijera,
                contentDescription = "tijera",
                modifier = Modifier
                    .size(60.dp)
                    .clickable { imagenJugador = tijera }

            )
        }
    }
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
    }}

@Composable
fun cambiaImagen(eleccion : Int) : Painter {
    var res : Painter

    when (eleccion){
        1 -> res = painterResource(R.drawable.piedra)
        2 ->  res = painterResource(R.drawable.papel)
        3 -> res = painterResource(R.drawable.tijera)
        else -> res = painterResource(R.drawable.noselect)
    }

    return res
}

