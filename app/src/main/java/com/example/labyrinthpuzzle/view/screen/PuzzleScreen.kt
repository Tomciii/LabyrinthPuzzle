package com.example.labyrinthpuzzle.view.screen


import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun PuzzleScreen(navController: NavController = rememberNavController(), puzzleArchetypeId: String? = "1", puzzleInstanceId: String? = "1"){

    when(puzzleArchetypeId){
        "1" -> EightTilePuzzleScreen(navController, puzzleInstanceId)
        "2" -> MemoryPuzzleScreen(navController, puzzleInstanceId)
        else -> {}
    }

}