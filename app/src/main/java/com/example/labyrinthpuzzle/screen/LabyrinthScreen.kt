package com.example.labyrinthpuzzle.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.labyrinthpuzzle.widgets.HomeTopAppBar
import com.example.labyrinthpuzzle.widgets.SimpleTopAppBar

@Composable
fun LabyrinthScreen(navController: NavController = rememberNavController(), labyrinthId: String? = "1"){
    Scaffold(topBar = {
        SimpleTopAppBar(arrowBackClicked = { navController.popBackStack() }){
            Text(text="Labyrinth")
        }
    }) { padding ->
        Labyrinth(modifier = Modifier.padding(padding), navController)
    }
}

@Composable
fun Labyrinth(
    modifier: Modifier,
    navController: NavController
) {
    Button(onClick = { navController.navigate(Screen.PuzzleScreen.route) }) {
        Text(text = "EightTilePuzzle", modifier = Modifier
            .width(100.dp)
            .padding(4.dp))

    }
}