package com.example.ejercicio01_lista

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ListadoObjetos() {
    ItemList(
        itemObjeto = listOf(
            Objeto("Patatas", "Patatitas fritas sabor jamon"),
            Objeto("Manzana", "Manzana roja y dulce"),
            Objeto("Arepas", "Arepas con carne desmechada"),
            Objeto("Macarrones", "Macarrones con tomatico y carnecita pica y quesito"),
            Objeto("San jacobo", "Con su jamoncito y su quesito")
        )
    )
}

@Composable
fun ItemList(itemObjeto: List<Objeto>) {

    LazyColumn {
        items(itemObjeto) { itemObjeto ->
            ElementosView(objeto = itemObjeto)
        }
    }
}

@Composable
fun ElementosView(objeto: Objeto) {
    var descripcion by rememberSaveable { mutableStateOf(true) }
    val foto = R.drawable.placeholder_background
    Card(
        Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .clickable { descripcion = !descripcion }
    ) {

        Row(


            Modifier
                .fillMaxSize()
                .padding(10.dp),

            verticalAlignment = Alignment.CenterVertically

        ) {
            Image(painterResource(foto), "Comida")

            Crossfade(targetState = descripcion) { expanded ->
                if (expanded) {
                    Column(
                        Modifier.padding(10.dp)
                    ) {

                        Text(
                            color = Color.Blue,
                            fontStyle = FontStyle.Italic,
                            text = objeto.nombre)

                    }
                } else {
                    Column {
                        Text(objeto.descripcion)
                    }
                }
            }

        }
    }
}
