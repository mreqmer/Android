package com.example.listatareas

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.listatareas.dal.TareasEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


/**
 * Lista
 */
@Composable
fun Lista(listaTareas: List<TareasEntity>, coroutineScope: CoroutineScope) {
    LazyColumn(){
        items(listaTareas){tarea->
            filaTarea(tarea, coroutineScope)
        }

    }
}

/**
 * NuevaTarea
 */
@Composable
fun nuevaTarea(coroutineScope: CoroutineScope) {

    var texto by remember { mutableStateOf("") }

    Row(){
        TextField(
            value = texto,
            onValueChange = {
                texto = it
            },
            label = {
                Text ( text = "Nueva Tarea")
            }
        )

        Button(
            onClick = {
                        var tarea = TareasEntity()
                            tarea.name = texto
                            coroutineScope.launch{
                            MainActivity.basedatos.TareasDao().insert(tarea)
                            listaTareas.addAll(MainActivity.basedatos.TareasDao().getAll())

                            texto = ""
                      }

                      },
        ){
            Text ("Añadir")
        }
    }
}

/**
 * FilaTarea
 */

@Composable
fun filaTarea(tarea: TareasEntity, coroutineScope: CoroutineScope){
    var checked by remember { mutableStateOf(tarea.isDone) }
    Row {
        Checkbox(
            checked = checked,
            onCheckedChange = {
                checked = it
                tarea.isDone = checked
                coroutineScope.launch {
                    MainActivity.basedatos.TareasDao().actualizar(tarea)
                }
            }
        )
        Text(text = tarea.name)
    }
}
/**
 *
 */

@Composable
fun miApp(){
    val coroutineScope = rememberCoroutineScope()
    var listaTareas = remember { mutableListOf<TareasEntity>() }
    LaunchedEffect(Unit) {
        listaTareas.clear()
        listaTareas.addAll(MainActivity.basedatos.TareasDao().getAll())
    }

    Column(
        Modifier.
            fillMaxSize(),

        verticalArrangement = Arrangement.SpaceEvenly

    )
    {
        nuevaTarea(coroutineScope)
        Lista(listaTareas, coroutineScope) }

}

