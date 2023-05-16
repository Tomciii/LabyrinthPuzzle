package com.example.labyrinthpuzzle.viewModels

import androidx.lifecycle.ViewModel
import com.example.labyrinthpuzzle.model.models.Eight
import com.example.labyrinthpuzzle.model.repository.EightTilePuzzleRepository

class EightTilesPuzzleViewModel(private val repository: EightTilePuzzleRepository) : ViewModel() {
    fun getEightTilePuzzleById(puzzleId: String): Eight {
        return repository.getEightTilePuzzlebyId(puzzleId.toInt())
        // repository.getEightTilePuzzlebyId(puzzleId.toInt())
    }

    fun convertListTo2DArray(list: List<String>): Array<Array<Int?>> {
        val matrix = Array(3) { arrayOfNulls<Int>(3) }

        for (i in 0 until 3) {
            for (j in 0 until 3) {
                matrix[i][j] = list[i * 3 + j].toInt()
            }
        }

        return matrix
    }

    suspend fun update(eightTile: Eight){
        repository.update(eightTile)
    }

    fun isPuzzleSolved(arr: Array<Array<Int?>>): Boolean {
        var expectedValue = 0
        for (i in 0 until arr.size) {
            for (j in 0 until arr[i].size) {
                if (arr[i][j] != expectedValue) {
                    return false
                }
                expectedValue++
            }
        }
        return true
    }
}