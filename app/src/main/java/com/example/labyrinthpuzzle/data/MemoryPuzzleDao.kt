package com.example.labyrinthpuzzle.data

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import com.example.labyrinthpuzzle.models.Memory

@Dao
interface MemoryPuzzleDao {

    @Update
    suspend fun update(memoryPuzzle: Memory)

    @Query("SELECT * FROM memory where id=:id")
    fun getMemoryPuzzleById(id: Int): Memory

}