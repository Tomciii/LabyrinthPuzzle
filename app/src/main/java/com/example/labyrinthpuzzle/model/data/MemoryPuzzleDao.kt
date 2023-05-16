package com.example.labyrinthpuzzle.model.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.labyrinthpuzzle.model.models.Memory

@Dao
interface MemoryPuzzleDao {

    @Insert
    suspend fun insertAll(memoryList: List<Memory>)

    @Update
    suspend fun update(memoryPuzzle: Memory)
    @Query("DELETE FROM memory")
    suspend fun deleteAll()
    @Query("SELECT * FROM memory where id=:id")
    fun getMemoryPuzzleById(id: Int): Memory

}