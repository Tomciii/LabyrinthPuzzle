package com.example.labyrinthpuzzle.view.navigation

import android.content.Context
import android.content.res.Resources
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.labyrinthpuzzle.screen.HomeScreen
import com.example.labyrinthpuzzle.screen.HowToScreen
import com.example.labyrinthpuzzle.view.screen.LABYRINTH_TILE_ID
import com.example.labyrinthpuzzle.view.screen.Screen
import com.example.labyrinthpuzzle.screen.SettingsScreen
import com.example.labyrinthpuzzle.view.screen.*
@Composable
fun Navigation(resources: Resources, context: Context) {
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

        composable(route = Screen.ViewLabyrinthScreen.route){
            ViewLabyrinthScreen(navController = navController)
        }

        composable(
            route = Screen.PuzzleScreen.route,
            arguments = listOf(navArgument(name = PUZZLE_ARCHETYPE_ID) {type = NavType.StringType}, navArgument(name = PUZZLE_ID) {type = NavType.StringType})
        ){ backStackEntry ->    // backstack contains all information from navhost
            PuzzleScreen(
                navController = navController,
                backStackEntry.arguments?.getString(PUZZLE_ARCHETYPE_ID),
                backStackEntry.arguments?.getString(PUZZLE_ID))
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