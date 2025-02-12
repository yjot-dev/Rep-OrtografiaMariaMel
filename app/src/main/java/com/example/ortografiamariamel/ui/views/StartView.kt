package com.example.ortografiamariamel.ui.views

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.ortografiamariamel.R
import com.example.ortografiamariamel.ui.theme.OrtografiaMariaMelTheme
import com.example.ortografiamariamel.ui.views.utils.BackGroundImage
import com.example.ortografiamariamel.ui.views.utils.RecyclerButton

@Composable
fun StartView(
    onStartClicked: () -> Unit,
    modifier: Modifier = Modifier
){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.background(MaterialTheme.colorScheme.onPrimary)
    ){
        BackGroundImage(
            alpha = 1f,
            modifier = Modifier.fillMaxHeight(0.87f)
        )
        RecyclerButton(
            textButton = stringResource(R.string.button_start),
            modifier = Modifier
                .weight(1f)
                .padding(vertical = dimensionResource(id = R.dimen.short2_dp))
                .fillMaxWidth(0.5f),
            onClick = onStartClicked
        )
    }
}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun PreviewStartView() {
    OrtografiaMariaMelTheme {
        StartView(
            modifier = Modifier.fillMaxSize(),
            onStartClicked = {}
        )
    }
}