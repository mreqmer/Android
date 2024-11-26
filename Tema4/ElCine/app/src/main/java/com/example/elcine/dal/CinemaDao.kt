package com.example.elcine.dal

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface CinemaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(cliente: ClienteEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(configuracion: ConfiguracionEntity)

    @Update
    suspend fun update (cliente: ClienteEntity)

    @Update
    suspend fun update (configuracion: ConfiguracionEntity)

    @Query("SELECT * FROM tClientes")
    suspend fun getAllClientes(): List<ClienteEntity>

    @Query("SELECT * FROM tConfiguracion")
    suspend fun getAllConfiguraciones(): List<ConfiguracionEntity>

    @Query("Select * FROM tClientes WHERE idCliente = :idCliente")
    suspend fun  getCliente(idCliente: Long): ClienteEntity?

//    @Query("SELECT numSalas FROM tConfiguracion WHERE idConfiguracion = :idConfiguracion")
//    suspend fun  getNumeroSalas(idCliente: Long): ClienteEntity?

    @Query("Select * FROM tConfiguracion WHERE idConfiguracion = :idConfiguracion")
    suspend fun  getConfiguracion(idConfiguracion: Long): ConfiguracionEntity?
}