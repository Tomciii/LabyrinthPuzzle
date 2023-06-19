package com.example.labyrinthpuzzle.view.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.labyrinthpuzzle.model.models.LabyrinthTile
import com.example.labyrinthpuzzle.view.screen.Screen
import com.example.labyrinthpuzzle.view.theme.*
import com.example.labyrinthpuzzle.viewModels.LabyrinthViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Composable
fun HomeScreenMenuButton(navController: NavController, text: String, route: String){
    Button(modifier = Modifier.height(65.dp), onClick = {
        navController.navigate(route)
    }) {
        Text(text = text, modifier = BigTextModifier, textAlign = TextAlign.Center, fontSize = 17.sp)
    }

    Spacer(modifier = Modifier.height(12.dp))
}

@Composable
fun LabyrinthTileScreenButton (navController: NavController, tile: LabyrinthTile, isSolved: Boolean, direction: Int, alignment: Alignment, viewModel: LabyrinthViewModel) {

    // The id of the Labyrinth Tile that you click on, takes care of back button as well as next Labyrinth
    var clickedId by remember { mutableStateOf(1) }
    var updatedclickedId = rememberUpdatedState(1)

    LaunchedEffect(updatedclickedId.value) {
        withContext(Dispatchers.IO) {
            if (alignment == Alignment.TopCenter){
                clickedId = viewModel.getPreviousTileIdByCoordinates(tile!!.xCoordinate, tile!!.yCoordinate + 1)
            } else if (alignment == Alignment.BottomCenter){
                clickedId = viewModel.getPreviousTileIdByCoordinates(tile!!.xCoordinate, tile!!.yCoordinate - 1)
            } else if (alignment == Alignment.CenterStart){
                clickedId = viewModel.getPreviousTileIdByCoordinates(tile!!.xCoordinate - 1, tile!!.yCoordinate)
            } else if (alignment == Alignment.CenterEnd){
                clickedId = viewModel.getPreviousTileIdByCoordinates(tile!!.xCoordinate + 1, tile!!.yCoordinate)
            }
        }
    }


    Box(modifier = Modifier.fillMaxSize()){
        if (!direction.equals(0)) {
            if (direction.equals(99)) {
                Button(
                    onClick = { navController.navigate(Screen.LabyrinthTileScreen.withId((clickedId).toString())) },
                    modifier = Modifier
                        .align(alignment)
                        .padding(15.dp)
                        .height(65.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Purple150)
                ) {
                    LabyrinthTileScreenButtonText(text = "Back")
                }
            } else {
                // check if isSolved -> display "Go to next Tile"
                // if not -> Open Puzzle
                if (isSolved.equals(true)) {
                    Button(
                        onClick = { navController.navigate(Screen.LabyrinthTileScreen.withId((clickedId).toString())) },
                        modifier = Modifier
                            .align(alignment)
                            .padding(15.dp)
                            .height(65.dp)
                    ) {
                        LabyrinthTileScreenButtonText(text = "Go to next Tile")
                    }
                } else {
                    Button(onClick = {
                        navController.navigate(
                            Screen.PuzzleScreen.withIds(
                                tile!!.puzzleArchetypeId.toString(),
                                direction.toString()
                            )
                        )
                    }, modifier = Modifier
                        .align(alignment)
                        .padding(15.dp)
                        .height(65.dp)) {
                        LabyrinthTileScreenButtonText(text = "Open puzzle")
                    }
                }
            }
        }
    }
}