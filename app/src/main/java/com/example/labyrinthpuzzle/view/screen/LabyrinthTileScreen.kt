package com.example.labyrinthpuzzle.view.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.labyrinthpuzzle.model.entity.LabyrinthTile
import com.example.labyrinthpuzzle.model.utils.InjectorUtils
import com.example.labyrinthpuzzle.view.theme.LABYRINTH
import com.example.labyrinthpuzzle.view.theme.Purple100
import com.example.labyrinthpuzzle.view.widgets.LabyrinthTileScreenButton
import com.example.labyrinthpuzzle.view.widgets.SimpleTopAppBar
import com.example.labyrinthpuzzle.viewModels.EightTilesPuzzleViewModel
import com.example.labyrinthpuzzle.viewModels.LabyrinthViewModel
import com.example.labyrinthpuzzle.viewModels.MemoryPuzzleViewModel
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
            Text(text= LABYRINTH)
        }
    }) { padding ->
        tile?.let {
            LabyrinthTile(tile, modifier = Modifier.padding(padding), navController, labyrinthViewModel)

            //only for testing
            GlobalScope.launch(Dispatchers.IO){
          //  labyrinthViewModel.printArray()
            }

        }
    }
}

@Composable
fun LabyrinthTile(labyrinthTile: LabyrinthTile?, modifier: Modifier, navController: NavController, viewModel: LabyrinthViewModel) {

    var tile by remember { mutableStateOf(labyrinthTile) }

    val eightTilesPuzzleViewModel: EightTilesPuzzleViewModel =
        viewModel(factory = InjectorUtils.provideEightTilePuzzleViewModel(LocalContext.current))

    val memoryPuzzleViewModel: MemoryPuzzleViewModel =
        viewModel(factory = InjectorUtils.provideMemoryPuzzleViewModel(LocalContext.current))

    val labyrinthViewModel: LabyrinthViewModel =
        viewModel(factory = InjectorUtils.provideLabyrinthViewModel(LocalContext.current))

    var isUpSolved by remember { mutableStateOf(false) }
    var isDownSolved by remember { mutableStateOf(false) }
    var isLeftSolved by remember { mutableStateOf(false) }
    var isRightSolved by remember { mutableStateOf(false) }

    var updatedIsUpSolved = rememberUpdatedState(tile!!.up)
    var updatedIsDownSolved = rememberUpdatedState(tile!!.down)
    var updatedIsLeftSolved = rememberUpdatedState(tile!!.left)
    var updatedIsRightSolved = rememberUpdatedState(tile!!.right)

    LaunchedEffect(updatedIsUpSolved.value, updatedIsDownSolved.value, updatedIsLeftSolved.value, updatedIsRightSolved.value) {
        withContext(Dispatchers.IO) {
            if (tile!!.puzzleArchetypeId == 1){
                isUpSolved = eightTilesPuzzleViewModel.isPuzzleSolved(updatedIsUpSolved.value)
                isDownSolved = eightTilesPuzzleViewModel.isPuzzleSolved(updatedIsDownSolved.value)
                isLeftSolved = eightTilesPuzzleViewModel.isPuzzleSolved(updatedIsLeftSolved.value)
                isRightSolved = eightTilesPuzzleViewModel.isPuzzleSolved(updatedIsRightSolved.value)
            } else {
                isUpSolved = memoryPuzzleViewModel.isPuzzleSolved(updatedIsUpSolved.value)
                isDownSolved = memoryPuzzleViewModel.isPuzzleSolved(updatedIsDownSolved.value)
                isLeftSolved = memoryPuzzleViewModel.isPuzzleSolved(updatedIsLeftSolved.value)
                isRightSolved = memoryPuzzleViewModel.isPuzzleSolved(updatedIsRightSolved.value)
            }

            if (isUpSolved){
                val id = viewModel.getTileIdByCoordinates(tile!!.xCoordinate, tile!!.yCoordinate + 1)
                labyrinthViewModel.unlockLabyrinthTile(id)
            }

            if (isDownSolved){
                val id = viewModel.getTileIdByCoordinates(tile!!.xCoordinate, tile!!.yCoordinate - 1)
                labyrinthViewModel.unlockLabyrinthTile(id)
            }

            if (isLeftSolved){
                val id = viewModel.getTileIdByCoordinates(tile!!.xCoordinate - 1, tile!!.yCoordinate)
                labyrinthViewModel.unlockLabyrinthTile(id)
            }

            if (isRightSolved){
                val id = viewModel.getTileIdByCoordinates(tile!!.xCoordinate + 1, tile!!.yCoordinate)
                labyrinthViewModel.unlockLabyrinthTile(id)
            }
        }
    }

    Box(Modifier
        .fillMaxSize()
        .background(color = Purple100)) {

        if (tile!!.puzzleArchetypeId == 1){
         //  Log.d("Labyrinth Tile ", "Labyrinth Tile " + tile!!.id.toString() + ", Type of Labyrinth : Eight Tiles")
        } else {
         //   Log.d("Labyrinth Tile ", "Labyrinth Tile " + tile!!.id.toString() + ", Type of Labyrinth : Memory")
        }

        displayExtraInformation(tile, Modifier.align(Alignment.Center))

        LabyrinthTileScreenButton(navController = navController, tile = tile!!, isSolved = isUpSolved, direction = tile!!.up, alignment = Alignment.TopCenter, viewModel = viewModel)
        LabyrinthTileScreenButton(navController = navController, tile = tile!!, isSolved = isDownSolved, direction = tile!!.down, alignment = Alignment.BottomCenter, viewModel = viewModel)
        LabyrinthTileScreenButton(navController = navController, tile = tile!!, isSolved = isLeftSolved, direction = tile!!.left, alignment = Alignment.CenterStart, viewModel = viewModel)
        LabyrinthTileScreenButton(navController = navController, tile = tile!!, isSolved = isRightSolved, direction = tile!!.right, alignment = Alignment.CenterEnd, viewModel = viewModel)
    }
}

@Composable
private fun displayExtraInformation(tile: LabyrinthTile?, modifier: Modifier){
    if (tile!!.id == 13){
        Text(text="Congratulations you've won!", modifier = modifier)
    } else if (tile!!.id == 18 || tile!!.id == 14 || tile!!.id == 15 ){
        Text(text="You've reached a dead end!", modifier = modifier)
    }
}