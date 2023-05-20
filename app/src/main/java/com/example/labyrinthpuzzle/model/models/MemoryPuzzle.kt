package com.example.labyrinthpuzzle.model.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Memory (
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var grid: List<String>, // [1,1,2,2,3,3] pairs of 2, 6 puzzle pieces in total
    var uncoveredState: List<String>, // [0,0,0,0,0,0] 0 = hidden, 1 = turned around
    var isSolved: Boolean
)