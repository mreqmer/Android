package com.example.logincalculadoracompose

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun loginView(navController: NavHostController) {
    var context = LocalContext.current
    var nombre by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable {  mutableStateOf("") }



    Column(
        Modifier
            .fillMaxSize(),


        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){


        Row(
            Modifier
                .padding(20.dp),

            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(

                text = "Nombre:",
                Modifier
                    .width(100.dp)
                    .padding(end = 10.dp),
                textAlign = TextAlign.End
            )
            TextField(
                value = nombre,
                onValueChange = { nombre = it },
                modifier = Modifier
                    .width(200.dp) ,
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    errorIndicatorColor = Color.Transparent,
                    containerColor = Color.LightGray,
                ),

                shape = RoundedCornerShape(12.dp),
            )
        }
        Row(
            Modifier
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically

        ) {
            Text(
                text = "Contraseña:",
                Modifier
                    .width(100.dp)
                    .padding(end = 10.dp),
                textAlign = TextAlign.End


            )
            TextField(
                value = password,
                onValueChange = { password = it },
                modifier = Modifier
                    .width(200.dp),


                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    errorIndicatorColor = Color.Transparent,
                    containerColor = Color.LightGray,
                ),
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                shape = RoundedCornerShape(12.dp),
            )
        }


        Button(
            onClick = {

                if(nombre.equals("Marta") && (password.equals("1234"))){
                    navController.navigate("calculadora/${nombre}")
                }else{
                    Toast.makeText(context,"Usuario o contraseña inválido", Toast.LENGTH_LONG).show()
                }


            },

        ){
            Text("LogIn")
        }



    }

}