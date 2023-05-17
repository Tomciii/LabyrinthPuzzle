package com.example.labyrinthpuzzle.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.labyrinthpuzzle.model.utils.InjectorUtils
import com.example.labyrinthpuzzle.view.screen.Screen
import com.example.labyrinthpuzzle.view.widgets.HomeTopAppBar
import com.example.labyrinthpuzzle.viewModels.LabyrinthViewModel
import kotlin.system.exitProcess

@Composable
fun HomeScreen(navController: NavController = rememberNavController()){

    val viewModel: LabyrinthViewModel =
        viewModel(factory = InjectorUtils.provideLabyrinthViewModel(LocalContext.current))


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
            Button(onClick = {
                var labyrinthId = "1"
                navController.navigate("labyrinth/$labyrinthId")
            }) {
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