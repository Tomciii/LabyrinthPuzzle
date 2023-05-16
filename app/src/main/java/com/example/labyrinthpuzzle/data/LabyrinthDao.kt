package com.example.labyrinthpuzzle.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.labyrinthpuzzle.models.Labyrinth
import com.example.labyrinthpuzzle.models.Memory

@Dao
interface LabyrinthDao {



    @Update
    suspend fun update(labyrinth: Labyrinth)

    @Query("SELECT * FROM labyrinth where id=:id")
    fun getLabyrinthById(id: Int): Labyrinth            //should it get a tile?
}