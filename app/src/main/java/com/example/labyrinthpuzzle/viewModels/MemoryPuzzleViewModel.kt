package com.example.labyrinthpuzzle.viewModels

import androidx.lifecycle.ViewModel
import com.example.labyrinthpuzzle.models.EightTile
import com.example.labyrinthpuzzle.models.Memory
import com.example.labyrinthpuzzle.models.getEightTilesPuzzleList
import com.example.labyrinthpuzzle.models.getMemoryPuzzleList
import com.example.labyrinthpuzzle.repository.MemoryPuzzleRepository

class MemoryPuzzleViewModel(private val repository: MemoryPuzzleRepository) : ViewModel() {
    fun getMemoryPuzzleById(puzzleId: String): Memory {
        return getMemoryPuzzleList().get(puzzleId.toInt())
    }
}