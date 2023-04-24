package com.example.labyrinthpuzzle.repository

import com.example.labyrinthpuzzle.data.EightTilePuzzleDao
import com.example.labyrinthpuzzle.models.EightTile

class EightTilePuzzleRepository(private val eightTilePuzzleDao: EightTilePuzzleDao) {

    suspend fun update(eightTilePuzzle: EightTile) = eightTilePuzzleDao.update(eightTilePuzzle)
    fun getEightTilePuzzlebyId(id: Int) = eightTilePuzzleDao.getEightTilePuzzleById(id)
}