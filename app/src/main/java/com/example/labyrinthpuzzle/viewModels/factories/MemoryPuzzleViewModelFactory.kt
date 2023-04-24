package com.example.labyrinthpuzzle.viewModels.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.labyrinthpuzzle.repository.MemoryPuzzleRepository
import com.example.labyrinthpuzzle.viewModels.MemoryPuzzleViewModel

class MemoryPuzzleViewModelFactory(private val repository: MemoryPuzzleRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MemoryPuzzleViewModel::class.java)){
            return MemoryPuzzleViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}