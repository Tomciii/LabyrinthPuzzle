package com.example.labyrinthpuzzle.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Eighttile (
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var grid: List<String>,
    var isSolved: Boolean
)

var eighttilePuzzleLists = listOf(
    Eighttile(
        id = 0,
        grid = listOf("1","0","2","3","4","5","6","7","8"),
        isSolved = false
    ),
    Eighttile(
        id = 1,
        grid = listOf("1","0","2","3","4","5","6","7","8"),
        isSolved = false
    ),
    Eighttile(
        id = 2,
        grid = listOf("1","0","2","3","4","5","6","7","8"),
        isSolved = false
    )
)
