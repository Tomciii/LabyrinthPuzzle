package com.example.labyrinthpuzzle.repository

import com.example.labyrinthpuzzle.data.EightTilePuzzleDao

class EightTilePuzzleRepository(private val eightTilePuzzleDao: EightTilePuzzleDao) {

    fun getEightTilePuzzlebyId(id: Int) = eightTilePuzzleDao.getEightTilePuzzleById(id)
}