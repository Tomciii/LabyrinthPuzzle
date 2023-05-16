package com.example.labyrinthpuzzle.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.labyrinthpuzzle.models.Memory
import com.example.labyrinthpuzzle.models.memoryList
import com.example.labyrinthpuzzle.utils.CustomConverters
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities = [Memory::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(CustomConverters::class)
abstract class MemoryPuzzleDatabase : RoomDatabase() {
    abstract fun dao(): MemoryPuzzleDao

    companion object{
        @Volatile
        private var Instance: MemoryPuzzleDatabase? = null

        fun getDatabase(context: Context): MemoryPuzzleDatabase {
            return Instance?: synchronized(this){
                Room.databaseBuilder(
                    context,
                    MemoryPuzzleDatabase::class.java,
                    "memoryPuzzle_db")
                    .addCallback(object : Callback(){
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)

                            CoroutineScope(Dispatchers.IO).launch {
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