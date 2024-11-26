package com.example.elcine.dal

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ClienteEntity::class, ConfiguracionEntity::class], version = 1)
abstract class CinemaDatabase: RoomDatabase() {

    abstract fun cinemaDao(): CinemaDao

}