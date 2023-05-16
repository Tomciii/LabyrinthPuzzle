package com.example.labyrinthpuzzle.model.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Eight (
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var grid: List<String>,
    var isSolved: Boolean
)

var eightPuzzleLists = listOf(
    Eight(
        id = 0,
        grid = listOf("1","0","2","3","4","5","6","7","8"),
        isSolved = false
    ),
    Eight(
        id = 1,
        grid = listOf("1","0","2","3","4","5","6","7","8"),
        isSolved = false
    ),
    Eight(
        id = 2,
        grid = listOf("1","0","2","3","4","5","6","7","8"),
        isSolved = false
    )
)
