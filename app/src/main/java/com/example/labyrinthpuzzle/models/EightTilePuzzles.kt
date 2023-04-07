package com.example.labyrinthpuzzle.models

data class EightTile(
    var id: Int,
    var grid: Array<Array<Int>>
)

var eightTiles = listOf(
    EightTile(id = 1,
        grid = arrayOf(
            arrayOf(6, 0, 8),
            arrayOf(3, 7, 2),
            arrayOf(5, 1, 4))
))

fun getEightTiles(id:Int): Array<Array<Int>>{
    return eightTiles.get(id).grid
}