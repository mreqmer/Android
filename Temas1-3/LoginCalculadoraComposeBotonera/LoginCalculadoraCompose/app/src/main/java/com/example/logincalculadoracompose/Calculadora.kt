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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController



@Composable
fun Botonera(navController: NavController, pantallaActual: String, numero1: String?, numero2: String?){
    Row(
        Modifier
            .width(350.dp),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.SpaceEvenly,

        ){

        Button(
            enabled = pantallaActual != "ResultadoSuma",
            onClick = {
                navController.navigate("ResultadoSuma/$numero1/$numero2")
            }
        ) {
            Text("+")
        }

        Button(
            enabled = pantallaActual != "ResultadoResta",
            onClick = {
                navController.navigate("ResultadoResta/$numero1/$numero2")
            }
        ) {
            Text("-")
        }

        Button(
            enabled = pantallaActual != "ResultadoMultiplica",
            onClick = {
                navController.navigate("ResultadoMultiplica/$numero1/$numero2")
            }
        ) {
            Text("*")
        }

        Button(
            enabled = pantallaActual != "ResultadoDivide",
            onClick = {
                navController.navigate("ResultadoDivide/$numero1/$numero2")
            }
        ) {
            Text("/")
        }
    }

}

@Composable
fun ResultadoSuma(navController: NavController, numero1: String?, numero2: String?){

    var res = suma(numero1, numero2)

     Column(

            Modifier
                .fillMaxSize(),

            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {

         Button(
             onClick = { navController.popBackStack() },

             ){
             Text("Back")
         }
            Text("El resultado de la suma de " + numero1 + " + " + numero2  +"=" )
            Text(res)


            Spacer(Modifier.height(500.dp))

            Botonera(navController = navController, "ResultadoSuma", numero1, numero2)
        }



}

@Composable
fun ResultadoResta(navController: NavController, numero1: String?, numero2: String?){


    var res = resta(numero1, numero2)

    Column(



        Modifier
            .fillMaxSize(),

        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {

        Button(
            onClick = { navController.popBackStack() },

            ){
            Text("Back")
        }

        Text("El resultado de la resta es:"+ res)


        Spacer(Modifier.height(500.dp))

        Botonera(navController = navController, "ResultadoResta", numero1, numero2)
    }

}

@Composable
fun ResultadoMultiplica(navController: NavController, numero1: String?, numero2: String?){
    var res = multiplica(numero1, numero2)

    Column(
        Modifier
            .fillMaxSize(),

        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {

        Button(
            onClick = { navController.popBackStack() },

            ){
            Text("Back")
        }

        Text("El resultado de la multiplicacion es: $res")


        Spacer(Modifier.height(500.dp))

        Botonera(navController = navController, "ResultadoMultiplica", numero1, numero2)
    }


}

@Composable
fun ResultadoDivide(navController: NavController, numero1: String?, numero2: String?){
    var res = divide(numero1, numero2)

    Column(
        Modifier
            .fillMaxSize(),

        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {

        Button(
            onClick = { navController.popBackStack() },

            ){
            Text("Back")
        }

        Text("El resultado de la division es: $res")


        Spacer(Modifier.height(500.dp))

        Botonera(navController = navController, "ResultadoDivision", numero1, numero2)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun calculadoraView(navController: NavHostController, nombre: String?) {
    var factor1 by rememberSaveable { mutableStateOf("") }
    var factor2 by rememberSaveable { mutableStateOf("") }


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
            text = "Bienvenido/a $nombre",
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

        Spacer(Modifier.height(200.dp))


            Botonera(navController = navController, "calculadora", factor1, factor2)







    }
}



