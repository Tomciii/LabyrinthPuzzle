package com.example.labyrinthpuzzle.screen


import androidx.compose.foundation.layout.Arrangement
import com.example.labyrinthpuzzle.view.theme.SETTINGS
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.labyrinthpuzzle.model.utils.InjectorUtils
import com.example.labyrinthpuzzle.view.theme.Purple100
import com.example.labyrinthpuzzle.view.widgets.SimpleTopAppBar
import com.example.labyrinthpuzzle.viewModels.EightTilesPuzzleViewModel
import com.example.labyrinthpuzzle.viewModels.LabyrinthViewModel
import com.example.labyrinthpuzzle.viewModels.MemoryPuzzleViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
@Composable
fun SettingsScreen(navController: NavController = rememberNavController()){

    val eightTilesViewModel: EightTilesPuzzleViewModel =
        viewModel(factory = InjectorUtils.provideEightTilePuzzleViewModel(LocalContext.current))

    val memoryViewModel: MemoryPuzzleViewModel =
        viewModel(factory = InjectorUtils.provideMemoryPuzzleViewModel(LocalContext.current))

    val labyrinthViewModel: LabyrinthViewModel =
        viewModel(factory = InjectorUtils.provideLabyrinthViewModel(LocalContext.current))

    val coroutineScope = rememberCoroutineScope()

    Surface(modifier = Modifier.fillMaxSize(), color = Purple100) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            SimpleTopAppBar(
                navController = navController) {
                Text(text = SETTINGS)
            }

            Text("Game Settings")

            Button(onClick = {
                reset(eightTilesViewModel,
                    labyrinthViewModel,
                    memoryViewModel,
                    coroutineScope)
            }){
                Text(text = "Reset Progress")
            }
        }
    }
}

 fun reset(viewModel: EightTilesPuzzleViewModel,
           labyrinthViewModel: LabyrinthViewModel,
           memoryPuzzleViewModel: MemoryPuzzleViewModel,
           coroutineScope: CoroutineScope){

         coroutineScope.launch {
            viewModel.reset()
            labyrinthViewModel.reset()
             memoryPuzzleViewModel.reset()
         }
 }
