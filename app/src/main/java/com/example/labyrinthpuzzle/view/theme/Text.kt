package com.example.labyrinthpuzzle.view.theme

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@Composable
fun LabyrinthTileScreenButtonText(text: String){
    Text(
        text = text, modifier = MediumTextModifier,
        textAlign = TextAlign.Center,
        fontSize = 15.sp
    )
}