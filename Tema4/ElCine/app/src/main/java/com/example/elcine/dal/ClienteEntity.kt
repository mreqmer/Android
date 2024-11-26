package com.example.elcine.dal

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tClientes")
data class ClienteEntity(
    @PrimaryKey(autoGenerate = true)
    var idCliente: Long = 0,
    var salaElegida: Int = 0,
    var palomitas: Int = 0
)

