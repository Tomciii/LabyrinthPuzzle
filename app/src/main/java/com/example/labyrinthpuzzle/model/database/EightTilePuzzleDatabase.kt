package com.example.labyrinthpuzzle.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.labyrinthpuzzle.model.dao.EightTilePuzzleDao
import com.example.labyrinthpuzzle.model.models.Eight
import com.example.labyrinthpuzzle.model.models.eightPuzzleLists
import com.example.labyrinthpuzzle.model.utils.CustomConverters
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities = [Eight::class],
    version = 5,
    exportSchema = false
)
@TypeConverters(CustomConverters::class)
abstract class EightTilePuzzleDatabase : RoomDatabase() {
    abstract fun dao(): EightTilePuzzleDao

    companion object{
        @Volatile
        private var Instance: EightTilePuzzleDatabase? = null

        fun getDatabase(context: Context): EightTilePuzzleDatabase {
            return Instance ?: synchronized(this){
                Room.databaseBuilder(
                    context,
                    EightTilePuzzleDatabase::class.java,
                    "eightPuzzle_db")
                    .addCallback(object : Callback(){
                        override fun onOpen(db: SupportSQLiteDatabase) {
                            super.onOpen(db)

                            CoroutineScope(Dispatchers.IO).launch {
                                Instance?.dao()?.deleteAll()
                                Instance?.dao()!!.insertAll(eightPuzzleList = eightPuzzleLists)
                            }
                        }
                    })
                    .fallbackToDestructiveMigration()
                    .build()
                    .also {
                        Instance = it
                    }
            }
        }
    }
}