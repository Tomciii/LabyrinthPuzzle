package com.example.labyrinthpuzzle.data

import androidx.room.*
import com.example.labyrinthpuzzle.models.EightTile

@Dao
interface EightTilePuzzleDao {

    @Update
    suspend fun update(eightTilePuzzle: EightTile)

    @Query("SELECT * FROM eightTile where id=:id")
    fun getEightTilePuzzleById(id: Int): EightTile
}