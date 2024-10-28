package com.example.ej_login

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(R.layout.activity_main)

        //variable botón para hacer el login
        val button = findViewById<Button>(R.id.btnSend)
        //cuando se le da al botón comprueba el login, si es todo correcto va a la siguiente pantalla, la de calculadora
        button.setOnClickListener{

            //Variables nombre y password para el login de usuario
            val nombre = (findViewById<EditText>(R.id.txtUsuario)).text.toString()
            val password = findViewById<EditText>(R.id.txtPassword).text.toString()

            if(nombre.equals("Marta") && password.equals("1234")){
                //por alguna razón hay que crear el intent antes de almacenar las variables en el putExtra
                val intent = Intent(this, Calculadora::class.java)
                // Almacena la variable nombre para pasarla a otras clases
                intent.putExtra("usuario", nombre)
                // Inicia la nueva actividad
                startActivity(intent)
            }else{
                Toast.makeText(this, "Usuario incorrecto", Toast.LENGTH_SHORT).show()
            }

        }


    }
}