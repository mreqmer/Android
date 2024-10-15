package com.example.ejercicio02_widgets

import android.R
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderPositions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun WidgetsView(){
    var checked by rememberSaveable { mutableStateOf(false) }
    var checkedBoton by rememberSaveable { mutableStateOf(false) }
    var texto by rememberSaveable { mutableStateOf("") }
    var sliderPosition by rememberSaveable { mutableStateOf(0f) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal =10.dp),

        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(
            Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically



        ) {
            Text("Checkbox")
            Checkbox(
                checked = checked,
                onCheckedChange = { checked = it }
            )

            Text("Checkbox")
            Checkbox(
                checked = checked,
                onCheckedChange = { checked = it }
            )
        }
        TextField(
            value = texto,
            onValueChange = { newText ->
                texto = newText},

            label = { Text("Escribe algo") }
            )
        Text(
            text = texto
        )

        Slider(
            value = sliderPosition,
            onValueChange = { sliderPosition = it },
            steps = 50,
            valueRange = 0f .. 50f
        )
        Text(text = sliderPosition.toString())
        Row(){

            Checkbox(

                checked = checkedBoton,
                onCheckedChange = { checkedBoton = it }
            )

            Crossfade(targetState = checkedBoton) {expanded ->
                if(expanded){
                    Button(onClick = { onClick() }) {
                        Text("Boton con animacion")

                    }
                }
                }


            if(checkedBoton){
                Button(onClick = { onClick() }) {
                    Text("boton con if")

                }
            }
        }

        }
    }

fun onClick() {
    TODO("Not yet implemented")
}
