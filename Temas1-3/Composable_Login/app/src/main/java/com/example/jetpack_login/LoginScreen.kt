package com.example.jetpack_login

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Preview
@Composable
fun LoginView(navController: NavHostController) {
    val context = LocalContext.current
    var usuario by rememberSaveable   { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal =60.dp),

        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,

    ){
        Text(
            text = "Log in:",
            fontSize = 20.sp,
            color = Color.Blue,

        )
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(
            value = usuario,
            onValueChange = { usuario = it }, // Actualiza el valor del texto
            label = {
                Text(text = "Usuario", color = Color.Magenta)
            },
            singleLine = true
        )

        Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = {
                Text(text = "Contraseña", color = Color.Magenta)
            },
                visualTransformation = PasswordVisualTransformation(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(30.dp))

        Button(onClick = {

            if (usuario == "Marta" && password == "1234") {
               
                navController.navigate("contacts")
            } else {
                Toast.makeText(context, "Usuario o contraseña incorrectos", Toast.LENGTH_LONG).show()
            }
        }, colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)) {
            Text("Send")
        }

        }
    }



