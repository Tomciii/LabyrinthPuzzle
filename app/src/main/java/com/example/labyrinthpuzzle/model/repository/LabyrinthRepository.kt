package com.example.labyrinthpuzzle.model.repository

import com.example.labyrinthpuzzle.model.dao.LabyrinthDao
import com.example.labyrinthpuzzle.model.entity.LabyrinthTile

class LabyrinthRepository(private val dao: LabyrinthDao) {

    // fun getLabyrinthTileById(id: Int) = labyrinthDao.getLabyrinthById(id)

    suspend fun update(labyrinthTile: LabyrinthTile) = dao.update(labyrinthTile)

    fun getLabyrinthTileById(id: Int): LabyrinthTile? {
        return dao.getLabyrinthById(id)
    }

    suspend fun reset() = dao.reset()

    suspend fun unlockLabyrinthTile(id: Int) = dao.unlockLabyrinthTile(id)

    fun getAllTiles (): List<LabyrinthTile> {
        return dao.getAllTiles()
    }

    fun getLabyrinthTileIdByCoordiantes(xCoordinates: Int, yCoordinates: Int) : Int {
        return dao.getLabyrinthIdByCoordinates(xCoordinates, yCoordinates)
    }
}