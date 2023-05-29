package com.example.labyrinthpuzzle.model.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.labyrinthpuzzle.model.models.LabyrinthTile

@Dao
interface LabyrinthDao {

    @Query("DELETE FROM labyrinthtile")
    suspend fun deleteAll()
    @Insert
    suspend fun insertAll(labyrinth: List<LabyrinthTile>)
    @Insert
    suspend fun insert(labyrinth: LabyrinthTile)

    @Update
    suspend fun update(labyrinth: LabyrinthTile)

    @Query("SELECT * FROM labyrinthtile WHERE id=:id")
    fun getLabyrinthById(id: Int): LabyrinthTile?

    @Query("SELECT * FROM labyrinthtile")
    fun getAllTiles(): List<LabyrinthTile>

    @Query("SELECT id FROM labyrinthtile WHERE xCoordinate=:xCoordinate AND yCoordinate=:yCoordinate")
    fun getLabyrinthIdByCoordinates(xCoordinate: Int, yCoordinate: Int) : Int
}