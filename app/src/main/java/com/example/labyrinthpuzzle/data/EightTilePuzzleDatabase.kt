package com.example.labyrinthpuzzle.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.labyrinthpuzzle.models.EightTile

/*@Database(
    entities = [EightTile::class],
    version = 1,
    exportSchema = false
)*/
abstract class EightTilePuzzleDatabase : RoomDatabase() {
    abstract fun eightTilePuzzleDao(): EightTilePuzzleDao

    companion object{
        @Volatile
        private var Instance: EightTilePuzzleDatabase? = null

        fun getDatabase(context: Context): EightTilePuzzleDatabase {
            return Instance?: synchronized(this){
                Room.databaseBuilder(context, EightTilePuzzleDatabase::class.java, "eightTilePuzzle_db")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also {
                        Instance = it
                    }
            }
        }
    }
}