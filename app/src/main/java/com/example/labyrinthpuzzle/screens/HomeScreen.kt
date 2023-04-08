package com.example.labyrinthpuzzle.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.labyrinthpuzzle.widgets.HomeTopAppBar

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

}