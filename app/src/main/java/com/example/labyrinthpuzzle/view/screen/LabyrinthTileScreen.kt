package com.example.labyrinthpuzzle.view.screen

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.labyrinthpuzzle.model.models.LabyrinthTile
import com.example.labyrinthpuzzle.model.utils.InjectorUtils
import com.example.labyrinthpuzzle.view.widgets.SimpleTopAppBar
import com.example.labyrinthpuzzle.viewModels.EightTilesPuzzleViewModel
import com.example.labyrinthpuzzle.viewModels.LabyrinthViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun LabyrinthTileScreen(navController: NavController = rememberNavController(), labyrinthId: Int? = 1){

    val labyrinthViewModel: LabyrinthViewModel = viewModel(factory = InjectorUtils.provideLabyrinthViewModel(
        LocalContext.current))

    var updatedLabyrinthTileID = rememberUpdatedState(labyrinthId)

    var tile by remember { mutableStateOf<LabyrinthTile?>(null) }

    LaunchedEffect(updatedLabyrinthTileID.value) {
        withContext(Dispatchers.IO) {
            tile = labyrinthViewModel.getLabyrinthTileById(updatedLabyrinthTileID.value!!.toInt())
            Log.d("Test", tile.toString())
        }
    }

    Scaffold(topBar = {
        SimpleTopAppBar(arrowBackClicked = { navController.popBackStack() }){
            Text(text="Labyrinth")
        }
    }) { padding ->
        tile?.let {
            LabyrinthTile(tile, modifier = Modifier.padding(padding), navController)

            //only for testing
            GlobalScope.launch(Dispatchers.IO){
            labyrinthViewModel.printArray()
            }

        }
    }
}

@Composable
fun LabyrinthTile(labyrinthTile: LabyrinthTile?, modifier: Modifier, navController: NavController) {

    var tile by remember { mutableStateOf(labyrinthTile) }
    val eightTilesPuzzleViewModel: EightTilesPuzzleViewModel =
        viewModel(factory = InjectorUtils.provideEightTilePuzzleViewModel(LocalContext.current))

    var isUpSolved by remember { mutableStateOf<Boolean>(false) }
    var isDownSolved by remember { mutableStateOf<Boolean>(false) }
    var isLeftSolved by remember { mutableStateOf<Boolean>(false) }
    var isRightSolved by remember { mutableStateOf<Boolean>(false) }

    var updatedIsUpSolved = rememberUpdatedState(tile!!.up)
    var updatedIsDownSolved = rememberUpdatedState(tile!!.down)
    var updatedIsLeftSolved = rememberUpdatedState(tile!!.left)
    var updatedIsRightSolved = rememberUpdatedState(tile!!.right)

    LaunchedEffect(updatedIsUpSolved.value, updatedIsDownSolved.value, updatedIsLeftSolved.value, updatedIsRightSolved.value) {
        withContext(Dispatchers.IO) {
            isUpSolved = eightTilesPuzzleViewModel.isPuzzleSolved(updatedIsUpSolved.value)
            isDownSolved = eightTilesPuzzleViewModel.isPuzzleSolved(updatedIsDownSolved.value)
            isLeftSolved = eightTilesPuzzleViewModel.isPuzzleSolved(updatedIsLeftSolved.value)
            isRightSolved = eightTilesPuzzleViewModel.isPuzzleSolved(updatedIsRightSolved.value)
        }
    }

    Log.d("Test :)", isUpSolved.toString())

    Box(Modifier.fillMaxSize()) {
        PuzzleButton(navController = navController, tile = tile!!, isSolved = isUpSolved, direction = tile!!.up, alignment = Alignment.TopCenter)
        PuzzleButton(navController = navController, tile = tile!!, isSolved = isDownSolved, direction = tile!!.down, alignment = Alignment.BottomCenter)
        PuzzleButton(navController = navController, tile = tile!!, isSolved = isUpSolved, direction = tile!!.left, alignment = Alignment.CenterStart)
        PuzzleButton(navController = navController, tile = tile!!, isSolved = isUpSolved, direction = tile!!.right, alignment = Alignment.CenterEnd)
    }
}

@Composable
fun PuzzleButton (navController: NavController, tile: LabyrinthTile, isSolved: Boolean, direction: Int, alignment: Alignment) {

    Box(modifier = Modifier.fillMaxSize()){
        if (!direction.equals(0)) {
            if (direction.equals(99)) {
                //TODO: Add Back Button

            } else {
                // check if isSolved -> display "Go to next Tile"
                // if not -> Open Puzzle
                if (isSolved.equals(true)) {
                    Button(
                        onClick = { navController.navigate(Screen.LabyrinthTileScreen.withId((tile!!.id+1).toString())) },
                        modifier = Modifier.align(alignment)
                    ) {
                        Text(
                            text = "Go to next Tile", modifier = Modifier
                                .width(100.dp)
                                .padding(4.dp),
                            textAlign = TextAlign.Center
                        )
                    }
                } else {
                    Button(onClick = {
                        navController.navigate(
                            Screen.PuzzleScreen.withIds(
                                tile!!.puzzleArchetypeId.toString(),
                                direction.toString()
                            )
                        )
                    }, modifier = Modifier.align(alignment)) {
                        Text(
                            text = "Open Puzzle", modifier = Modifier
                                .width(100.dp)
                                .padding(4.dp),
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}