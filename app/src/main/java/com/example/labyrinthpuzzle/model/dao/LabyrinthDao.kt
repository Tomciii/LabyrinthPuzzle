package com.example.labyrinthpuzzle.model.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import com.example.labyrinthpuzzle.model.models.Labyrinth

@Dao
interface LabyrinthDao {



    @Update
    suspend fun update(labyrinth: Labyrinth)

    @Query("SELECT * FROM labyrinth where id=:id")
    fun getLabyrinthById(id: Int): Labyrinth            //should it get a tile?
}