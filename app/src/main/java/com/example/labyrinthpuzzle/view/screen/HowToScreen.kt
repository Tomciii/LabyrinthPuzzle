package com.example.labyrinthpuzzle.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.labyrinthpuzzle.R
import com.example.labyrinthpuzzle.view.theme.Purple100
import com.example.labyrinthpuzzle.view.widgets.SimpleTopAppBar

@Composable
fun HowToScreen(navController: NavController = rememberNavController()){
   Surface(modifier = Modifier.fillMaxSize(), color = Purple100) {
           Column {
               SimpleTopAppBar(
                   navController = navController) {
                   Text(text = "How To Play")
               }
           Column(modifier = Modifier.verticalScroll(rememberScrollState())){
               Text("Welcome to Labyrinth Puzzle!", modifier = Modifier.padding(10.dp))
               Text("To start the game and enter the labyrinth, touch the 'Start' button.", modifier = Modifier.padding(10.dp))
               Image(painter = painterResource(id = R.drawable.start_screen), contentDescription = "Start Screen", modifier = Modifier.size(600.dp), contentScale = ContentScale.Fit)
               Text("After that, the first tile of the puzzle appears and you have to solve the puzzle to advance.", modifier = Modifier.padding(10.dp))
               Image(painter = painterResource(id = R.drawable.start_button_clicked), contentDescription = "Open Puzzle Button",  modifier = Modifier.size(600.dp), contentScale = ContentScale.Fit)
               Text("When clicking the button, you have to solve either one of two possible puzzles: Memory or 8-Tile.", modifier = Modifier.padding(10.dp))
               Image(painter = painterResource(id = R.drawable.memory), contentDescription = "Memory Screen",  modifier = Modifier.size(600.dp), contentScale = ContentScale.Fit)
               Image(painter = painterResource(id = R.drawable.puzzle_screen), contentDescription = "8-Tile Screen",  modifier = Modifier.size(600.dp), contentScale = ContentScale.Fit)
               Text("Memory should be kind of self-explanatory, click on the cards and find two matching ones to solve the puzzle. If you, however, open up the 8-Tile puzzle, you will need to move tiles around in the correct order. Don't worry, touching one of the tiles with a blank space next to it is enough and it will move to where the blank space is.", modifier = Modifier.padding(10.dp))
               Image(painter = painterResource(id = R.drawable.memory_solved), contentDescription = "Memory solve",  modifier = Modifier.size(600.dp), contentScale = ContentScale.Fit)
               Image(painter = painterResource(id = R.drawable.solved), contentDescription = "8-Tile solved",  modifier = Modifier.size(600.dp), contentScale = ContentScale.Fit)
               Text("After you solved one of the puzzles that appeared, the initial button state changes from 'Open Puzzle' to 'Next Tile' and you can advance.", modifier = Modifier.padding(10.dp))
               Image(painter = painterResource(id = R.drawable.next_tile), contentDescription = "8-Tile solved",  modifier = Modifier.size(600.dp), contentScale = ContentScale.Fit)
               Text("There is a chance to get to a junction with multiple puzzles and therefore possible paths to the end. Find the correct one!", modifier = Modifier.padding(10.dp))
               Image(painter = painterResource(id = R.drawable.multiple), contentDescription = "8-Tile solved",  modifier = Modifier.size(600.dp), contentScale = ContentScale.Fit)
               Text("If you should choose an incorrect one, there may be a dead end somewhere along the way. So choose wisely to be as fast as possible.", modifier = Modifier.padding(10.dp))
               Image(painter = painterResource(id = R.drawable.deadend), contentDescription = "8-Tile solved",  modifier = Modifier.size(600.dp), contentScale = ContentScale.Fit)
               Text("When you completed all the puzzles and found your way through the labyrinth, the game ends and you will be greeted with a winning screen.", modifier = Modifier.padding(10.dp))
               Image(painter = painterResource(id = R.drawable.winning), contentDescription = "8-Tile solved",  modifier = Modifier.size(600.dp), contentScale = ContentScale.Fit)
           }
       }
   }
}