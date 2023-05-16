package com.example.labyrinthpuzzle.screen

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
import com.example.labyrinthpuzzle.models.Eighttile
import com.example.labyrinthpuzzle.models.Memory
import com.example.labyrinthpuzzle.utils.InjectorUtils
import com.example.labyrinthpuzzle.viewModels.EightTilesPuzzleViewModel
import com.example.labyrinthpuzzle.viewModels.MemoryPuzzleViewModel
import com.example.labyrinthpuzzle.widgets.HomeTopAppBar
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
    Button(onClick = { navController.navigate(Screen.LabyrinthTileScreen.route) }) {
    Text(text = "Start", modifier = Modifier
        .width(100.dp)
        .padding(4.dp))
}
}