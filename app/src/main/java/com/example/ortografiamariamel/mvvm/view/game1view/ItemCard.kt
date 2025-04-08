package com.example.ortografiamariamel.mvvm.view.game1view

import androidx.compose.animation.animateColorAsState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.ortografiamariamel.composable.RecyclerButton

@Composable
fun ItemCard(
    modifier: Modifier,
    textN: String,
    isCorrectN: Boolean,
    isIncorrectN: Boolean,
    isSelectedN: Boolean,
    onClick: ()-> Unit,
){
    val selectedColor by animateColorAsState(
        if (isSelectedN) MaterialTheme.colorScheme.primaryContainer
        else MaterialTheme.colorScheme.primary, label = "ColorAnimation1"
    )
    val incorrectColor by animateColorAsState(
        if (isIncorrectN) MaterialTheme.colorScheme.error
        else MaterialTheme.colorScheme.tertiary, label = "ColorAnimation2"
    )
    RecyclerButton(
        textButton = textN,
        isEnabled = isCorrectN,
        colorButton = selectedColor,
        colorBorder = incorrectColor,
        colorText = MaterialTheme.colorScheme.onPrimaryContainer,
        modifier = modifier,
        onClick = onClick
    )
}