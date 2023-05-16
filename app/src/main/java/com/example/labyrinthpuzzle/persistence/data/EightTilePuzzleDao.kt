package com.example.labyrinthpuzzle.persistence.data

import androidx.room.*
import com.example.labyrinthpuzzle.models.Eight

@Dao
interface EightTilePuzzleDao {
    @Insert
    suspend fun insertAll(eightPuzzleList: List<Eight>)
    @Update
    suspend fun update(eightPuzzle: Eight)

    @Query("SELECT * FROM eight")
    fun selectAll(): List<Eight>
    @Query("SELECT * FROM eight where id=:id")
    fun getEightTilePuzzleById(id: Int): Eight

}