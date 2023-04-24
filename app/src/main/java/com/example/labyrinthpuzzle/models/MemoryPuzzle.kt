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