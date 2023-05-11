package com.example.labyrinthpuzzle.models


//TODO: Discuss and test if this is a good idea
data class Labyrinth (
    var labyrinthId: Int,
    var labyrinthGrid: Array<Array<LabyrinthTile?>>,
    var isSolved: Boolean
)

data class LabyrinthTile (
    var labyrinthTileId: Int,
    var puzzleArchetypeId: Int,
    var up: Node,
    var down: Node,
    var left: Node,
    var right: Node
        )

data class Node(
    var isSolved: Boolean,
    var id: Int
)

fun createLabyrinth(): Array<Array<LabyrinthTile?>> {
    val rowCount = 7
    val columnCount = 4

    return Array(rowCount) {
        arrayOfNulls(columnCount)
    }
}