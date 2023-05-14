package com.example.labyrinthpuzzle.models

import androidx.room.Entity
import androidx.room.PrimaryKey
//TODO: Discuss and test if this is a good idea
//TODO: Converter for labyrinthGrid
@Entity
data class Labyrinth (
    @PrimaryKey(autoGenerate = true)
    var id: Int,
   // var labyrinthGrid: Array<Array<LabyrinthTile?>>,
    var isSolved: Boolean
)

data class LabyrinthTile (
    var id: Int,
    var puzzleArchetypeId: Int,
    var up: Node,
    var down: Node,
    var left: Node,
    var right: Node
        )

// I guess id 99 = back button idk
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