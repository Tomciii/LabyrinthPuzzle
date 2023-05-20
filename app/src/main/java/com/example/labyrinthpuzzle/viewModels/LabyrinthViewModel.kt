package com.example.labyrinthpuzzle.viewModels

import androidx.lifecycle.ViewModel
import com.example.labyrinthpuzzle.model.models.LabyrinthTile
import com.example.labyrinthpuzzle.model.repository.LabyrinthRepository

class LabyrinthViewModel(private val repository: LabyrinthRepository): ViewModel() {
    private val rows = 7
    private val columns = 5

    fun getLabyrinthTileById(id: Int): LabyrinthTile? {
        return repository.getLabyrinthTileById(id)
    }

    suspend fun update(labyrinthTile: LabyrinthTile){
        repository.update(labyrinthTile)
    }

    fun getAllTiles (): Array<Array<LabyrinthTile?>> {
        val list = repository.getAllTiles()

        val tileArray = Array(rows) { row ->
            Array(columns) { column ->
                //if tile is in list map its x-coord to col & y-coord to row, if null take current row & col as coords
                list.find { it.xCoordinate == column && it.yCoordinate == row }
                    ?.copy(xCoordinate = column, yCoordinate = row)
            }
        }

        return tileArray
    }

    //only for testing
    fun printArray() {
        val array = getAllTiles()
        for (row in array) {
            for (tile in row) {
                print("${tile?.id ?: "-"} ") // Print the ID of the tile, or "-" if it's null
            }
            println()
        }
    }
}