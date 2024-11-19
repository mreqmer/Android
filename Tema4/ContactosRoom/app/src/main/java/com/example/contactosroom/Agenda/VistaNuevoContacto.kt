package com.example.contactosroom.Agenda

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.contactosroom.Agenda.MainActivity.Companion.basedatos
import com.example.contactosroom.R
import com.example.contactosroom.dal.ContactoEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

//Vista para agregar un nuevo contacto
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgregarContactoView(navController: NavHostController) {
    //datos del contacto
    var nombre by remember { mutableStateOf("") }
    var telefono by remember { mutableStateOf("") }
    var avatarSeleccionado by remember { mutableStateOf("imgdefault") }
    val context = LocalContext.current
    //variable para el dropdown menu
    var expandido by remember { mutableStateOf(false) }
    //corrutina para la BD
    val coroutineScope = rememberCoroutineScope()
    // Lista de avatares disponibles
    val avatares = listOf("imgdefault", "amarillo", "rosa", "pibemoreno", "pibepelirrojo")


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Marco para el avatar
        Box(
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .background(Color.Gray),
            contentAlignment = Alignment.Center
        ) {
            // Imagen dentro del marco
            Image(
                painter = painterResource(id = getFotoPerfil(avatarSeleccionado)),
                contentDescription = "Avatar",
                modifier = Modifier.fillMaxSize()
            )
        }

        Spacer(modifier = Modifier.height(60.dp))

        // Selector de avatar
        ExposedDropdownMenuBox(
            expanded = expandido,
            onExpandedChange = { expandido = !expandido },

            ) {
            OutlinedTextField(
                value = avatarSeleccionado,
                onValueChange = { },
                label = { Text("Selecciona un avatar") },
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandido) },
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor()
            )

            //logica del menu
            ExposedDropdownMenu(
                expanded = expandido,
                onDismissRequest = { expandido = false }
            ) {
                avatares.forEach { avatar ->
                    DropdownMenuItem(
                        text = { Text(avatar) },
                        onClick = {
                            avatarSeleccionado = avatar
                            expandido = false
                        },
                        modifier = Modifier.background(Color(0xFFF2F0EF))
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(25.dp))

        // Campo de nombre
        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(25.dp))

        // Campo de teléfono
        OutlinedTextField(
            value = telefono,
            onValueChange = { telefono = it },
            label = { Text("Teléfono") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Phone),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(30.dp))

        // Botón para guardar el contacto (puedes agregar la lógica aquí)
        Button(
            onClick = {
                coroutineScope.launch {
                    btnGuardaContacto(nombre, telefono, avatarSeleccionado, context, navController);
                }

            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Guardar Contacto")
        }
    }
}

//devuelve el id de una foto a partir de un string para las fotos de perfil
fun getFotoPerfil(avatar: String): Int {
    return when (avatar) {
        "amarillo" -> R.drawable.amarillo
        "rosa" -> R.drawable.rosa
        "pibemoreno" -> R.drawable.pibemoreno
        "pibepelirrojo" -> R.drawable.pibepelirrojo
        else -> R.drawable.imgdefault
    }
}

//boton que que comprueba que los campos no esten vacios, además si estan rellenos crea el
// objeto contacto y le hace el insert
suspend fun btnGuardaContacto(
    nombre: String,
    telefono: String,
    avatarSeleccionado: String,
    context: Context,
    navController: NavHostController
) {
    //objeto Contacto
    var nuevoContacto: ContactoEntity;

    if (nombre != "" && telefono != "") {
        nuevoContacto = ContactoEntity(
            nombre = nombre,
            telefono = telefono,
            imagen = avatarSeleccionado
        )
        basedatos.contactoDao().insert(nuevoContacto)
        navController.navigate("ContactoView")
    //toast para la informacion del usuario
    } else if (nombre == "") {
        Toast.makeText(context, "El nombre no puede estar vacío", Toast.LENGTH_SHORT).show()
    } else if (telefono == "") {
        Toast.makeText(context, "El telefono no puede estar vacío", Toast.LENGTH_SHORT).show()
    }
}