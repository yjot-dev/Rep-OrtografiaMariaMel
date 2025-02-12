package com.example.ortografiamariamel.ui.views

import android.content.res.Configuration
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.ortografiamariamel.R
import com.example.ortografiamariamel.ui.theme.OrtografiaMariaMelTheme
import com.example.ortografiamariamel.ui.views.utils.RecyclerButton

@Composable
fun UnitView(
    modifier: Modifier = Modifier,
    @DrawableRes imageId: Int,
    numberUnit: Int,
    onTitleClicked: () -> Unit,
    onActivityClicked: () -> Unit
){
    Column(
        modifier = modifier
            .background(MaterialTheme.colorScheme.onPrimary),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = imageId),
            modifier = Modifier.fillMaxHeight(0.7f),
            contentScale = ContentScale.Fit,
            contentDescription = null
        )
        Spacer(modifier = Modifier.sizeIn(
            minHeight = dimensionResource(R.dimen.short2_dp),
            maxHeight = dimensionResource(R.dimen.short4_dp)
        ))
        RecyclerButton(
            textButton = stringResource(id = R.string.title_unit) + " $numberUnit",
            colorButton = MaterialTheme.colorScheme.primary,
            colorBorder = MaterialTheme.colorScheme.primary,
            colorText = MaterialTheme.colorScheme.onPrimaryContainer,
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .sizeIn(
                    minHeight = dimensionResource(R.dimen.short7_dp),
                    maxHeight = dimensionResource(R.dimen.medium3_dp)
                ),
            onClick = onTitleClicked
        )
        Spacer(modifier = Modifier.sizeIn(
            minHeight = dimensionResource(R.dimen.short2_dp),
            maxHeight = dimensionResource(R.dimen.short4_dp)
        ))
        RecyclerButton(
            textButton = stringResource(id = R.string.activity_unit) + " $numberUnit",
            colorButton = MaterialTheme.colorScheme.primary,
            colorBorder = MaterialTheme.colorScheme.primary,
            colorText = MaterialTheme.colorScheme.onPrimaryContainer,
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .sizeIn(
                    minHeight = dimensionResource(R.dimen.short7_dp),
                    maxHeight = dimensionResource(R.dimen.medium3_dp)
                ),
            onClick = onActivityClicked
        )
    }
}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun PreviewUnitView() {
    OrtografiaMariaMelTheme {
        UnitView(
            modifier = Modifier.fillMaxSize(),
            imageId = R.drawable.unit_one,
            numberUnit = 1,
            onTitleClicked = {},
            onActivityClicked = {}
        )
    }
}