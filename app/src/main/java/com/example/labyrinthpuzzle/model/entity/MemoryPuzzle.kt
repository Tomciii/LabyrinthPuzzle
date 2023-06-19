package com.example.labyrinthpuzzle.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Memory (
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var grid: List<String>, // [1,1,2,2,3,3] pairs of 2, 6 puzzle pieces in total
    var isSolved: Boolean
)