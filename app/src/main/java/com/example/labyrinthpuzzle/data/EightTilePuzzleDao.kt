package com.example.labyrinthpuzzle.data

import androidx.room.*
import com.example.labyrinthpuzzle.models.Eighttile

@Dao
interface EightTilePuzzleDao {
    @Insert
    suspend fun insertAll(eighttilePuzzleList: List<Eighttile>)
    @Update
    suspend fun update(eighttilePuzzle: Eighttile)

    @Query("SELECT * FROM eighttile")
    fun selectAll(): List<Eighttile>
    @Query("SELECT * FROM eighttile where id=:id")
    fun getEightTilePuzzleById(id: Int): Eighttile

}