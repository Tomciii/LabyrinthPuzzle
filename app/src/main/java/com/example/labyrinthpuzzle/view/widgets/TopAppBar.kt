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
    firstMenuItemClicked: () -> Unit = {},
    secondMenuItemClicked: () -> Unit = {},
    arrowBackClicked: () -> Unit = {},
    menuContent: @Composable () -> Unit = {
        DropdownMenuItem(onClick = { firstMenuItemClicked() }) {
            Row {
                Icon(imageVector = Icons.Default.AccountCircle, contentDescription = "View Labyrinth", modifier = Modifier.padding(4.dp))
                Text(text = "View Labyrinth", modifier = Modifier
                    .width(100.dp)
                    .padding(4.dp))
            }
        }
        DropdownMenuItem(onClick = { secondMenuItemClicked() }) {
            Row {
                Icon(imageVector = Icons.Default.Favorite, contentDescription = "How To Play", modifier = Modifier.padding(4.dp))
                Text(text = "How To Play", modifier = Modifier
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
                modifier = Modifier.clickable { arrowBackClicked() }
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
fun HomeTopAppBar(
    title: String = "default",
    menuContent: @Composable () -> Unit
){
    var showMenu by remember { mutableStateOf(false) }

    TopAppBar(
        title = { Text(title) },
        actions = {
            IconButton(onClick = { showMenu = !showMenu }) {
                Icon(imageVector = Icons.Default.MoreVert, contentDescription = "More")
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