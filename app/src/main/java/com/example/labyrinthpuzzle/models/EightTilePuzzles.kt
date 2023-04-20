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
        grid = listOf("0","1","2","3","4","5","6","7","8"),
        isSolved = false
    ))

fun getEightTilesPuzzle(id:Int): List<String>{
    return eightTiles.get(id).grid
}

fun getEightTilesPuzzles(): List<EightTile>{
    return eightTiles
}