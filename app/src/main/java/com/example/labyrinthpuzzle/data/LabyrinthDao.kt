package com.example.labyrinthpuzzle.data

import androidx.room.Query
import androidx.room.Update
import com.example.labyrinthpuzzle.models.Labyrinth

interface LabyrinthDao {

    @Update
    suspend fun update(labyrinth: Labyrinth)

    @Query("SELECT * FROM labyrinth where id=:id")
    fun getLabyrinthTileById(id: Int): Labyrinth            //should it get a tile?
}