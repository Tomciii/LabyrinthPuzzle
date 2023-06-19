package com.example.labyrinthpuzzle.screen

import com.example.labyrinthpuzzle.view.theme.SETTINGS
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.labyrinthpuzzle.view.theme.Purple100
import com.example.labyrinthpuzzle.view.widgets.SimpleTopAppBar

@Composable
fun SettingsScreen(navController: NavController = rememberNavController()){
    Surface(modifier = Modifier.fillMaxSize(), color = Purple100) {
        Column {
            SimpleTopAppBar(
                navController = navController) {
                Text(text = SETTINGS)
            }

            Text("Settings!")
        }
    }
}