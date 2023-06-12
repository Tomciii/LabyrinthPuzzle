package com.example.labyrinthpuzzle.view.screen

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
        }
    }

    Scaffold(topBar = {
        SimpleTopAppBar(navController = navController){
            Text(text="Labyrinth")
        }
    }) { padding ->
        tile?.let {
            LabyrinthTile(tile, modifier = Modifier.padding(padding), navController, labyrinthViewModel)

            //only for testing
            GlobalScope.launch(Dispatchers.IO){
            labyrinthViewModel.printArray()
            }

        }
    }
}

@Composable
fun LabyrinthTile(labyrinthTile: LabyrinthTile?, modifier: Modifier, navController: NavController, viewModel: LabyrinthViewModel) {

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


    Box(Modifier.fillMaxSize()) {
        Text(text = "Tile ID " + tile!!.id.toString()) // For testing
        PuzzleButton(navController = navController, tile = tile!!, isSolved = isUpSolved, direction = tile!!.up, alignment = Alignment.TopCenter, viewModel = viewModel)
        PuzzleButton(navController = navController, tile = tile!!, isSolved = isDownSolved, direction = tile!!.down, alignment = Alignment.BottomCenter, viewModel = viewModel)
        PuzzleButton(navController = navController, tile = tile!!, isSolved = isLeftSolved, direction = tile!!.left, alignment = Alignment.CenterStart, viewModel = viewModel)
        PuzzleButton(navController = navController, tile = tile!!, isSolved = isRightSolved, direction = tile!!.right, alignment = Alignment.CenterEnd, viewModel = viewModel)
    }
}

@Composable
fun PuzzleButton (navController: NavController, tile: LabyrinthTile, isSolved: Boolean, direction: Int, alignment: Alignment, viewModel: LabyrinthViewModel) {

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
                    modifier = Modifier.align(alignment)
                ) {
                    Text(
                        text = "Back", modifier = Modifier
                            .width(100.dp)
                            .padding(4.dp),
                        textAlign = TextAlign.Center
                    )
                }
            } else {
                // check if isSolved -> display "Go to next Tile"
                // if not -> Open Puzzle
                if (isSolved.equals(true)) {
                    Button(
                        onClick = { navController.navigate(Screen.LabyrinthTileScreen.withId((clickedId).toString())) },
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