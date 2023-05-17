package com.example.labyrinthpuzzle.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.labyrinthpuzzle.screen.*

@Composable
fun Navigation() {
    val navController = rememberNavController()
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

        composable(
            Screen.LabyrinthScreen.route,
            arguments = listOf(navArgument(name = LABYRINTH_TILE_ID) {type = NavType.StringType})
        ) { backStackEntry ->    // backstack contains all information from navhost
            LabyrinthScreen(
                navController = navController, backStackEntry.arguments?.getString(
                    LABYRINTH_TILE_ID))   // get the argument from navhost that will be passed
        }
    }
}