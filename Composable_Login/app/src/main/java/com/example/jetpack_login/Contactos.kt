package com.example.jetpack_login

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity

@Preview
@Composable
fun ListaContactos() {
    ItemList(
        itemContacto = listOf(
            Contacto("Juan", "611123456", 1),
            Contacto("María", "678456123", 0),
            Contacto("Raúl", "644789456", 1),
            Contacto("Ana", "693882147", 0),
            Contacto("Juan", "611123456", 1),
            Contacto("María", "678456123", 0),
            Contacto("Raúl", "644789456", 1),
            Contacto("Ana", "693882147", 0),
            Contacto("Juan", "611123456", 1),
            Contacto("María", "678456123", 0),
            Contacto("Raúl", "644789456", 1),
            Contacto("Ana", "693882147", 0),
            Contacto("Juan", "611123456", 1),
            Contacto("María", "678456123", 0),
            Contacto("Raúl", "644789456", 1),
            Contacto("Ana", "693882147", 0),
            Contacto("Juan", "611123456", 1),
            Contacto("María", "678456123", 0),
            Contacto("Raúl", "644789456", 1),
            Contacto("Ana", "693882147", 0),
            Contacto("Juan", "611123456", 1),
            Contacto("María", "678456123", 0),
            Contacto("Raúl", "644789456", 1),
            Contacto("Ana", "693882147", 0)
        )
    )
}

@Composable
fun ItemList(itemContacto: List<Contacto>) {
    LazyColumn {	// produce una lista de desplazamiento vertical,
        items(itemContacto) { itemContacto ->
            ContactoView(contacto = itemContacto)
        }
    }
}

private fun realizarLlamada(context: Context, numero: String) {
    val intent = Intent(Intent.ACTION_DIAL).apply {
        data = Uri.parse("tel:$numero")
    }
    context.startActivity(intent)
}

@Composable
fun ContactoView(contacto: Contacto) {
    val context = LocalContext.current
    var isExpanded by remember { mutableStateOf(false) }
    val foto = if (contacto.imagen == 0) R.drawable.mujer_icon else R.drawable.hombre_icon

    Card(
        Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { isExpanded = !isExpanded }
    ) {
        Row(
            modifier = Modifier.padding(8.dp)
        ) {
            Image(
                painter = painterResource(foto),
                contentDescription = "Foto contacto",
                modifier = Modifier
                    .size(64.dp)
                    .padding(end = 8.dp)
            )
            Column {
                // Usamos Crossfade para intercambiar entre inicial y nombre completo
                Crossfade(targetState = isExpanded) { expanded ->
                    if (expanded) {
                        Column {
                            Text(
                                text = contacto.nombre,
                                fontSize = 24.sp,
                            )

                            Text(
                                text = contacto.tfno,
                                fontSize = 20.sp,
                                modifier = Modifier
                                    .padding(top = 8.dp)
                                    .clickable { realizarLlamada(context, contacto.tfno) }
                            )
                        }
                    } else {
                        Text(
                            text = contacto.nombre.take(1),
                            fontSize = 20.sp,
                            modifier = Modifier.padding(top = 8.dp)
                        )
                    }
                }
            }
        }
    }
}