package com.example.contactosroom.Agenda

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode.Companion.Color
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.contactosroom.Agenda.MainActivity.Companion.basedatos
import com.example.contactosroom.R
import com.example.contactosroom.dal.ContactoEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Composable
fun ContactoView(){
    var contactos by remember { mutableStateOf<List<ContactoEntity>>(emptyList()) }
    LaunchedEffect(Unit) {
        contactos = cargaContactos()
    }

    ItemList(contactos);
}

@Composable
fun ItemList(itemContacto: List<ContactoEntity>) {
    LazyColumn {	// produce una lista de desplazamiento vertical,
        items(itemContacto) { itemContacto ->
            ContactoCarta(itemContacto)
        }
    }
}

@Composable
fun ContactoCarta(contacto: ContactoEntity) {

    var foto = contacto.imagen

    Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFF2F0EF)
            ),
    ) {
        Row {
            Column {
                Image(
                    painter = painterResource(devuelveFoto(foto)),
                    contentDescription = "Foto contacto",
                    Modifier.height(100.dp)
                )
            }
            Column {
                Text(
                    text = contacto.nombre,
                    fontSize = 24.sp,
                    modifier = Modifier.padding(8.dp)
                )
                Text(
                    text = contacto.telefono,
                    fontSize = 24.sp,
                    modifier = Modifier.padding(8.dp)
                )}

        }
    }
}
@Composable
fun devuelveFoto(choice: String): Int{
    var imagen = when (choice) {
        "amarillo" -> R.drawable.amarillo
        "rosa" -> R.drawable.rosa
        "pibemoreno" -> R.drawable.pibemoreno
        "pibepelirrojo" -> R.drawable.pibepelirrojo
        else -> R.drawable.imgdefault
    }
    return imagen
}

suspend fun cargaContactos(): List<ContactoEntity> {
    return withContext(Dispatchers.IO) {
        // Cargar los contactos desde la base de datos
        basedatos.contactoDao().getAllContactos()
    }
}

