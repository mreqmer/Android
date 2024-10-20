package com.example.logincalculadoracompose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun calculadoraView(navController: NavHostController) {
    var factor1 by rememberSaveable { mutableStateOf("") }
    var factor2 by rememberSaveable { mutableStateOf("") }
    var res by rememberSaveable { mutableStateOf("0.0") }

    Column(

        Modifier
            .fillMaxSize(),


        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ){
        Button(
            onClick = { navController.popBackStack() },

            ){
            Text("Back")
        }
        Text(
            text = "Bienvenido/a nombre",
            modifier = Modifier.padding(bottom = 50.dp),
            fontSize = 30.sp,
            color = Color.Cyan
        )

        TextField(

            value = factor1,
            onValueChange = { factor1 = it },
            modifier = Modifier
                .width(200.dp)
                .padding(bottom = 30.dp),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.Transparent,
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            label = { Text("Factor 1") },
        )

        TextField(
            value = factor2,
            onValueChange = { factor2 = it },
            modifier = Modifier
                .width(200.dp)
                .padding(bottom = 50.dp),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.Transparent,
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            label = { Text("Factor 2") },
        )

        Text(

            text = res,
            fontSize = 30.sp,
        )


        Spacer(Modifier.height(200.dp))

        Row(

            Modifier
                .width(350.dp),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.SpaceEvenly,

        ){

            Button(
                onClick = {res = suma(factor1, factor2)},

            ){
                Text("+")
            }

            Button(
                onClick = {res = resta(factor1, factor2)},

                ){
                Text("-")
            }

            Button(
                onClick = {res = multiplica(factor1, factor2) },

                ){
                Text("*")
            }

            Button(
                onClick = {res = divide(factor1, factor2) },

                ){
                Text("/")
            }
        }
    }
}

fun suma(factor1: String, factor2: String): String {
    val numero1 = factor1.toDoubleOrNull() ?: 0.0
    val numero2 = factor2.toDoubleOrNull() ?: 0.0

    val resultado = numero1 + numero2

    return resultado.toString()
}

fun resta(factor1: String, factor2: String): String {
    val numero1 = factor1.toDoubleOrNull() ?: 0.0
    val numero2 = factor2.toDoubleOrNull() ?: 0.0

    val resultado = numero1 - numero2

    return resultado.toString()
}

fun multiplica(factor1: String, factor2: String): String {
    val numero1 = factor1.toDoubleOrNull() ?: 0.0
    val numero2 = factor2.toDoubleOrNull() ?: 0.0

    val resultado = numero1 * numero2

    return resultado.toString()
}

fun divide(factor1: String, factor2: String): String {
    val numero1 = factor1.toDoubleOrNull() ?: 0.0
    val numero2 = factor2.toDoubleOrNull() ?: 0.0

    val resultado = numero1 / numero2

    return resultado.toString()
}

