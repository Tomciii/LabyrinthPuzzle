package com.example.labyrinthpuzzle.persistence.repository

import com.example.labyrinthpuzzle.persistence.data.MemoryPuzzleDao
import com.example.labyrinthpuzzle.models.Memory

class MemoryPuzzleRepository(private val dao: MemoryPuzzleDao) {

    suspend fun update(memoryPuzzle: Memory) = dao.update(memoryPuzzle)
    fun getMemoryPuzzlebyId(id: Int) = dao.getMemoryPuzzleById(id)
}