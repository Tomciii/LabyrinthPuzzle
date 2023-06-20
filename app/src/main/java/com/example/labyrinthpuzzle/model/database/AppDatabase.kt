package com.example.labyrinthpuzzle.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.labyrinthpuzzle.model.dao.EightTilePuzzleDao
import com.example.labyrinthpuzzle.model.dao.LabyrinthDao
import com.example.labyrinthpuzzle.model.dao.MemoryPuzzleDao
import com.example.labyrinthpuzzle.model.entity.Eight
import com.example.labyrinthpuzzle.model.entity.LabyrinthTile
import com.example.labyrinthpuzzle.model.entity.Memory
import com.example.labyrinthpuzzle.model.utils.CustomConverters
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.util.*

@Database(
    entities = [Eight::class, Memory::class, LabyrinthTile::class],
    version = 10,
    exportSchema = false
)
@TypeConverters(CustomConverters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun eightTilePuzzleDao(): EightTilePuzzleDao
    abstract fun memoryPuzzleDao(): MemoryPuzzleDao
    abstract fun labyrinthTilePuzzleDao(): LabyrinthDao

    companion object{
        @Volatile
        private var Instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return Instance ?: synchronized(this) {
                val databaseBuilder = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "app_db"
                )
                    .fallbackToDestructiveMigration()

                val database = databaseBuilder.build()
                Instance = database

                // reads from application.properties
                val initializeDataFlag = readProperty(context)

                if (initializeDataFlag){ // false == keep date, true == reset
                    initializeData(database, context)
                }

                database
            }
        }



        private fun readProperty(context: Context): Boolean {
            try {
                val properties = Properties()
                val assetManager = context.assets
                val inputStream = assetManager.open("application.properties")
                properties.load(inputStream)
                return properties.getProperty("initialize_data", "true").toBoolean()
            } catch (e: IOException) {
                e.printStackTrace()
            }
            return true // Default value if the file read fails
        }

        private fun initializeData(database: AppDatabase, context: Context) {
            CoroutineScope(Dispatchers.IO).launch {

                database.eightTilePuzzleDao().deleteAll()
                database.labyrinthTilePuzzleDao().deleteAll()
                database.memoryPuzzleDao().deleteAll()

                try {
                    val inputStream = context.assets.open("insert.sql")
                    val bufferedReader = BufferedReader(InputStreamReader(inputStream))

                    var line: String?

                    while (bufferedReader.readLine().also { line = it } != null) {
                        if (!line.toString().startsWith("--") && !line.toString().isBlank())
                            database.openHelper.writableDatabase.execSQL(line.toString())
                    }
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }
    }
}

