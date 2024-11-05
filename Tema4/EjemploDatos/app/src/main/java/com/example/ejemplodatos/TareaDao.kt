package com.example.ejemplodatos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update


@Dao
interface TareaDao {
    @Query("SELECT * FROM tareas")
    suspend fun getAll():List<TareasEntity>
    @Query("SELECT * FROM tareas WHERE id = :id")
    suspend fun get(id: Long): TareasEntity
    @Insert
    suspend fun insert(tarea: TareasEntity): Long
    @Update
    suspend fun update (tarea: TareasEntity)
    @Delete
    suspend fun delete(tarea: TareasEntity)
}