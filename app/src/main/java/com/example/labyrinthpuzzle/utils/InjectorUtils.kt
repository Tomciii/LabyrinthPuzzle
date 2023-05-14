package com.example.labyrinthpuzzle.utils

import android.content.Context
import com.example.labyrinthpuzzle.data.EightTilePuzzleDatabase
import com.example.labyrinthpuzzle.data.LabyrinthDatabase
import com.example.labyrinthpuzzle.data.MemoryPuzzleDatabase
import com.example.labyrinthpuzzle.repository.EightTilePuzzleRepository
import com.example.labyrinthpuzzle.repository.LabyrinthRepository
import com.example.labyrinthpuzzle.repository.MemoryPuzzleRepository
import com.example.labyrinthpuzzle.viewModels.factories.EightTilesPuzzleViewModelFactory
import com.example.labyrinthpuzzle.viewModels.factories.LabyrinthViewModelFactory
import com.example.labyrinthpuzzle.viewModels.factories.MemoryPuzzleViewModelFactory

object InjectorUtils {

    private fun getEightPuzzleTileRepository(context: Context) : EightTilePuzzleRepository{
        return EightTilePuzzleRepository(EightTilePuzzleDatabase.getDatabase(context).eightTilePuzzleDao())
    }

    fun provideEightTilePuzzleViewModel(context: Context) : EightTilesPuzzleViewModelFactory {
        val repository = getEightPuzzleTileRepository(context)
        return EightTilesPuzzleViewModelFactory(repository)
    }

    private fun getMemoryRepository(context: Context) : MemoryPuzzleRepository {
        return MemoryPuzzleRepository(MemoryPuzzleDatabase.getDatabase(context).memoryPuzzleDao())
    }

    fun provideMemoryPuzzleViewModel(context: Context) : MemoryPuzzleViewModelFactory {
        val repository = getMemoryRepository(context)
        return MemoryPuzzleViewModelFactory(repository)
    }

    private fun getLabyrinthRepository(context: Context) : LabyrinthRepository {
        return LabyrinthRepository(LabyrinthDatabase.getDatabase(context).labyrinthDao())
    }

    fun provideLabyrinthModel(context: Context) : LabyrinthViewModelFactory {
        val repository = getLabyrinthRepository(context)
        return LabyrinthViewModelFactory(repository)
    }

}