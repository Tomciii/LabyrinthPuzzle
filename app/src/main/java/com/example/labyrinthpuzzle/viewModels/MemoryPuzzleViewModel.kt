package com.example.labyrinthpuzzle.viewModels

import androidx.lifecycle.ViewModel
import com.example.labyrinthpuzzle.model.models.Memory
import com.example.labyrinthpuzzle.model.repository.MemoryPuzzleRepository

class MemoryPuzzleViewModel(private val repository: MemoryPuzzleRepository) : ViewModel() {
    fun getMemoryPuzzleById(puzzleId: String): Memory {
       return repository.getMemoryPuzzlebyId(puzzleId.toInt())
    }
}