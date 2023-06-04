package com.example.labyrinthpuzzle.view.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.labyrinthpuzzle.model.utils.InjectorUtils
import com.example.labyrinthpuzzle.viewModels.EightTilesPuzzleViewModel
import com.example.labyrinthpuzzle.viewModels.LabyrinthViewModel
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.labyrinthpuzzle.model.models.Eight
import com.example.labyrinthpuzzle.model.models.LabyrinthTile
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Composable
fun ViewLabyrinthScreen(
    navController: NavController = rememberNavController(),
    labyrinthTileID: String? = "2"
){
    val viewModel: LabyrinthViewModel =
        viewModel(factory = InjectorUtils.provideLabyrinthViewModel(LocalContext.current))

    val updateLoadedTiles = rememberUpdatedState(labyrinthTileID)

    var labyrinthTile by remember { mutableStateOf<List<LabyrinthTile?>>(listOf()) }

    LaunchedEffect(updateLoadedTiles.value) {
        withContext(Dispatchers.IO) {
            labyrinthTile = viewModel.getAllLabyrinthTiles()
        }
    }


    val rows = 7;
    val columns = 5;

    LazyVerticalGrid(
        modifier = Modifier
            .padding(5.dp),
        columns = GridCells.Fixed(columns),
        content = {
            items(35) { i ->
                Box(
                    modifier = Modifier
                        .aspectRatio(1f)
                        .background(Color.Green)
                        .padding(2.dp)
                        .border(width = 2.dp, Color.Black),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "Tile $i")
                }
            }
        })
}