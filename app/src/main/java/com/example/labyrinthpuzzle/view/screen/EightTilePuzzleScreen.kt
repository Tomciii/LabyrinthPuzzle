package com.example.labyrinthpuzzle.view.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.labyrinthpuzzle.model.entity.Eight
import com.example.labyrinthpuzzle.model.utils.InjectorUtils
import com.example.labyrinthpuzzle.view.theme.Purple100
import com.example.labyrinthpuzzle.view.theme.Purple150
import com.example.labyrinthpuzzle.view.widgets.PuzzleNotSolvedButton
import com.example.labyrinthpuzzle.view.widgets.PuzzleSolvedButton
import com.example.labyrinthpuzzle.viewModels.EightTilesPuzzleViewModel
import com.example.labyrinthpuzzle.view.widgets.SimpleTopAppBar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun EightTilePuzzleScreen(
    navController: NavController = rememberNavController(),
    eightTilePuzzleID: String?
) {

    val viewModel: EightTilesPuzzleViewModel =
        viewModel(factory = InjectorUtils.provideEightTilePuzzleViewModel(LocalContext.current))

    val updatedEightTilePuzzleID = rememberUpdatedState(eightTilePuzzleID)

    var eightTile by remember { mutableStateOf<Eight?>(null) }

    LaunchedEffect(updatedEightTilePuzzleID.value) {
        withContext(Dispatchers.IO) {
            eightTile = viewModel.getEightTilePuzzleById(updatedEightTilePuzzleID.value.toString())
        }
    }
        Surface(color = Purple100) {
            SimpleTopAppBar(navController = navController) {
                Text(text = "Eight Tiles Puzzle")
            }

                eightTile?.let {EightTileScreen(eightTile, navController, viewModel) }
        }

}

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun EightTileScreen(
    eightPuzzle: Eight?,
    navController: NavController,
    viewModel: EightTilesPuzzleViewModel
) {

    var eightTilePuzzleInstance by remember {
        mutableStateOf(eightPuzzle)
    }

    val coroutineScope = rememberCoroutineScope()

    var emptyTilePosition by remember { mutableStateOf(-1 to -1) }

    var tiles by remember { mutableStateOf(viewModel.convertListTo2DArray(eightTilePuzzleInstance!!.grid)) }
    var isSolved by remember { mutableStateOf(false) }
    var selectedTile by remember { mutableStateOf(-1 to -1) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.height(16.dp)
            .background(Purple100))
        Box(
            modifier = Modifier
                .height(380.dp)
                .fillMaxWidth()
        ) {
            var num = 365 / 10
            val boxSize = with(LocalDensity.current) { (num.dp).toPx() }
            val textSize = with(LocalDensity.current) { (boxSize / 1).toSp() }
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                for (i in tiles.indices) {
                    Row(
                        modifier = Modifier
                            .height(boxSize.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        for (j in tiles[i].indices) {
                            val position = i to j

                            val isSelectedTile = selectedTile == position
                            val backgroundColor =
                                if (tiles[i][j] == 0) Color.White else if (isSelectedTile) Color.White else Purple150


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
                                                val emptyTileCol = emptyTilePosition.first
                                                val emptyTileRow = emptyTilePosition.second
                                                val isAdjacentToEmptyTile =
                                                    (selectedTile.first == emptyTileCol &&
                                                            (position.second == emptyTileRow + 1 || position.second == emptyTileRow - 1)) ||
                                                            (position.second == emptyTileRow &&
                                                                    (position.first == emptyTileCol + 1 || position.first == emptyTileCol - 1)) ||
                                                            (position.first == emptyTileCol - 1 && position.second == emptyTileRow)
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
                                if (!tiles[i][j].toString().equals("0"))
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

        if (viewModel.isPuzzleInCorrectOrder(tiles) || eightTilePuzzleInstance!!.isSolved) {
            PuzzleSolvedButton(navController)

            eightTilePuzzleInstance!!.isSolved = true
            var solvedPuzzle = Eight(eightTilePuzzleInstance!!.id, eightPuzzle!!.grid, true)

            coroutineScope.launch {
                viewModel.update(solvedPuzzle)
            }

        } else {
            PuzzleNotSolvedButton(navController)
        }
    }
}


private fun swapTiles(
    position1: Pair<Int, Int>,
    position2: Pair<Int, Int>,
    tiles: Array<Array<Int?>>
) {
    val temp = tiles[position1.first][position1.second]
    tiles[position1.first][position1.second] = tiles[position2.first][position2.second]
    tiles[position2.first][position2.second] = temp
}