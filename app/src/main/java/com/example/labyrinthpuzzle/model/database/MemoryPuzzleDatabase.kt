package com.example.labyrinthpuzzle.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.labyrinthpuzzle.model.dao.MemoryPuzzleDao
import com.example.labyrinthpuzzle.model.models.Memory
import com.example.labyrinthpuzzle.model.models.memoryList
import com.example.labyrinthpuzzle.model.utils.CustomConverters
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities = [Memory::class],
    version = 2,
    exportSchema = false
)
@TypeConverters(CustomConverters::class)
abstract class MemoryPuzzleDatabase : RoomDatabase() {
    abstract fun dao(): MemoryPuzzleDao

    companion object{
        @Volatile
        private var Instance: MemoryPuzzleDatabase? = null

        fun getDatabase(context: Context): MemoryPuzzleDatabase {
            return Instance ?: synchronized(this){
                Room.databaseBuilder(
                    context,
                    MemoryPuzzleDatabase::class.java,
                    "memoryPuzzle_db")
                    .addCallback(object : Callback(){
                        override fun onOpen(db: SupportSQLiteDatabase) {
                            super.onOpen(db)

                            CoroutineScope(Dispatchers.IO).launch {
                                Instance?.dao()!!.deleteAll()
                                Instance?.dao()!!.insertAll(memoryList = memoryList)
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