package com.example.labyrinthpuzzle.view.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.labyrinthpuzzle.model.models.LabyrinthTile
import com.example.labyrinthpuzzle.model.utils.InjectorUtils
import com.example.labyrinthpuzzle.view.widgets.SimpleTopAppBar
import com.example.labyrinthpuzzle.viewModels.LabyrinthViewModel

@Composable
fun LabyrinthTileScreen(navController: NavController = rememberNavController(), labyrinthId: Int? = 1){

    var tile = MutableLiveData<LabyrinthTile?>().value

    Scaffold(topBar = {
        SimpleTopAppBar(arrowBackClicked = { navController.popBackStack() }){
            Text(text="Labyrinth")
        }
    }) { padding ->
        LabyrinthTile(tile, modifier = Modifier.padding(padding), navController)
    }
}

@Composable
fun LabyrinthTile(labyrinthTile: LabyrinthTile?, modifier: Modifier, navController: NavController) {

    var tiley by remember {
        mutableStateOf(labyrinthTile)
    }

    Button(onClick = { navController.navigate(Screen.PuzzleScreen.route) }) {
        Text(text = "EightTilePuzzle", modifier = Modifier
            .width(100.dp)
            .padding(4.dp))

    }


}