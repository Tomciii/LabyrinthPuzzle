package com.example.labyrinthpuzzle.model.repository

import com.example.labyrinthpuzzle.model.dao.LabyrinthDao
import com.example.labyrinthpuzzle.model.entity.LabyrinthTile

class LabyrinthRepository(private val labyrinthDao: LabyrinthDao) {

    // fun getLabyrinthTileById(id: Int) = labyrinthDao.getLabyrinthById(id)

    suspend fun update(labyrinthTile: LabyrinthTile) = labyrinthDao.update(labyrinthTile)

    fun getLabyrinthTileById(id: Int): LabyrinthTile? {
        return labyrinthDao.getLabyrinthById(id)
    }

    fun getAllTiles (): List<LabyrinthTile> {
        return labyrinthDao.getAllTiles()
    }

    fun getLabyrinthTileIdByCoordiantes(xCoordinates: Int, yCoordinates: Int) : Int {
        return labyrinthDao.getLabyrinthIdByCoordinates(xCoordinates, yCoordinates)
    }
}