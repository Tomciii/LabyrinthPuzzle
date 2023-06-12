package com.example.labyrinthpuzzle.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.labyrinthpuzzle.view.screen.Screen
import com.example.labyrinthpuzzle.view.widgets.SimpleTopAppBar

@Composable
fun HowToScreen(navController: NavController = rememberNavController()){
   Surface() {
       Column {
           SimpleTopAppBar(
               navController = navController,
               firstMenuItemClicked = { navController.navigate(Screen.ViewLabyrinthScreen.route)},
               secondMenuItemClicked = { navController.navigate(Screen.HowToScreen.route)},
               arrowBackClicked = { navController.popBackStack() }) {
               Text(text = "How To")
           }

           Text("How To Play!")
       }
   }
}