package com.example.elcine.cinema

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.elcine.cinema.MainActivity.Companion.basedatos
import com.example.elcine.dal.ClienteEntity
import com.example.elcine.dal.ConfiguracionEntity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

@Composable
fun segundaActividadView(navController: NavHostController, idConfig: String?) {

    val coroutineScope = rememberCoroutineScope()
    var configuracion by remember { mutableStateOf(ConfiguracionEntity(0,0,0,0f))  }
    var enabled by remember { mutableStateOf(true) }

    LaunchedEffect(enabled) {
        var numClientes = 0
        if (enabled){
            while(numClientes < 100){
                delay(1000)
                numClientes += entraCliente(configuracion.numSalas)

            }
        }
        else {
            enabled = false
        }

    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){

        Text("Salas")
        LaunchedEffect(Unit) {
            basedatos.cinemaDao().deleteClientes()
            configuracion = basedatos.cinemaDao().getConfiguracion(idConfig?.toLong() ?: 0)!!
        }
        salas(configuracion)

    }

}


@Composable
fun salas(configuracion : ConfiguracionEntity?){

    LazyColumn {

        if (configuracion != null) {
            items(configuracion.numSalas) { index ->
                SalaItem(index = index + 1)
            }
        }

    }

}

@Composable
fun SalaItem(index: Int) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(8.dp))

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),

            shape = RoundedCornerShape(4.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.Green // Color de la barra
            )
        ) {
            Row(

                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            )
            {
                Text(
                    text = "Sala $index",
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

suspend fun entraCliente(numSalas : Int) : Int{

    var numClientes = Random.nextInt(1, 3)
    var salaElegida : Int
    var newCliente : ClienteEntity

    for (i in 1..numClientes) {
        salaElegida = Random.nextInt(1, numSalas+1)
        newCliente = ClienteEntity(salaElegida = salaElegida, palomitas = 0)
        basedatos.cinemaDao().insert(newCliente)
    }

    return numClientes;
}