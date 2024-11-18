package com.example.ppt2.dal

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Jugadores")
data class JugadorEntity(
    @PrimaryKey(autoGenerate = true)
    var id:Long = 0,
    var name : String = "",
    var jugadas : Int = 0,
    var rondasGanadas : Int = 0,
    var ganadas: Int = 0
)
