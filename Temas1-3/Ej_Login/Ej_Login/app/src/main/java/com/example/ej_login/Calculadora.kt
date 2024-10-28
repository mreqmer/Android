package com.example.ej_login

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class Calculadora : AppCompatActivity() {
    companion object {
        private var factor1 = 0f
        private var factor2 = 0f
        private var res = 0f
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_calculadora)
        //coge el nombre de usuario que hemos almacenado como "usuario"
        val nombre = intent.getStringExtra("usuario")
        //cogemos el campo de bienvenida en una variable y luego le añadimos con el .text el texto que queramos
        val bienvenida = findViewById<TextView>(R.id.txtBienvenida)
        bienvenida.text = "Bienvenido/a, $nombre"

        //carga las funciones de los botones de la calculadora
        suma()
        resta()
        multiplicacion()
        division()

        findViewById<Button>(R.id.btnBack).setOnClickListener(){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        }

    fun leeNumeros(){
        factor1 = findViewById<EditText>(R.id.editFactor1).text.toString().toFloatOrNull()?:0f
        factor2 = findViewById<EditText>(R.id.editFactor2).text.toString().toFloatOrNull()?:0f
    }

    fun suma(){
        findViewById<Button>(R.id.btnMas).setOnClickListener(){
            leeNumeros()
            res = factor1 + factor2
            findViewById<TextView>(R.id.txtRes).text = res.toString()


        }
    }
    fun resta(){
        findViewById<Button>(R.id.btnResta).setOnClickListener(){
            leeNumeros()
            res = factor1 - factor2
            findViewById<TextView>(R.id.txtRes).text = res.toString()


        }
    }
    fun multiplicacion(){
        findViewById<Button>(R.id.btnMultiplica).setOnClickListener(){
            leeNumeros()
            res = factor1 * factor2
            findViewById<TextView>(R.id.txtRes).text = res.toString()


        }
    }
    fun division(){
        findViewById<Button>(R.id.btnDivide).setOnClickListener(){
            leeNumeros()
            if(factor2!=0f){
                res = factor1 / factor2
                findViewById<TextView>(R.id.txtRes).text = res.toString()
            }else{
                Toast.makeText(this, "Error, el divisor es 0", Toast.LENGTH_SHORT).show()

            }



        }
    }

    }
