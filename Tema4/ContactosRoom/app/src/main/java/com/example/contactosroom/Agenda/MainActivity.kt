package com.example.contactosroom.Agenda

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.room.Room
import com.example.contactosroom.dal.ContactoEntity
import com.example.contactosroom.dal.ContactosDatabase
import com.example.contactosroom.ui.theme.ContactosRoomTheme
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainActivity : ComponentActivity() {
    companion object{
        lateinit var basedatos: ContactosDatabase
        lateinit var todas: List<ContactoEntity>
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        basedatos = Room.databaseBuilder(
            this, ContactosDatabase::class.java, "contactos-db"
        ).build()
        runBlocking {
            launch{
                todas = basedatos.contactoDao().getAllContactos()
            }
        }
        enableEdgeToEdge()
        setContent {
            ContactosRoomTheme {

                    ContactoView()

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
    ContactosRoomTheme {
        Greeting("Android")
    }
}