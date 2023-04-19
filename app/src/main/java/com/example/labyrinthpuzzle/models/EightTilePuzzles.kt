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
        grid = listOf("6","0","8","3","7","2","5","1","4"),
        isSolved = false
    ))

fun getEightTilesPuzzle(id:Int): List<String>{
    return eightTiles.get(id).grid
}

fun getEightTilesPuzzles(): List<EightTile>{
    return eightTiles
}