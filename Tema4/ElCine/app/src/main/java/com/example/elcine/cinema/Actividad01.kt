package com.example.elcine.cinema

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.navigation.NavHostController

@Composable
fun primeraActividadView(navController: NavHostController) {
    var numSalas by remember { mutableStateOf(0) }
    var numAsientosSala by remember { mutableStateOf(0) }
    var precioPalomitas by remember { mutableStateOf(0f) }


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        OutlinedTextField(
            value = numSalas,
            onValueChange = { numSalas = it },
            label = { Text("Selecciona un avatar") },
        )




    }


}