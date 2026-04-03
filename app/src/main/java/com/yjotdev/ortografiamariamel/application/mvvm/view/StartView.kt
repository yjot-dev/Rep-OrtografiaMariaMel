package com.yjotdev.ortografiamariamel.application.mvvm.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.yjotdev.ortografiamariamel.R
import com.yjotdev.ortografiamariamel.application.theme.OrtografiaMariaMelTheme
import com.yjotdev.ortografiamariamel.application.components.BackGroundImage
import com.yjotdev.ortografiamariamel.application.components.RecyclerButton
import com.yjotdev.ortografiamariamel.application.utils.ComponentPreview

@Composable
fun StartView(
    onStartClicked: () -> Unit,
    modifier: Modifier = Modifier
){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = modifier.background(MaterialTheme.colorScheme.onPrimary)
    ){
        BackGroundImage(
            alpha = 1f,
            modifier = Modifier.fillMaxWidth(0.7f)
        )
        RecyclerButton(
            textButton = stringResource(R.string.button_start),
            modifier = Modifier
                .height(dimensionResource(id = R.dimen.short7_dp))
                .fillMaxWidth(0.7f),
            onClick = onStartClicked
        )
    }
}

@ComponentPreview
@Composable
private fun PreviewStartView() {
    OrtografiaMariaMelTheme {
        StartView(
            modifier = Modifier.fillMaxSize(),
            onStartClicked = {}
        )
    }
}