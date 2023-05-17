package com.example.labyrinthpuzzle.model.utils

import androidx.room.TypeConverter

class CustomConverters {
    @TypeConverter
    fun listToString(value: List<String>) = value.joinToString(",")

    @TypeConverter
    fun stringToList(value: String) = value.split(",").map{ it.trim()}
}