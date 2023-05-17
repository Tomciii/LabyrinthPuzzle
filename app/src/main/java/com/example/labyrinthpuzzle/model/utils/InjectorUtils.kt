package com.example.labyrinthpuzzle.model.utils

import android.content.Context
import com.example.labyrinthpuzzle.model.database.EightTilePuzzleDatabase
import com.example.labyrinthpuzzle.model.database.LabyrinthDatabase
import com.example.labyrinthpuzzle.model.database.MemoryPuzzleDatabase
import com.example.labyrinthpuzzle.model.repository.EightTilePuzzleRepository
import com.example.labyrinthpuzzle.model.repository.LabyrinthRepository
import com.example.labyrinthpuzzle.model.repository.MemoryPuzzleRepository
import com.example.labyrinthpuzzle.viewModels.factories.EightTilesPuzzleViewModelFactory
import com.example.labyrinthpuzzle.viewModels.factories.LabyrinthViewModelFactory
import com.example.labyrinthpuzzle.viewModels.factories.MemoryPuzzleViewModelFactory

object InjectorUtils {

    private fun getEightPuzzleTileRepository(context: Context) : EightTilePuzzleRepository {
        return EightTilePuzzleRepository(EightTilePuzzleDatabase.getDatabase(context).dao())
    }

    fun provideEightTilePuzzleViewModel(context: Context) : EightTilesPuzzleViewModelFactory {
        val repository = getEightPuzzleTileRepository(context)
        return EightTilesPuzzleViewModelFactory(repository)
    }

    private fun getMemoryRepository(context: Context) : MemoryPuzzleRepository {
        return MemoryPuzzleRepository(MemoryPuzzleDatabase.getDatabase(context).dao())
    }

    fun provideMemoryPuzzleViewModel(context: Context) : MemoryPuzzleViewModelFactory {
        val repository = getMemoryRepository(context)
        return MemoryPuzzleViewModelFactory(repository)
    }

    private fun getLabyrinthRepository(context: Context) : LabyrinthRepository {
        return LabyrinthRepository(LabyrinthDatabase.getDatabase(context).dao())
    }

    fun provideLabyrinthModel(context: Context) : LabyrinthViewModelFactory {
        val repository = getLabyrinthRepository(context)
        return LabyrinthViewModelFactory(repository)
    }

}