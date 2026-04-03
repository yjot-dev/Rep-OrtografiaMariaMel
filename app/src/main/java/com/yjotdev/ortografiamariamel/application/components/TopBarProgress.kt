package com.yjotdev.ortografiamariamel.application.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.vectorResource
import com.yjotdev.ortografiamariamel.R

@Composable
fun TopBarProgress(
    modifier: Modifier = Modifier,
    currentLesson: Int,
    totalLesson: Int,
    numLife: Int,
    numError: Int
) {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ){
        LinearProgressIndicator(
            progress = { currentLesson.toFloat() / (totalLesson - 1) },
            modifier = Modifier.height(dimensionResource(id = R.dimen.short4_dp)),
            color = MaterialTheme.colorScheme.error,
        )
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.favorite_48),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.error
        )
        Text(
            text = (numLife - numError).toString(),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.error
        )
    }
}