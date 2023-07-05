package com.example.labyrinthpuzzle.model.repository

import com.example.labyrinthpuzzle.model.dao.MemoryPuzzleDao
import com.example.labyrinthpuzzle.model.entity.Memory

class MemoryPuzzleRepository(private val dao: MemoryPuzzleDao) {

    suspend fun update(memoryPuzzle: Memory) = dao.update(memoryPuzzle)

    fun getMemoryPuzzlebyId(id: Int) = dao.getMemoryPuzzleById(id)

    fun isSolved(id: Int) = dao.isSolved(id)

    suspend fun reset() = dao.reset()
    fun updateToSolved(id: Int) = dao.updateToSolved(id)
}