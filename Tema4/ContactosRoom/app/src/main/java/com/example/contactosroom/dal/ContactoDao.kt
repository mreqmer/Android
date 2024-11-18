package com.example.contactosroom.dal

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface ContactoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(contacto: ContactoEntity)

    @Update
    suspend fun update (contacto: ContactoEntity)

    @Query("SELECT * FROM Contactos")
    suspend fun getAllContactos(): List<ContactoEntity>

    @Query("SELECT * FROM Contactos WHERE nombre = :nombre")
    suspend fun getContacto(nombre: String): ContactoEntity?

    @Query("DELETE FROM Contactos WHERE id = :id")
    suspend fun deleteContacto(id: Long)
}