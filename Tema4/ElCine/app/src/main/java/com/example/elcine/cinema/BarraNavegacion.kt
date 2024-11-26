package com.example.elcine.cinema

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.input.pointer.PointerIcon.Companion.Text
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun BarraNavegacion(navController: NavController) {

    Row (
        modifier = Modifier
            .windowInsetsPadding(WindowInsets.safeDrawing)
            .fillMaxWidth()
            .background(Color(0xFFF5F5DC))
            .height(40.dp),

        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.Center
    ){
        OutlinedButton(
            onClick = {navController.navigate("Actividad01")},
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .padding(0.dp),
            shape = RectangleShape
        ) {
            Text(" 1 ")
        }

        OutlinedButton(
            onClick = {navController.navigate("Actividad02")},
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .padding(0.dp),
            shape = RectangleShape
        ) {
            Text(" 2 ")
        }

        OutlinedButton(
            onClick = {navController.navigate("Actividad03")},
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .padding(0.dp),
            shape = RectangleShape
        ) {
            Text(" 3 ")
        }
    }

}


