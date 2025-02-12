package com.example.ortografiamariamel.ui.views

import android.content.res.Configuration
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.example.ortografiamariamel.R
import com.example.ortografiamariamel.ui.theme.OrtografiaMariaMelTheme
import com.example.ortografiamariamel.ui.theme.Typography
import com.example.ortografiamariamel.ui.views.utils.RecyclerButton

//Edad minima y maxima del jugador
private const val minAge: Float = 11f
private const val maxAge: Float = 18f

@Composable
fun RegisterView(
    valueName: String,
    onValueChangeName: (String) -> Unit,
    valueAge: Float,
    onValueChangeAge: (Float) -> Unit,
    onNextClicked: () -> Unit,
    enabledNextButton: Boolean,
    modifier: Modifier = Modifier
){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.background(MaterialTheme.colorScheme.onPrimary)
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxHeight(0.87f)
                .fillMaxWidth(0.8f)
        ){
            Image(
                painter = painterResource(id = R.drawable.app_name),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier.fillMaxWidth(0.7f)
            )
            Image(
                painter = painterResource(id = R.drawable.app_pencil),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier.fillMaxHeight(0.5f)
            )
            WriteName(
                labelId = R.string.write_name,
                keyboardOption = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next),
                value = valueName,
                onValueChange = onValueChangeName
            )
            ChooseAge(
                labelId1 = R.string.choose_age,
                labelId2 = R.string.slider_age,
                value = valueAge,
                onValueChange = onValueChangeAge
            )
        }
        RecyclerButton(
            textButton = stringResource(R.string.button_next),
            isEnabled = enabledNextButton,
            modifier = Modifier
                .weight(1f)
                .padding(vertical = dimensionResource(id = R.dimen.short2_dp))
                .fillMaxWidth(0.5f),
            onClick = onNextClicked
        )
    }
}

@Composable
private fun WriteName(
    @StringRes labelId: Int,
    keyboardOption: KeyboardOptions,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.padding(dimensionResource(id = R.dimen.short1_dp))
    ){
        Text(
            text = stringResource(id = labelId),
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            style = Typography.titleLarge
        )
        TextField(
            value = value,
            onValueChange = onValueChange,
            keyboardOptions = keyboardOption,
            singleLine = true,
            modifier = Modifier.testTag("tag_write_name")
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ChooseAge(
    @StringRes labelId1: Int,
    @StringRes labelId2: Int,
    value: Float,
    onValueChange: (Float) -> Unit,
    modifier: Modifier = Modifier
){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.padding(dimensionResource(id = R.dimen.short1_dp))
    ){
        Text(
            text = stringResource(id = labelId1),
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            style = Typography.titleLarge
        )
        Slider(
            value = value,
            onValueChange = onValueChange,
            valueRange = minAge..maxAge,
            steps = (maxAge - minAge).toInt(),
            colors = SliderDefaults.colors(
                activeTrackColor = MaterialTheme.colorScheme.primaryContainer
            ),
            thumb = {
                Image(
                    painter = painterResource(id = R.drawable.logo_register),
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.fillMaxHeight(0.5f)
                )
            },
            modifier = Modifier.testTag("tag_choose_age")
        )
        Text(
            text = stringResource(id = labelId2, value.toInt()),
            color = MaterialTheme.colorScheme.primaryContainer,
            style = Typography.titleLarge
        )
    }
}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun PreviewRegisterView() {
    OrtografiaMariaMelTheme {
        RegisterView(
            modifier = Modifier.fillMaxSize(),
            valueName = "Yasser",
            onValueChangeName = {},
            valueAge = 11f,
            onValueChangeAge = {},
            onNextClicked = {},
            enabledNextButton = false
        )
    }
}