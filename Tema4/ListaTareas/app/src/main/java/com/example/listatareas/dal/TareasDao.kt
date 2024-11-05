package com.example.listatareas.dal

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface TareaDao {
    @Query("SELECT * FROM Tareas")
    suspend fun getAll():List<TareasEntity>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(tarea: TareasEntity) : Long
    @Update
    suspend fun actualizar(tarea: TareasEntity)
}