package com.example.labyrinthpuzzle.screen


import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun PuzzleScreen(navController: NavController = rememberNavController(), puzzleArchetypeId: String? = "1", puzzleInstanceId: String? = "1"){
    Column {
        when(puzzleArchetypeId){
            "1" -> EightTilePuzzleScreen(navController, puzzleInstanceId)
            else -> {}
        }
    }
}