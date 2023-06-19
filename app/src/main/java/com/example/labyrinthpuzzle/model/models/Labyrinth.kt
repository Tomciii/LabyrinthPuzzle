package com.example.labyrinthpuzzle.model.models

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity
data class LabyrinthTile (
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var labyrinthID: Int,
    var xCoordinate: Int,
    var yCoordinate: Int,
    var puzzleArchetypeId: Int,
    var up: Int,
    var down: Int,
    var left: Int,
    var right: Int,
    var isUnlocked: Boolean
        )