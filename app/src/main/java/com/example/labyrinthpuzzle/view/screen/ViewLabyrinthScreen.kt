package com.example.labyrinthpuzzle.view.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Surface
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.labyrinthpuzzle.model.utils.InjectorUtils
import com.example.labyrinthpuzzle.viewModels.LabyrinthViewModel
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.labyrinthpuzzle.model.entity.LabyrinthTile
import com.example.labyrinthpuzzle.view.theme.*
import com.example.labyrinthpuzzle.view.widgets.SimpleTopAppBar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Composable
fun ViewLabyrinthScreen(
    navController: NavController = rememberNavController(),
){
    val viewModel: LabyrinthViewModel =
        viewModel(factory = InjectorUtils.provideLabyrinthViewModel(LocalContext.current))


    val rows = 7;
    val columns = 5;

    Surface(modifier = Modifier.fillMaxSize(), color = Purple100) {
        Column {
            SimpleTopAppBar(
                navController = navController) {
                androidx.compose.material.Text(text = VIEW_LABYRINTH)
            }

            var labyrinthTiles by remember { mutableStateOf<List<LabyrinthTile?>>(listOf()) }

            LaunchedEffect(Unit) {
                withContext(Dispatchers.IO) {
                    val updatedLabyrinthTiles = viewModel.getAllLabyrinthTiles()
                    withContext(Dispatchers.Main) {
                        labyrinthTiles = updatedLabyrinthTiles
                    }
                }
            }

            LabyrinthGrid(
                labyrinthTiles = labyrinthTiles,
                columns = columns,
                rows = rows,
                navController = navController
            )
        }
    }
}

@Composable
fun LabyrinthGrid(labyrinthTiles: List<LabyrinthTile?>, rows: Int, columns: Int, navController: NavController) {

    Column(modifier = Modifier.padding(5.dp)) {
        repeat(rows) { row ->
            Row(Modifier.fillMaxWidth()) {
                repeat(columns) { column ->
                    val tile = labyrinthTiles.find { it?.xCoordinate == column && it?.yCoordinate == rows - 1 - row }

                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .aspectRatio(1f)
                            .background(
                                if (tile?.isUnlocked == true && ((tile!!.id == 18 || tile!!.id == 14 || tile!!.id == 15))) Yellow
                                else if (tile?.isUnlocked == true) Green100
                                else Purple150)
                            .padding(2.dp)
                            .border(width = 2.dp, Color.Black),
                        contentAlignment = Alignment.Center
                    ) {
                        if (tile?.isUnlocked == true)
                            Button(
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
                            modifier = Modifier.fillMaxSize(),
                            onClick = { navController.navigate(Screen.LabyrinthTileScreen.withId((tile.id).toString())) },
                        ) {
                            Text(text = "${tile.id}")
                        }

                        else Text(" ")
                    }
                }
            }
        }
    }
}