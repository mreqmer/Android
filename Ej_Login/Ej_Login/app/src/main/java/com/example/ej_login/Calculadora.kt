package com.example.ej_login

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Calculadora : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_calculadora)
        //coge el nombre de usuario que hemos almacenado como "usuario"
        val nombre = intent.getStringExtra("usuario")
        //cogemos el campo de bienvenida en una variable y luego le añadimos con el .text el texto que queramos
        val bienvenida = findViewById<TextView>(R.id.txtBienvenida)
        bienvenida.text = "Bienvenido/a, $nombre"

        }
    }
