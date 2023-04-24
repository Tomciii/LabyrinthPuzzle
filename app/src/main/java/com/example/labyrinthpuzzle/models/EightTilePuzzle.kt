package com.example.labyrinthpuzzle.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class EightTile (
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var grid: List<String>,
    var isSolved: Boolean
)

var eightTiles = listOf(
    EightTile(
        id = 1,
        grid = listOf("1","0","2","3","4","5","6","7","8"),
        isSolved = false
    ),
    EightTile(
        id = 2,
        grid = listOf("3","1","2","4","0","5","6","7","8"),
        isSolved = false
    ),
    EightTile(
        id = 3,
        grid = listOf("1","2","3","0","4","5","6","7","8"),
        isSolved = false
    )
)

fun getEightTilesPuzzle(id:Int): List<String>{
    return eightTiles.get(id).grid
}

fun getEightTilesPuzzles(): List<EightTile>{
    return eightTiles
}