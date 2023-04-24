package com.example.labyrinthpuzzle.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
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

    companion object{
        @Volatile
        private var Instance: MemoryPuzzleDatabase? = null

        fun getDatabase(context: Context): MemoryPuzzleDatabase {
            return Instance?: synchronized(this){
                Room.databaseBuilder(context, MemoryPuzzleDatabase::class.java, "memoryPuzzle_db")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also {
                        Instance = it
                    }
            }
        }
    }
}