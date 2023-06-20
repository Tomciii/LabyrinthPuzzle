package com.example.labyrinthpuzzle.view.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.labyrinthpuzzle.model.entity.Memory
import com.example.labyrinthpuzzle.model.utils.InjectorUtils
import com.example.labyrinthpuzzle.view.theme.*
import com.example.labyrinthpuzzle.view.widgets.PuzzleNotSolvedButton
import com.example.labyrinthpuzzle.view.widgets.PuzzleSolvedButton
import com.example.labyrinthpuzzle.view.widgets.SimpleTopAppBar
import com.example.labyrinthpuzzle.viewModels.MemoryPuzzleViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun MemoryPuzzleScreen (navController: NavController, memoryPuzzleId: String?){
    val viewModel: MemoryPuzzleViewModel =
        viewModel(factory = InjectorUtils.provideMemoryPuzzleViewModel(LocalContext.current))

    val updatedMemoryPuzzleID = rememberUpdatedState(memoryPuzzleId)

    var memoryPuzzle by remember { mutableStateOf<Memory?>(null) }

    LaunchedEffect(updatedMemoryPuzzleID.value) {
        withContext(Dispatchers.IO) {
            memoryPuzzle = viewModel.getMemoryPuzzleById(updatedMemoryPuzzleID.value.toString())
        }
    }

    Surface(color = Purple100) {
        SimpleTopAppBar(navController = navController) {
            Text(text = "Memory Puzzle")
        }
        memoryPuzzle?.let {MemoryPuzzle(memoryPuzzle, navController, viewModel) }
    }

}

@SuppressLint("CoroutineCreationDuringComposition",
    "UnrememberedMutableState",
    "StateFlowValueCalledInComposition"
)
@Composable
fun MemoryPuzzle(
    memoryPuzzle: Memory?,
    navController: NavController,
    viewModel: MemoryPuzzleViewModel
) {
    var memoryPuzzleInstance by remember {
        mutableStateOf(memoryPuzzle)
    }

    val solvedArrayState = remember { mutableStateListOf<Int?>(0, 0, 0, 0, 0, 0) }

    var solvedArray by remember(solvedArrayState) { mutableStateOf(solvedArrayState) }

    val coroutineScope = rememberCoroutineScope()

    val rows = 3
    val columns = 2

    val selectedBoxes = remember { mutableStateListOf<Pair<Int, Int>>() }
    val matchedBoxes = remember { mutableStateListOf<Pair<Int, Int>>() }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Box(
            modifier = Modifier
                .height(450.dp)
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.align(Alignment.Center)
            ) {
                for (i in 0 until rows) {
                    Row(
                        modifier = Modifier.padding(8.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        for (j in 0 until columns) {
                            var boxIndex = i + j

                            if (i == 1) {
                                boxIndex++
                            } else if (i == 2) {
                                boxIndex += 2
                            }

                            var box = i to j
                            var isSelected = remember { mutableStateOf(selectedBoxes.contains(i to j)) }
                            var isMatched = remember { mutableStateOf(matchedBoxes.contains(box)) }
                            var incorrectSelection by remember { mutableStateOf<Pair<Int, Int>?>(null) }

                            Box(
                                modifier = Modifier
                                    .size(130.dp)
                                    .padding(8.dp)
                                    .border(1.dp, Color.Black)
                                    .background(
                                        if (solvedArray[boxIndex] == 1) Green100
                                        else if (selectedBoxes.contains(box)) Color.White
                                        else if (box == incorrectSelection) Color.White
                                        else Purple150, RectangleShape
                                    )
                                    .clickable {
                                        if (isSelected.value || isMatched.value) {
                                            return@clickable
                                        }

                                        if (selectedBoxes.size < 2) {
                                            selectedBoxes.add(box)


                                            if (selectedBoxes.size == 2) {
                                                val isCorrect = checkMatch(selectedBoxes[0], selectedBoxes[1], memoryPuzzleInstance!!.grid)

                                                if (isCorrect) {
                                                    var firstBoxIndex = selectedBoxes[0].first + selectedBoxes[0].second

                                                    if (selectedBoxes[0].first == 1) {
                                                        firstBoxIndex++
                                                    } else if (selectedBoxes[0].first == 2) {
                                                        firstBoxIndex += 2
                                                    }

                                                    var secondBoxIndex = selectedBoxes[1].first + selectedBoxes[1].second

                                                    if (selectedBoxes[1].first == 1) {
                                                        secondBoxIndex++
                                                    } else if (selectedBoxes[1].first == 2) {
                                                        secondBoxIndex += 2
                                                    }

                                                    solvedArrayState[firstBoxIndex] = 1
                                                    solvedArrayState[secondBoxIndex] = 1
                                                    matchedBoxes.add(box)
                                                } else {
                                                    incorrectSelection = box
                                                }

                                                coroutineScope.launch {
                                                    delay(200)
                                                    incorrectSelection = null
                                                    selectedBoxes.clear()
                                                }
                                            }
                                        }
                                    }
                            ) {

                                var memorySymbol = getMemorySymbol(memoryPuzzle, boxIndex)

                                if (selectedBoxes.contains(box) || solvedArray[boxIndex] == 1) {
                                    Icon(
                                        imageVector = memorySymbol,
                                        contentDescription = "Symbol",
                                        modifier = Modifier
                                            .align(Alignment.Center)
                                            .size(50.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }

        if (viewModel.isPuzzleInCorrectOrder(solvedArray.toTypedArray())) {
            PuzzleSolvedButton(navController)

            memoryPuzzleInstance!!.isSolved = true
            var solvedPuzzle = Memory(memoryPuzzleInstance!!.id, memoryPuzzle!!.grid, true)

            coroutineScope.launch {
                viewModel.update(solvedPuzzle)
            }

        } else {
            PuzzleNotSolvedButton(navController)
        }
    }
}

private fun getMemorySymbol(memoryPuzzle : Memory?, boxIndex: Int):ImageVector{
    Icons.Default.Build
    if (memoryPuzzle!!.grid.get(boxIndex) == "1") {
        return Icons.Default.Call
    } else if (memoryPuzzle!!.grid.get(boxIndex) == "2") {
        return Icons.Default.Check
    }

    return Icons.Default.Build
}
private fun checkMatch(box1: Pair<Int, Int>, box2: Pair<Int, Int>, grid:List<String>): Boolean {
    var firstBoxIndex = box1.first + box1.second

    if (box1.first == 1){
        firstBoxIndex++
    } else if (box1.first == 2){
        firstBoxIndex += 2
    }

    var secondBoxIndex = box2.first + box2.second

    if (box2.first == 1){
        secondBoxIndex++
    } else if (box2.first == 2){
        secondBoxIndex += 2
    }

    return grid!!.get(firstBoxIndex) == grid!!.get(secondBoxIndex)
}