package com.example.labyrinthpuzzle.persistence.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.labyrinthpuzzle.models.Memory

@Dao
interface MemoryPuzzleDao {

    @Insert
    suspend fun insertAll(memoryList: List<Memory>)

    @Update
    suspend fun update(memoryPuzzle: Memory)

    @Query("SELECT * FROM memory where id=:id")
    fun getMemoryPuzzleById(id: Int): Memory

}