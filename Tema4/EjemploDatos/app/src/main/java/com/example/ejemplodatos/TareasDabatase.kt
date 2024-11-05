package com.example.ejemplodatos

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [TareasEntity::class], version=1, exportSchema = true)
abstract class TareasDabatase: RoomDatabase() {
    abstract fun TareaDao(): TareaDao
}