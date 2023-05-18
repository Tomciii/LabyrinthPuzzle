package com.example.labyrinthpuzzle.model.repository

import com.example.labyrinthpuzzle.model.dao.LabyrinthDao
import com.example.labyrinthpuzzle.model.models.LabyrinthTile

class LabyrinthRepository(private val labyrinthDao: LabyrinthDao) {

    // fun getLabyrinthTileById(id: Int) = labyrinthDao.getLabyrinthById(id)

    fun getLabyrinthTileById(id: Int): LabyrinthTile {
        return labyrinthDao.getLabyrinthById(id)
    }

}