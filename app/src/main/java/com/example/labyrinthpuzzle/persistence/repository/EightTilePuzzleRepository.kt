package com.example.labyrinthpuzzle.persistence.repository

import com.example.labyrinthpuzzle.persistence.data.EightTilePuzzleDao
import com.example.labyrinthpuzzle.models.Eight

class EightTilePuzzleRepository(private val dao: EightTilePuzzleDao) {

    suspend fun update(eightPuzzle: Eight) = dao.update(eightPuzzle)
    fun getEightTilePuzzlebyId(id: Int) = dao.getEightTilePuzzleById(id)
}