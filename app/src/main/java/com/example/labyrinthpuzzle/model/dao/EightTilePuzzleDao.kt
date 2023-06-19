package com.example.labyrinthpuzzle.model.dao

import androidx.room.*
import com.example.labyrinthpuzzle.model.entity.Eight

@Dao
interface EightTilePuzzleDao {
    @Insert
    suspend fun insertAll(eightPuzzleList: List<Eight>)
    @Update
    suspend fun update(eightPuzzle: Eight)

    @Query("DELETE FROM eight")
    suspend fun deleteAll()

    @Query("SELECT * FROM eight")
    fun selectAll(): List<Eight>
    @Query("SELECT * FROM eight where id=:id")
    fun getEightTilePuzzleById(id: Int): Eight?

    @Query("SELECT isSolved FROM eight where id=:id")
    fun isSolved(id: Int): Boolean

}