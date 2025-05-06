package com.anvipus.explore.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.anvipus.core.models.Movie

@Database(entities = [Movie::class], version = 1, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}