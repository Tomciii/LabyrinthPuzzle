package com.example.labyrinthpuzzle.screen

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.labyrinthpuzzle.view.widgets.SimpleTopAppBar

@Composable
fun HowToScreen(navController: NavController = rememberNavController()){
    SimpleTopAppBar(arrowBackClicked = { navController.popBackStack() }) {
        Text(text = "How To")
    }

    // How To kek
}