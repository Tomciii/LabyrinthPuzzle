package com.example.labyrinthpuzzle.persistence.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.labyrinthpuzzle.models.Labyrinth
import com.example.labyrinthpuzzle.persistence.utils.CustomConverters

@Database(
    entities = [Labyrinth::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(CustomConverters::class)
abstract class LabyrinthDatabase : RoomDatabase() {
    abstract fun dao(): LabyrinthDao

    companion object{
        @Volatile
        private var Instance: LabyrinthDatabase? = null

        fun getDatabase(context: Context): LabyrinthDatabase {
            return Instance ?: synchronized(this){
                Room.databaseBuilder(context, LabyrinthDatabase::class.java, "labyrinth_db")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also {
                        Instance = it
                    }
            }
        }
    }
}