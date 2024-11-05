package com.example.listatareas

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
import com.example.listatareas.dal.TareasDatabase
import com.example.listatareas.dal.TareasEntity
import com.example.listatareas.ui.theme.ListaTareasTheme
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainActivity : ComponentActivity() {
    companion object{
        lateinit var basedatos: TareasDatabase
        lateinit var todas: List<TareasEntity>
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        basedatos = Room.databaseBuilder(
            this, TareasDatabase::class.java, "tareas-db"
        ).build()
        runBlocking {
            launch{
                todas = basedatos.TareasDao().getAll()
            }
        }
        enableEdgeToEdge()
        setContent {
            ListaTareasTheme {
                miApp()
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
    ListaTareasTheme {
        Greeting("Android")
    }
}