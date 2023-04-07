package com.example.labyrinthpuzzle.screens

sealed class Screen (val route: String) {
    object MainScreen : Screen("main")
}