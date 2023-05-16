package com.example.labyrinthpuzzle.viewModels.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.labyrinthpuzzle.persistence.repository.EightTilePuzzleRepository
import com.example.labyrinthpuzzle.viewModels.EightTilesPuzzleViewModel

class EightTilesPuzzleViewModelFactory(private val repository: EightTilePuzzleRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(EightTilesPuzzleViewModel::class.java)){
            return EightTilesPuzzleViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}