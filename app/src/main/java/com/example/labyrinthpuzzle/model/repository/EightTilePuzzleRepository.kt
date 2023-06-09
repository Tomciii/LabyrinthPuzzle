package com.example.labyrinthpuzzle.model.repository

import com.example.labyrinthpuzzle.model.dao.EightTilePuzzleDao
import com.example.labyrinthpuzzle.model.entity.Eight

class EightTilePuzzleRepository(private val dao: EightTilePuzzleDao) {

    suspend fun update(eightPuzzle: Eight) = dao.update(eightPuzzle)
    fun getEightTilePuzzlebyId(id: Int) = dao.getEightTilePuzzleById(id)

    suspend fun reset() = dao.reset()
    fun isSolved(id: Int) = dao.isSolved(id)
}