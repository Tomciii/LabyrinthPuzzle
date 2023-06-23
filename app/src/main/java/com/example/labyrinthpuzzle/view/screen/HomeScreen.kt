package com.example.labyrinthpuzzle.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.labyrinthpuzzle.view.screen.Screen
import com.example.labyrinthpuzzle.view.theme.*
import com.example.labyrinthpuzzle.view.widgets.HomeScreenTopAppBar
import com.example.labyrinthpuzzle.view.widgets.HomeScreenMenuButton

@Composable
fun HomeScreen(navController: NavController = rememberNavController()){

    Scaffold(topBar = {
        HomeScreenTopAppBar(title = LABYRINTH_PUZZLE)
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

            HomeScreenMenuButton(navController, START, "labyrinth/1")

            HomeScreenMenuButton(navController, HOW_TO_PLAY, Screen.HowToScreen.route)

            HomeScreenMenuButton(navController, VIEW_LABYRINTH, Screen.ViewLabyrinthScreen.route)

            HomeScreenMenuButton(navController, SETTINGS, Screen.SettingsScreen.route)
        }
    }
}