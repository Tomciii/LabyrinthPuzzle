package com.example.labyrinthpuzzle.viewModels

import androidx.lifecycle.ViewModel
import com.example.labyrinthpuzzle.model.entity.Memory
import com.example.labyrinthpuzzle.model.repository.MemoryPuzzleRepository

class MemoryPuzzleViewModel(private val repository: MemoryPuzzleRepository) : ViewModel() {
    fun getMemoryPuzzleById(puzzleId: String): Memory {
       return repository.getMemoryPuzzlebyId(puzzleId.toInt())
    }

    fun isPuzzleSolved(id: Int): Boolean {
        return repository.isSolved(id)
    }

    fun isPuzzleInCorrectOrder(arr: Array<Int?>): Boolean {
       return arr[0] == 1 && arr[1] == 1 && arr[2] == 1 && arr[3] == 1 && arr[4] == 1 && arr[5] == 1
    }

    suspend fun reset(){
        repository.reset()
    }

    suspend fun update(memory: Memory){
        repository.update(memory)
    }
}