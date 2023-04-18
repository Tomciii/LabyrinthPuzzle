package com.example.labyrinthpuzzle.data

import androidx.room.Dao
import androidx.room.Update
import com.example.labyrinthpuzzle.models.EightTile

@Dao
interface EightTilePuzzleDao {
    @Update
    suspend fun update(eightTilePuzzle: EightTile)
    abstract fun getEightTilePuzzleById(id: Int): EightTile
}