package com.example.labyrinthpuzzle.view.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.labyrinthpuzzle.model.models.Memory
import com.example.labyrinthpuzzle.model.utils.InjectorUtils
import com.example.labyrinthpuzzle.view.widgets.SimpleTopAppBar
import com.example.labyrinthpuzzle.viewModels.MemoryPuzzleViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
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

    Surface {
        SimpleTopAppBar(arrowBackClicked = { navController.popBackStack() }) {
            Text(text = "Memory Puzzle")
        }
        memoryPuzzle?.let {MemoryPuzzle(memoryPuzzle, navController, viewModel) }
    }

}

@SuppressLint("CoroutineCreationDuringComposition", "UnrememberedMutableState",
    "StateFlowValueCalledInComposition"
)
@Composable
fun MemoryPuzzle(
    memoryPuzzle: Memory?,
    navController: NavController,
    viewModel: MemoryPuzzleViewModel
){
    var memoryPuzzleInstance by remember {
        mutableStateOf(memoryPuzzle)
    }

    val solvedArrayState = MutableStateFlow(mutableStateListOf<Int?>(0, 0, 0, 0, 0, 0).toTypedArray())
    var solvedArray = solvedArrayState.value


    val coroutineScope = rememberCoroutineScope()

    val rows = 3
    val columns = 2

    val grid = Array(rows) { IntArray(columns) }
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
            ){
            for (i in 0 until rows) {
                Row(
                    modifier = Modifier.padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    for (j in 0 until columns) {
                        var boxIndex = i + j

                        if (i == 1){
                            boxIndex++
                        } else if (i == 2){
                            boxIndex += 2
                        }

                        val box = i to j
                        val isSelected = remember { mutableStateOf(selectedBoxes.contains(i to j)) }
                        val isMatched = remember { mutableStateOf(matchedBoxes.contains(box)) }


                            Box(
                                modifier = Modifier
                                    .size(130.dp)
                                    .padding(8.dp)
                                    .background(
                                        if (isMatched.value) Color.Green
                                        else if (isSelected.value) Color.White
                                        else Color.Gray
                                        , RectangleShape)
                                    .clickable {
                                        if (isSelected.value || isMatched.value) {
                                            return@clickable
                                        }

                                        if (selectedBoxes.size < 2) {
                                            selectedBoxes.add(box)
                                            isSelected.value = true

                                            if (selectedBoxes.size == 2) {
                                                val isCorrect = checkMatch(selectedBoxes[0], selectedBoxes[1], memoryPuzzleInstance!!.grid)
                                                if (isCorrect) {
                                                    isMatched.value = true
                                                }
                                                selectedBoxes.clear()
                                            }
                                        }
                                    }
                            ) {

                                Text(
                                    text = "Box Index ($box) -  ${memoryPuzzle!!.grid.get(boxIndex)}",
                                    modifier = Modifier.align(Alignment.Center)
                                )
                            }
                    }
                }
            }
            }
        }

        if (viewModel.isPuzzleInCorrectOrder(solvedArray)) {
            Button(onClick = { navController.popBackStack() }, enabled = true) {
                Text("Puzzle Solved!")
            }

            memoryPuzzleInstance!!.isSolved = true
            var solvedPuzzle = Memory(memoryPuzzleInstance!!.id, memoryPuzzle!!.grid, true)

            coroutineScope.launch {
          //      viewModel.update(solvedPuzzle)
            }

        } else {
            Button(onClick = { navController.popBackStack() }, enabled = false) {
                Text("Puzzle not solved")
            }
        }
}}

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