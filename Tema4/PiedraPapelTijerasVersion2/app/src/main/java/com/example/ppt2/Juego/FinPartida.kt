package com.example.ppt2.Juego

import android.media.MediaPlayer
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.request.ImageRequest
import coil.size.Size
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.ppt2.R

@Composable
fun finPartidaGanar(navController: NavHostController, nombre: String?) {

    val context = LocalContext.current
    //controla que se mutee o no el sonido
    var isMuted = remember { mutableStateOf(false) }
    //guarda el sonido, que se repetira en bucle
    val mp: MediaPlayer = remember {
        MediaPlayer.create(context, R.raw.desdentaobailando)
    }
    DisposableEffect(Unit) {
        mp.isLooping = true
        mp.start()
        onDispose {
            mp.stop()
        }
    }
    //animacion infinita para el texto
    val infiniteTransition = rememberInfiniteTransition(label = "Infinite Transition")
    val scale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 2f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000),
            repeatMode = RepeatMode.Reverse
        ),
        label = "Scale"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(WindowInsets.systemBars.asPaddingValues())
    ) {
        //funcionalidad del boton para silenciar y desilenciar el sonido
        fun toggleSound() {
            isMuted.value = !isMuted.value
            if (isMuted.value) {
                mp.setVolume(0f, 0f) // Silenciar
            } else {
                mp.setVolume(1f, 1f) // Volver a activar
            }
        }
        // Imagen en la parte superior que mutea
        Row(
            Modifier
                .fillMaxWidth()
                .height(80.dp)
                .padding(30.dp),
            horizontalArrangement = Arrangement.End
        ) {
            Image(
                modifier = Modifier
                    .size(20.dp)
                    .clickable { toggleSound() },
                painter = painterResource(
                    if (isMuted.value){
                        R.drawable.soundoff
                    } else R.drawable.soundon
                ),
                contentDescription = "ayuda",
            )
        }

        // Resto del contenido
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 80.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            GifImage()

            Text(
                text = "HAS GANADOOO!!!",
                modifier = Modifier
                    .padding(bottom = 60.dp)
                    .graphicsLayer {
                    scaleX = scale
                    scaleY = scale
                    transformOrigin = TransformOrigin.Center
                },
                fontFamily = FontFamily.Monospace
            )

                OutlinedButton(
                    onClick = {navController.navigate("juego/${nombre}") },
                    modifier = Modifier.size(200.dp, 50.dp)) {
                    Text("Volver a jugar")
                    }
                OutlinedButton(
                    onClick = {navController.navigate("puntuaciones/${nombre}")},
                    modifier = Modifier.size(200.dp, 50.dp)
                    ) {
                    Text("Scores")
                     }
                OutlinedButton(
                    onClick = {navController.navigate("login") },
                    modifier = Modifier.size(200.dp, 25.dp)
                    ) {
                    Text("Cambio de jugador")
                    }


        }
    }
}

//funcion para mostrar un gif
@Composable
fun GifImage(
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val imageLoader = ImageLoader.Builder(context)
        .components { add(GifDecoder.Factory()) }
        .build()
    Image(
        painter = rememberAsyncImagePainter(
            ImageRequest.Builder(context).data(data = R.drawable.desdentao).apply(block = {
                size(Size.ORIGINAL)
            }).build(), imageLoader = imageLoader
        ),
        contentDescription = null,
        modifier = modifier.size(300.dp),
    )
}