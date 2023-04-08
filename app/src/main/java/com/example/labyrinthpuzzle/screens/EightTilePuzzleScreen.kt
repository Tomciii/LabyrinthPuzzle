package com.example.labyrinthpuzzle.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.labyrinthpuzzle.models.getEightTiles
import com.example.labyrinthpuzzle.widgets.SimpleTopAppBar
import java.lang.Math.abs

@Composable
fun EightTilePuzzleScreen(navController: NavController = rememberNavController()){
    Surface{
        SimpleTopAppBar(arrowBackClicked = { navController.popBackStack() }){
            Text(text="Eight Tiles Puzzle")
        }
        EightTileScreen(getEightTiles(0), navController)
    }
}

@Composable
fun EightTileScreen(eightTile: Array<Array<Int>>, navController: NavController) {
    var emptyTilePosition by remember { mutableStateOf(0 to 0) }
    var tiles by remember { mutableStateOf(eightTile) }
    var selectedTile by remember { mutableStateOf(-1 to -1) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
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
                for (i in tiles.indices) {
                    Row(
                        modifier = Modifier
                            .height(boxSize.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        for (j in tiles[i].indices) {
                            val position = i to j

                            val emptyTileRow = emptyTilePosition.first
                            val emptyTileCol = emptyTilePosition.second
                            val isSelectedTile = selectedTile == position
                            val backgroundColor = if (tiles[i][j] == 0) Color.White else if (isSelectedTile) Color.White else Color.LightGray
                            val isAdjacentToEmptyTile =
                                (i == emptyTileRow && (j == emptyTileCol + 1 || j == emptyTileCol - 1)) ||  // same row, adjacent column
                                (j == emptyTileCol && abs(i + emptyTileRow) == 1)

                            Box(
                                modifier = Modifier
                                    .weight(2f)
                                    .aspectRatio(1f)
                                    .background(backgroundColor)
                                    .border(1.dp, Color.Black)
                                    .pointerInput(Unit) {
                                        detectTapGestures(
                                            onPress = {
                                                selectedTile = position
                                            },
                                            onTap = {
                                                if (isAdjacentToEmptyTile) {
                                                    swapTiles(position, emptyTilePosition, tiles)
                                                    emptyTilePosition = position
                                                }
                                                selectedTile = -1 to -1
                                            }
                                        )
                                    },
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = tiles[i][j].toString(),
                                    fontSize = textSize,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis,
                                    color = Color.Black
                                )
                            }
                            if (tiles[i][j] == 0) {
                                emptyTilePosition = i to j
                            }
                        }
                    }
                }
            }
        }
        Button(onClick = { navController.popBackStack() }) {
            Text("Solved")
        }
    }
}

private fun swapTiles(position1: Pair<Int, Int>, position2: Pair<Int, Int>, tiles: Array<Array<Int>>) {
    val temp = tiles[position1.first][position1.second]
    tiles[position1.first][position1.second] = tiles[position2.first][position2.second]
    tiles[position2.first][position2.second] = temp
}