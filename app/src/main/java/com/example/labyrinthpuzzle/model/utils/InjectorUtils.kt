package com.example.labyrinthpuzzle.model.utils

import android.content.Context
import com.example.labyrinthpuzzle.model.database.AppDatabase
import com.example.labyrinthpuzzle.model.repository.EightTilePuzzleRepository
import com.example.labyrinthpuzzle.model.repository.LabyrinthRepository
import com.example.labyrinthpuzzle.model.repository.MemoryPuzzleRepository
import com.example.labyrinthpuzzle.viewModels.factories.EightTilesPuzzleViewModelFactory
import com.example.labyrinthpuzzle.viewModels.factories.LabyrinthViewModelFactory
import com.example.labyrinthpuzzle.viewModels.factories.MemoryPuzzleViewModelFactory

object InjectorUtils {

    private fun getEightPuzzleTileRepository(context: Context) : EightTilePuzzleRepository {
        return EightTilePuzzleRepository(AppDatabase.getDatabase(context).eightTilePuzzleDao())
    }

    fun provideEightTilePuzzleViewModel(context: Context) : EightTilesPuzzleViewModelFactory {
        val repository = getEightPuzzleTileRepository(context)
        return EightTilesPuzzleViewModelFactory(repository)
    }

    private fun getMemoryRepository(context: Context) : MemoryPuzzleRepository {
        return MemoryPuzzleRepository(AppDatabase.getDatabase(context).memoryPuzzleDao())
    }

    fun provideMemoryPuzzleViewModel(context: Context) : MemoryPuzzleViewModelFactory {
        val repository = getMemoryRepository(context)
        return MemoryPuzzleViewModelFactory(repository)
    }

    private fun getLabyrinthRepository(context: Context) : LabyrinthRepository {
        return LabyrinthRepository(AppDatabase.getDatabase(context).labyrinthTilePuzzleDao())
    }

    fun provideLabyrinthViewModel(context: Context) : LabyrinthViewModelFactory {
        val repository = getLabyrinthRepository(context)
        return LabyrinthViewModelFactory(repository)
    }

}