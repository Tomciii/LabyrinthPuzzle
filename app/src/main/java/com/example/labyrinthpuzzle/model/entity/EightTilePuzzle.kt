package com.example.labyrinthpuzzle.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Eight (
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var grid: List<String>,
    var isSolved: Boolean
)