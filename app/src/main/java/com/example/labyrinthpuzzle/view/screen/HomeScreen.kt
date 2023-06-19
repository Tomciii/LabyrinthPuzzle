package com.example.labyrinthpuzzle.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.labyrinthpuzzle.model.utils.InjectorUtils
import com.example.labyrinthpuzzle.view.screen.Screen
import com.example.labyrinthpuzzle.view.widgets.HomeScreenTopAppBar
import com.example.labyrinthpuzzle.viewModels.LabyrinthViewModel
import com.example.labyrinthpuzzle.view.theme.Purple100
import com.example.labyrinthpuzzle.view.widgets.HomeScreenMenuButton

@Composable
fun HomeScreen(navController: NavController = rememberNavController()){

    val viewModel: LabyrinthViewModel =
        viewModel(factory = InjectorUtils.provideLabyrinthViewModel(LocalContext.current))

    Scaffold(topBar = {
        HomeScreenTopAppBar(title = "Labyrinth Puzzle")
    }) { padding -> MainContent(modifier = Modifier.padding(padding), navController) }
}

@Composable
fun MainContent(
    modifier: Modifier,
    navController: NavController
) {
    Box(modifier
        .fillMaxSize()
        .background(color = Purple100)){
        Column(modifier = Modifier.align(Alignment.Center).padding(15.dp)) {

            HomeScreenMenuButton(navController, "Start", "labyrinth/1")

            HomeScreenMenuButton(navController, "How To", Screen.HowToScreen.route)

            HomeScreenMenuButton(navController, "View Labyrinth", Screen.ViewLabyrinthScreen.route)

            HomeScreenMenuButton(navController, "Settings", Screen.SettingsScreen.route)
        }
    }
}