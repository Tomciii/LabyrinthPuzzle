package com.example.labyrinthpuzzle.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.labyrinthpuzzle.view.widgets.SimpleTopAppBar

@Composable
fun SettingsScreen(navController: NavController = rememberNavController()){
    Surface() {
        Column {
            SimpleTopAppBar(
                navController = navController) {
                Text(text = "Settings")
            }

            Text("Settings!")
        }
    }
}