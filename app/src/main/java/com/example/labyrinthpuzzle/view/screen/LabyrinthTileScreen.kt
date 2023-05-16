package com.example.labyrinthpuzzle.view.screen

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
import com.example.labyrinthpuzzle.view.widgets.SimpleTopAppBar

@Composable
fun LabyrinthTileScreen(navController: NavController = rememberNavController(), labyrinthId: String? = "1"){
    Scaffold(topBar = {
        SimpleTopAppBar(arrowBackClicked = { navController.popBackStack() }){
            Text(text="Labyrinth")
        }
    }) { padding ->
        LabyrinthTile(modifier = Modifier.padding(padding), navController)
    }
}

@Composable
fun LabyrinthTile(
    modifier: Modifier,
    navController: NavController
) {
    Button(onClick = { navController.navigate(Screen.PuzzleScreen.route) }) {
        Text(text = "EightTilePuzzle", modifier = Modifier
            .width(100.dp)
            .padding(4.dp))

    }
}