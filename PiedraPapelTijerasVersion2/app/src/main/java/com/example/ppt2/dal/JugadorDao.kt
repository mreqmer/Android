package com.example.ppt2.dal

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface JugadorDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(jugador: JugadorEntity)

    @Update
    suspend fun update(jugador: JugadorEntity)

    @Query("SELECT * FROM Jugadores ORDER BY ganadas DESC")
    suspend fun getAllJugadores(): List<JugadorEntity>

    @Query("SELECT * FROM Jugadores WHERE name = :name LIMIT 1")
    suspend fun getJugadorByName(name: String): JugadorEntity?

    @Query("DELETE FROM jugadores")
    suspend fun deleteAllJugadores()

    @Query("UPDATE Jugadores SET jugadas = jugadas + 1 WHERE name = :name")
    suspend fun incrementaPartidasJugadas(name: String)
    @Query("UPDATE Jugadores SET rondasGanadas = rondasGanadas + 1 WHERE name = :name")
    suspend fun incrementaRondasGanadas(name: String)
    @Query("UPDATE Jugadores SET ganadas = ganadas + 1 WHERE name = :name")
    suspend fun incrementaPartidasGanadas(name: String)

}