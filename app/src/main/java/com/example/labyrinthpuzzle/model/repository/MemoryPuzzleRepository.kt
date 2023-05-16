package com.example.labyrinthpuzzle.model.repository

import com.example.labyrinthpuzzle.model.data.MemoryPuzzleDao
import com.example.labyrinthpuzzle.model.models.Memory

class MemoryPuzzleRepository(private val dao: MemoryPuzzleDao) {

    suspend fun update(memoryPuzzle: Memory) = dao.update(memoryPuzzle)
    fun getMemoryPuzzlebyId(id: Int) = dao.getMemoryPuzzleById(id)
}