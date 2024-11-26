package com.example.elcine.dal

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tConfiguracion")
data class ConfiguracionEntity(
    @PrimaryKey(autoGenerate = true)
    var idConfiguracion: Long = 0,
    var numSalas : Int = 0,
    var numAsientos: Int,
    var precioPalomitas: Float,
)


//numSalas INT,
//numAsientos INT,
//precioPalomitas FLOAT(4,2
