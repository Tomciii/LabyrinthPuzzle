package com.example.labyrinthpuzzle.viewModels

import androidx.lifecycle.ViewModel
import com.example.labyrinthpuzzle.model.entity.LabyrinthTile
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

    fun getPreviousTileIdByCoordinates(xCoordinates: Int, yCoordinates: Int) : Int {
        return repository.getLabyrinthTileIdByCoordiantes(xCoordinates, yCoordinates)
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

    suspend fun unlockLabyrinthTile(id: Int){
        repository.unlockLabyrinthTile(id)
    }

    fun getAllLabyrinthTiles(): List<LabyrinthTile?> {
        return repository.getAllTiles()
    }

    /* fun loadLabyrinthTileById(id: Int) {
        val tile = repository.getLabyrinthTileById(id)
        labyrinthTile.value = tile
    } */
    companion object
}