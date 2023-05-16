package com.example.labyrinthpuzzle.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.labyrinthpuzzle.models.Eighttile
import com.example.labyrinthpuzzle.models.eighttilePuzzleLists
import com.example.labyrinthpuzzle.utils.CustomConverters
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities = [Eighttile::class],
    version = 2,
    exportSchema = false
)
@TypeConverters(CustomConverters::class)
abstract class EightTilePuzzleDatabase : RoomDatabase() {
    abstract fun eightTilePuzzleDao(): EightTilePuzzleDao

    companion object{
        @Volatile
        private var Instance: EightTilePuzzleDatabase? = null

        fun getDatabase(context: Context): EightTilePuzzleDatabase {
            return Instance?: synchronized(this){
                Room.databaseBuilder(
                    context,
                    EightTilePuzzleDatabase::class.java,
                    "eighttilePuzzle_db")
                    .addCallback(object : Callback(){
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)

                            CoroutineScope(Dispatchers.IO).launch {
                                Instance?.eightTilePuzzleDao()!!.insertAll(eighttilePuzzleList = eighttilePuzzleLists)
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