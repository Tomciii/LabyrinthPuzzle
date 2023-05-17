package com.example.labyrinthpuzzle.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.labyrinthpuzzle.widgets.HomeTopAppBar
import kotlin.system.exitProcess

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
    Column() {
        Row() {
            Button(onClick = { navController.navigate(Screen.LabyrinthScreen.route) }) {
                Text(text = "Start", modifier = Modifier
                    .width(100.dp)
                    .padding(4.dp))
            }
        }
        Row() {
            Button(onClick = { navController.navigate(Screen.HowToScreen.route) }) {
                Text(text = "How To", modifier = Modifier
                    .width(100.dp)
                    .padding(4.dp))

            }
        }
        Row() {
            Button(onClick = { navController.navigate(Screen.SettingsScreen.route) }) {
                Text(text = "Settings", modifier = Modifier
                    .width(100.dp)
                    .padding(4.dp))
            }
        }
        Row() {
            Button(onClick = {
                exitProcess(0)
            }) {
                Text(text = "Exit", modifier = Modifier
                    .width(100.dp)
                    .padding(4.dp))
            }
        }
    }
}