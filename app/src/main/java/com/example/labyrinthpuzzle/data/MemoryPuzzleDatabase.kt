package com.example.labyrinthpuzzle.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.labyrinthpuzzle.models.Memory
import com.example.labyrinthpuzzle.utils.CustomConverters

@Database(
    entities = [Memory::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(CustomConverters::class)
abstract class MemoryPuzzleDatabase : RoomDatabase() {
    abstract fun memoryPuzzleDao(): MemoryPuzzleDao
}