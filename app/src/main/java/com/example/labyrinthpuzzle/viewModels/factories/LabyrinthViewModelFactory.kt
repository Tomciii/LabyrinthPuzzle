package com.example.labyrinthpuzzle.viewModels.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.labyrinthpuzzle.persistence.repository.LabyrinthRepository
import com.example.labyrinthpuzzle.viewModels.LabyrinthViewModel

class LabyrinthViewModelFactory(private val repository: LabyrinthRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(LabyrinthViewModel::class.java)){
            return LabyrinthViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}