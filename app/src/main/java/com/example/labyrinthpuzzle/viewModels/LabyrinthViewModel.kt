package com.example.labyrinthpuzzle.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.labyrinthpuzzle.model.models.Eight
import com.example.labyrinthpuzzle.model.models.LabyrinthTile
import com.example.labyrinthpuzzle.model.repository.LabyrinthRepository

class LabyrinthViewModel(private val repository: LabyrinthRepository): ViewModel() {

    private var labyrinthTile = MutableLiveData<LabyrinthTile>()                                   //to observe changes to the labyrinth tile by observing the labyrinthTile live data

    fun getLabyrinthTileById(id: Int): LabyrinthTile? {
        return repository.getLabyrinthTileById(id)
    }

    suspend fun update(labyrinthTile: LabyrinthTile){
        repository.update(labyrinthTile)
    }

    /* fun loadLabyrinthTileById(id: Int) {
        val tile = repository.getLabyrinthTileById(id)
        labyrinthTile.value = tile
    } */
}