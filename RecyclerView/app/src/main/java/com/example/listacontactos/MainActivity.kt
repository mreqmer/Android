package com.example.listacontactos

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val contactos = listOf(
            Contacto("Juan","611123456", 0),
            Contacto("María","678456123", 1),
            Contacto("Raúl","644789456",0),
            Contacto("Ana","693882147",1),
            Contacto("Juan","611123456", 0),
            Contacto("María","678456123", 1),
            Contacto("Raúl","644789456",0),
            Contacto("Ana","693882147",1),
            Contacto("Juan","611123456", 0),
            Contacto("María","678456123", 1),
            Contacto("Raúl","644789456",0),
            Contacto("Ana","693882147",1),
            Contacto("Juan","611123456", 0),
            Contacto("María","678456123", 1),
            Contacto("Raúl","644789456",0),
            Contacto("Ana","693882147",1)
        )
        val adapter = ContactosAdapter(contactos)
        val recyclerView = findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.vistaContactos)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
    }
}