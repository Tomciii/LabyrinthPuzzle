package com.example.labyrinthpuzzle.view.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.labyrinthpuzzle.model.utils.InjectorUtils
import com.example.labyrinthpuzzle.screen.HomeScreen
import com.example.labyrinthpuzzle.screen.HowToScreen
import com.example.labyrinthpuzzle.view.screen.LABYRINTH_TILE_ID
import com.example.labyrinthpuzzle.view.screen.Screen
import com.example.labyrinthpuzzle.screen.SettingsScreen
import com.example.labyrinthpuzzle.view.screen.*
import com.example.labyrinthpuzzle.viewModels.LabyrinthViewModel

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val labyrinthViewModel: LabyrinthViewModel = viewModel(factory = InjectorUtils.provideLabyrinthViewModel(LocalContext.current))
    NavHost(navController = navController, startDestination = Screen.HomeScreen.route) {
        composable(route = Screen.HomeScreen.route){
            HomeScreen(navController = navController)
        }

        composable(route = Screen.HowToScreen.route){
            HowToScreen(navController = navController)
        }

        composable(route = Screen.SettingsScreen.route){
            SettingsScreen(navController = navController)
        }

        composable(route = Screen.PuzzleScreen.route){
            EightTilePuzzleScreen(navController = navController)
        }

        composable(route = Screen.ViewLabyrinthScreen.route){
            ViewLabyrinthScreen(navController = navController)
        }

        composable(route = Screen.PuzzleScreen.route){
            EightTilePuzzleScreen(navController = navController)
        }

        composable(
            Screen.LabyrinthTileScreen.route,
            arguments = listOf(navArgument(name = LABYRINTH_TILE_ID) {type = NavType.IntType})
        ) { backStackEntry ->    // backstack contains all information from navhost
            LabyrinthTileScreen(
                navController = navController, backStackEntry.arguments?.getInt(
                    LABYRINTH_TILE_ID
                ))   // get the argument from navhost that will be passed
        }
    }
}