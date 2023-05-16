package com.example.labyrinthpuzzle.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Memory (
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var grid: List<String>,
    var isSolved: Boolean
)

fun getMemoryPuzzleList(): List<Memory>{
    return listOf(
        Memory(
            id = 1,
            grid = listOf("0","0","1","1","2","2"),
            isSolved = false
        ),
        Memory(
            id = 2,
            grid = listOf("0","0","1","1","2","2"),
            isSolved = false
        ),
        Memory(
            id = 3,
            grid = listOf("0","0","1","1","2","2"),
            isSolved = false
        )
    )
}