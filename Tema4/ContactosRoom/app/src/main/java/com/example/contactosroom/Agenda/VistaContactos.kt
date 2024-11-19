package com.example.contactosroom.Agenda

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.contactosroom.Agenda.MainActivity.Companion.basedatos
import com.example.contactosroom.R
import com.example.contactosroom.dal.ContactoEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

//Vista con el listado de los distintos contactos
@Composable
fun ContactoView(navController: NavHostController) {
    var contactos by remember { mutableStateOf<List<ContactoEntity>>(emptyList()) }
    LaunchedEffect(Unit) {
        contactos = cargaContactos()
    }
    //con el box se contiene la aplicacion para poder poner el boton flotante abajo derecha
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(WindowInsets.systemBars.asPaddingValues())
    ) {
        // Contenido de la agenta
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            //listado contactos
            ItemList(contactos)
        }
        //boton flotante para ir a la pantalla de nuevo contacto
        FloatingActionButton(
            onClick = {  navController.navigate("addContactoView") },
            modifier = Modifier
                .fillMaxSize()
                .wrapContentWidth(Alignment.End)
                .wrapContentHeight(Alignment.Bottom)
                .padding(16.dp)
        ) {
            //icono del boton
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Añadir contacto"
            )
        }
    }
}

//LazyColumn
@Composable
fun ItemList(itemContacto: List<ContactoEntity>) {
    LazyColumn {
        items(itemContacto) { itemContacto ->
            ContactoCarta(itemContacto)
        }
    }
}

//contenido de cada parte del listado de contactos
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
                //foto perfil
                Image(
                    painter = painterResource(devuelveFoto(foto)),
                    contentDescription = "Foto contacto",
                    Modifier.height(100.dp)
                )
            }
            //datos del contacto
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
//devuelve el id de una foto a partir de un string para las fotos de perfil
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

//carga una lista de contactos de la base de datos
suspend fun cargaContactos(): List<ContactoEntity> {
    return withContext(Dispatchers.IO) {
        // Cargar los contactos desde la base de datos
        basedatos.contactoDao().getAllContactos()
    }
}

