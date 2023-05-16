package com.example.labyrinthpuzzle.model.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Memory (
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var grid: List<String>,
    var isSolved: Boolean
)

var memoryList = listOf(
    Memory(
        id = 0,
        grid = listOf("0","0","1","1","2","2"),
        isSolved = false
    ),
    Memory(
        id = 1,
        grid = listOf("0","0","1","1","2","2"),
        isSolved = false
    ),
    Memory(
        id = 2,
        grid = listOf("0","0","1","1","2","2"),
        isSolved = false
    )
)