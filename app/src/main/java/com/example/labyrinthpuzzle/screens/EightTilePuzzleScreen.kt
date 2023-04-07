package com.example.labyrinthpuzzle.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.labyrinthpuzzle.models.getEightTiles
import com.example.labyrinthpuzzle.widgets.HomeTopAppBar

@Composable
fun EightTilePuzzleScreen(navController: NavController = rememberNavController()){
    Surface{
        HomeTopAppBar(title = "Eight Tiles Puzzle"){

        }
        EightTileScreen(getEightTiles(0))
    }
}

@Composable
fun EightTileScreen(eightTile: Array<Array<Int>>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Eight Tile Screen")
        Spacer(modifier = Modifier.height(16.dp))
        Box(
            modifier = Modifier
                .height(380.dp)
                .background(Color.LightGray)
                .fillMaxWidth()
        ) {
            var num = 365 / 10
            val boxSize = with(LocalDensity.current) { (num.dp).toPx() }
            val textSize = with(LocalDensity.current) { (boxSize / 1).toSp() }
           Column(
               modifier = Modifier.fillMaxSize()
           ){
            for (i in eightTile.indices) {
                Row(
                    modifier = Modifier
                        .height(boxSize.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    for (j in eightTile[i].indices) {
                        Box(
                            modifier = Modifier
                                .weight(2f)
                                .aspectRatio(1f)
                                .border(1.dp, Color.Black),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = eightTile[i][j].toString(),
                                fontSize = textSize,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    }
                }
            }
           }
        }
    }
}