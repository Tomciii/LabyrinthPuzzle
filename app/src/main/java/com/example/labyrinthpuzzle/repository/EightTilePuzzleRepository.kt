package com.example.labyrinthpuzzle.repository

import com.example.labyrinthpuzzle.data.EightTilePuzzleDao
import com.example.labyrinthpuzzle.models.Eighttile

class EightTilePuzzleRepository(private val eightTilePuzzleDao: EightTilePuzzleDao) {

    suspend fun update(eighttilePuzzle: Eighttile) = eightTilePuzzleDao.update(eighttilePuzzle)
    fun getEightTilePuzzlebyId(id: Int) = eightTilePuzzleDao.getEightTilePuzzleById(id)
}