package com.pipingisodraw.core.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [ProjectEntity::class, PlacedComponentEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(PipingTypeConverters::class)
abstract class PipingIsoDatabase : RoomDatabase() {
    abstract fun projectDao(): ProjectDao

    abstract fun placedComponentDao(): PlacedComponentDao
}
