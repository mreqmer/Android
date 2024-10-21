package com.example.logincalculadoracompose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun ejemploView() {
    var factor1 by rememberSaveable { mutableStateOf("") }
    var factor2 by rememberSaveable { mutableStateOf("") }
    var res by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp), // Padding general
        verticalArrangement = Arrangement.SpaceBetween, // Distribuir el espacio entre elementos
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Espaciador para centrar los TextField
        Spacer(modifier = Modifier.weight(1f))

        // Parte superior de la columna
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Bienvenido/a nombre")

            TextField(
                value = factor1,
                onValueChange = { factor1 = it },
                modifier = Modifier.width(200.dp),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.Transparent,
                ),
                label = { Text("Factor 1") },
            )

            TextField(
                value = factor2,
                onValueChange = { factor2 = it },
                modifier = Modifier.width(200.dp),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.Transparent,
                ),
                label = { Text("Factor 2") },
            )

            TextField(
                value = res,
                onValueChange = { res = it },
                modifier = Modifier.width(200.dp),
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    errorIndicatorColor = Color.Transparent,
                    containerColor = Color.Magenta,
                )
            )
        }

        // Espaciador para empujar la fila de botones hacia abajo
        Spacer(modifier = Modifier.weight(1f))

        // Row para los botones en la parte inferior
        Row(
            modifier = Modifier
                .fillMaxWidth() // Asegura que la fila ocupe todo el ancho
                .padding(bottom = 20.dp), // Padding para la fila
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = {
                    // Acción para sumar
                },
            ) {
                Text("+")
            }

            Button(
                onClick = {
                    // Acción para restar
                },
            ) {
                Text("-")
            }

            Button(
                onClick = {
                    // Acción para multiplicar
                },
            ) {
                Text("*")
            }

            Button(
                onClick = {
                    // Acción para dividir
                },
            ) {
                Text("/")
            }
        }
    }
}