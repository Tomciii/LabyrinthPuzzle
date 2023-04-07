package com.example.labyrinthpuzzle.screens

sealed class Screen (val route: String) {
    object HomeScreen : Screen("home")
    object LabyrinthScreen : Screen("labyrinth")
    object PuzzleScreen : Screen("puzzle")
}