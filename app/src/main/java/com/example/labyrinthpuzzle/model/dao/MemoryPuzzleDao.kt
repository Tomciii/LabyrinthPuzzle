package com.example.labyrinthpuzzle.model.dao

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

    @Query("SELECT isSolved FROM memory where id=:id")
    fun isSolved(id: Int): Boolean

    @Query("UPDATE memory SET isSolved = 1 where id=:id")
    fun updateToSolved(id: Int)

}