package com.example.labyrinthpuzzle.utils

import android.content.Context
import com.example.labyrinthpuzzle.data.EightTilePuzzleDatabase
import com.example.labyrinthpuzzle.repository.EightTilePuzzleRepository
import com.example.labyrinthpuzzle.viewModels.factories.EightTilesPuzzleViewModelFactory

object InjectorUtils {

    private fun getEightPuzzleTileRepository(context: Context) : EightTilePuzzleRepository{
        return EightTilePuzzleRepository(EightTilePuzzleDatabase.getDatabase(context).eightTilePuzzleDao())
    }

    fun provideEightTilePuzzleViewModel(context: Context) : EightTilesPuzzleViewModelFactory {
        val repository = getEightPuzzleTileRepository(context)
        return EightTilesPuzzleViewModelFactory(repository)
    }
}