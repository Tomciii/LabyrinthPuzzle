package com.example.labyrinthpuzzle.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.labyrinthpuzzle.models.Eight
import com.example.labyrinthpuzzle.models.Memory
import com.example.labyrinthpuzzle.persistence.utils.InjectorUtils
import com.example.labyrinthpuzzle.viewModels.EightTilesPuzzleViewModel
import com.example.labyrinthpuzzle.viewModels.MemoryPuzzleViewModel
import com.example.labyrinthpuzzle.ui.widgets.HomeTopAppBar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Composable
fun HomeScreen(navController: NavController = rememberNavController()){
    Scaffold(topBar = {
        HomeTopAppBar(
            title = "Labyrinth Puzzle",
            menuContent = {}
        )
    }) { padding ->
        MainContent(modifier = Modifier.padding(padding), navController)
    }
}

@Composable
fun MainContent(
    modifier: Modifier,
    navController: NavController
) {

   // LocalContext.current.deleteDatabase("eightPuzzle_db")

    val viewModel: EightTilesPuzzleViewModel =
        viewModel(factory = InjectorUtils.provideEightTilePuzzleViewModel(LocalContext.current))

    val viewModel2: MemoryPuzzleViewModel =
        viewModel(factory = InjectorUtils.provideMemoryPuzzleViewModel(LocalContext.current))

    var eighttilePuzzle = Eight(0, listOf(),false)
    var eighttilePuzzle2 = Memory(0, listOf(),false)


        LaunchedEffect(Unit) {
            withContext(Dispatchers.IO){
                eighttilePuzzle = viewModel.getEightTilePuzzleById("0")
                eighttilePuzzle2 = viewModel2.getMemoryPuzzleById("1")
            }
        }


    Button(onClick = { navController.navigate(Screen.LabyrinthTileScreen.route) }) {
    Text(text = "Start", modifier = Modifier
        .width(100.dp)
        .padding(4.dp))
}
}