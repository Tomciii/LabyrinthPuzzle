package com.example.labyrinthpuzzle.utils

import android.content.Context
import com.example.labyrinthpuzzle.data.EightTilePuzzleDatabase
import com.example.labyrinthpuzzle.data.MemoryPuzzleDatabase
import com.example.labyrinthpuzzle.repository.EightTilePuzzleRepository
import com.example.labyrinthpuzzle.repository.MemoryPuzzleRepository
import com.example.labyrinthpuzzle.viewModels.factories.EightTilesPuzzleViewModelFactory
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
}