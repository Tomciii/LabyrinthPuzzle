package com.example.labyrinthpuzzle.viewModels

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.example.labyrinthpuzzle.models.EightTile
import com.example.labyrinthpuzzle.models.getEightTilesPuzzles
import com.example.labyrinthpuzzle.repository.EightTilePuzzleRepository

class EightTilesPuzzleViewModel(private val repository: EightTilePuzzleRepository) : ViewModel() {
    fun getEightTile(puzzleId: String): EightTile {
        return getEightTilesPuzzles().get(puzzleId.toInt())
    }

    private val _eightTileList = getEightTilesPuzzles().toMutableStateList()
    val eightTiles: List<EightTile>
        get() = _eightTileList
    fun getEightTilesPuzzleById(id:Int) : Array<Array<Int?>>{
       return convertListTo2DArray(repository.getEightTilePuzzlebyId(id).grid)
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

    fun isSequenceComplete(arr: Array<Array<Int?>>): Boolean {
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