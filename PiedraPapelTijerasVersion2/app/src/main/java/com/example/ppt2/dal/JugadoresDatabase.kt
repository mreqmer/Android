package com.example.ppt2.dal

import androidx.room.Database
import androidx.room.RoomDatabase
import kotlinx.coroutines.CoroutineScope

@Database(entities = [JugadorEntity::class], version=1)
abstract class JugadoresDatabase: RoomDatabase() {

    abstract fun jugadorDao(): JugadorDao
}