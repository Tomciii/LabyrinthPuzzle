package com.example.labyrinthpuzzle.repository

import com.example.labyrinthpuzzle.data.MemoryPuzzleDao
import com.example.labyrinthpuzzle.models.Memory

class MemoryPuzzleRepository(private val memoryPuzzleDao: MemoryPuzzleDao) {

    suspend fun update(memoryPuzzle: Memory) = memoryPuzzleDao.update(memoryPuzzle)
    fun getMemoryPuzzlebyId(id: Int) = memoryPuzzleDao.getMemoryPuzzleById(id)
}