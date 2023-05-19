package com.example.labyrinthpuzzle.view.screen

import android.graphics.Paint.Align
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
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.labyrinthpuzzle.model.models.LabyrinthTile
import com.example.labyrinthpuzzle.model.utils.InjectorUtils
import com.example.labyrinthpuzzle.view.widgets.SimpleTopAppBar
import com.example.labyrinthpuzzle.viewModels.LabyrinthViewModel
import kotlinx.coroutines.Dispatchers
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
        tile?.let { LabyrinthTile(tile, modifier = Modifier.padding(padding), navController) }
    }
}

@Composable
fun LabyrinthTile(labyrinthTile: LabyrinthTile?, modifier: Modifier, navController: NavController) {


    var tile by remember {
        mutableStateOf(labyrinthTile)
    }

    Log.d("Test :)", tile.toString())

    Box(Modifier.fillMaxSize()){
        if (!tile!!.up.equals(0)) {
            Button(onClick = { navController.navigate(Screen.PuzzleScreen.withIds(tile!!.puzzleArchetypeId.toString(), tile!!.up.toString())) }, modifier = Modifier.align(Alignment.TopCenter)) {
                Text(text = "Open Puzzle", modifier = Modifier
                    .width(100.dp)
                    .padding(4.dp),
                    textAlign = TextAlign.Center
                )
            }
        }

        if (!tile!!.left.equals(0)) {
            Button(onClick = { navController.navigate(Screen.PuzzleScreen.withIds(tile!!.puzzleArchetypeId.toString(), tile!!.left.toString())) }, modifier = Modifier.align(Alignment.CenterStart)) {
                Text(text = tile!!.up.toString(), modifier = Modifier
                    .width(100.dp)
                    .padding(4.dp),
                    textAlign = TextAlign.Center
                )
            }
        }
        if (!tile!!.right.equals(0)) {
            Button(onClick = { navController.navigate(Screen.PuzzleScreen.withIds(tile!!.puzzleArchetypeId.toString(), tile!!.right.toString())) }, modifier = Modifier.align(Alignment.CenterEnd)) {
                Text(text = "Center End", modifier = Modifier
                    .width(100.dp)
                    .padding(4.dp),
                    textAlign = TextAlign.Center
                )
            }
        }

        if (!tile!!.down.equals(0)) {
            Button(onClick = { navController.navigate(Screen.PuzzleScreen.withIds(tile!!.puzzleArchetypeId.toString(), tile!!.down.toString())) }, modifier = Modifier.align(Alignment.BottomCenter)) {
                Text(text = "Bottom Center", modifier = Modifier
                    .width(100.dp)
                    .padding(4.dp),
                    textAlign = TextAlign.Center
                )
            }
        }

    }
}