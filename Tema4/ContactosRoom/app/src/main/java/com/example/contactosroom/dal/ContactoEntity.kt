package com.example.contactosroom.dal

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Contactos")
data class ContactoEntity (
    @PrimaryKey(autoGenerate = true)
    var id:Long = 0,
    var nombre : String = "",
    var telefono : String = "",
    var imagen : String = "imgdefault"
)