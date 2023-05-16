package com.example.labyrinthpuzzle.model.repository

import com.example.labyrinthpuzzle.model.data.EightTilePuzzleDao
import com.example.labyrinthpuzzle.model.models.Eight

class EightTilePuzzleRepository(private val dao: EightTilePuzzleDao) {

    suspend fun update(eightPuzzle: Eight) = dao.update(eightPuzzle)
    fun getEightTilePuzzlebyId(id: Int) = dao.getEightTilePuzzleById(id)
}