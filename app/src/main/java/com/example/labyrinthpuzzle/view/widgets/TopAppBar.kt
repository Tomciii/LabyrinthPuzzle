package com.example.labyrinthpuzzle.view.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.labyrinthpuzzle.view.screen.Screen

@Composable
fun SimpleTopAppBar(
    navController: NavController,
    menuContent: @Composable () -> Unit = {
        DropdownMenuItem(onClick = { navController.navigate(Screen.ViewLabyrinthScreen.route) }) {
            Row {
                Icon(imageVector = Icons.Default.Search, contentDescription = "View Labyrinth", modifier = Modifier.padding(4.dp))
                Text(text = "View Labyrinth", modifier = Modifier
                    .width(100.dp)
                    .padding(4.dp))
            }
        }
        DropdownMenuItem(onClick = { navController.navigate(Screen.HowToScreen.route) }) {
            Row {
                Icon(imageVector = Icons.Default.Info, contentDescription = "How To Play", modifier = Modifier.padding(4.dp))
                Text(text = "How To Play", modifier = Modifier
                    .width(100.dp)
                    .padding(4.dp))
            }
        }
        DropdownMenuItem(onClick = { navController.navigate(Screen.SettingsScreen.route) }) {
            Row {
                Icon(imageVector = Icons.Default.Settings, contentDescription = "Settings", modifier = Modifier.padding(4.dp))
                Text(text = "Settings", modifier = Modifier
                    .width(100.dp)
                    .padding(4.dp))
            }
        }
    },
    content: @Composable () -> Unit
) {
    var showMenu by remember { mutableStateOf(false) }

    TopAppBar(
        elevation = 3.dp,
        title = { content() },
        navigationIcon = {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Arrow back",
                modifier = Modifier.clickable { navController.navigate(Screen.HomeScreen.route) }
            )
        },
        actions = {
            IconButton(onClick = { showMenu = !showMenu }) {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "More",
                    tint = Color.White
                )
            }
            DropdownMenu(
                expanded = showMenu,
                onDismissRequest = { showMenu = false }
            ) {
                menuContent()
            }
        }
    )
}

@Composable
fun HomeScreenTopAppBar(
    title: String = "Labyrinth Puzzle",
){
    TopAppBar(
        title = { Text(title) }
    )
}