package com.example.jetpack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpack.ui.theme.JetpackTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                setContent {
                    ItemList(
                        itemContacto = listOf(
                            Contacto("Juan", "611123456", 0),
                            Contacto("María", "678456123", 1),
                            Contacto("Raúl", "644789456",0),
                            Contacto("Ana", "693882147", 1)
                        )
                    )
                }
            }
    }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JetpackTheme {
        Greeting("Android")
    }
}

@Composable
fun ItemList(itemContacto: List<Contacto>) {
    LazyColumn {	// produce una lista de desplazamiento vertical,
        items(itemContacto) { itemContacto ->
            ContactoView(contacto = itemContacto)
        }
    }
}

@Composable
fun ContactoView(contacto: Contacto) {
    var foto = R.drawable.default_profile;
    if(contacto.imagen == 0){
       foto = R.drawable.images;
    }
    Card(Modifier.fillMaxWidth()) {
        Row {
            Column {
                Image(
                    painter = painterResource(foto),
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
                    text = contacto.tfno,
                    fontSize = 24.sp,
                    modifier = Modifier.padding(8.dp)
                )}}}}



