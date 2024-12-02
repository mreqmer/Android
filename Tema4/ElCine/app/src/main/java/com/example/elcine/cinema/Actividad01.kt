package com.example.elcine.cinema

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.elcine.cinema.MainActivity.Companion.basedatos
import com.example.elcine.dal.ConfiguracionEntity
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

@Composable
fun primeraActividadView(navController: NavHostController) {
    var numSalas by remember { mutableStateOf(0) }
    var numAsientosSala by remember { mutableStateOf(0) }
    var precioPalomitas by remember { mutableStateOf(0f) }
    val coroutineScope = rememberCoroutineScope()


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        OutlinedTextField(
            value = numSalas.toString(),
            onValueChange = { newValue -> numSalas = newValue.toIntOrNull() ?: 0 },
            label = { Text("Numero de salas") },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number
            ),
        )
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = numAsientosSala.toString(),
            onValueChange = { newValue -> numAsientosSala = newValue.toIntOrNull() ?: 0 },
            label = { Text("Numero de asientos/sala") },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number
            ),
        )
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = precioPalomitas.toString(),
            onValueChange = { newValue -> precioPalomitas = newValue.toFloatOrNull() ?: 0f },
            label = { Text("Precio palomitas") },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Decimal
            ),
        )
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                var configuracion = ConfiguracionEntity(
                    numSalas = numSalas,
                    numAsientos = numAsientosSala,
                    precioPalomitas = precioPalomitas
                )
                coroutineScope.launch {
                    basedatos.cinemaDao().insert(configuracion)
                }

                coroutineScope.launch {
                    var ultimaId = basedatos.cinemaDao().getLastConfiguracion()
                    navController.navigate("Actividad02/${ultimaId.toString()}")
                }



            },
        ) {
            Text("Guardar")
        }
    }
}
