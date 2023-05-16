package com.example.labyrinthpuzzle.ui.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun SimpleTopAppBar(
    arrowBackClicked: () -> Unit = {},
    menuContent: @Composable () -> Unit = {},
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