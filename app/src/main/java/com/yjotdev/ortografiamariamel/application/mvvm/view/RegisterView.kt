package com.yjotdev.ortografiamariamel.application.mvvm.view

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
import com.yjotdev.ortografiamariamel.R
import com.yjotdev.ortografiamariamel.application.theme.OrtografiaMariaMelTheme
import com.yjotdev.ortografiamariamel.application.components.RecyclerButton
import com.yjotdev.ortografiamariamel.application.utils.ComponentPreview

@Composable
fun RegisterView(
    name: String,
    age: Int,
    onName: (String) -> Unit,
    onAge: (Int) -> Unit,
    onNextClicked: () -> Unit,
    modifier: Modifier = Modifier
){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = modifier.background(MaterialTheme.colorScheme.onPrimary)
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
            contentScale = ContentScale.Fit
        )
        WriteName(
            labelId = R.string.write_name,
            keyboardOption = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next),
            value = name,
            onValueChange = { onName(it) },
            modifier = Modifier.fillMaxWidth(0.7f)
        )
        ChooseAge(
            labelId1 = R.string.choose_age,
            labelId2 = R.string.slider_age,
            value = age.toFloat(),
            onValueChange = { onAge(it.toInt()) },
            modifier = Modifier.fillMaxWidth(0.7f)
        )
        RecyclerButton(
            textButton = stringResource(R.string.button_next),
            isEnabled = name.isNotBlank(),
            modifier = Modifier
                .padding(vertical = dimensionResource(id = R.dimen.short2_dp))
                .fillMaxWidth(0.7f),
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
    Text(
        modifier = modifier,
        text = stringResource(id = labelId),
        color = MaterialTheme.colorScheme.onPrimaryContainer,
        style = MaterialTheme.typography.titleLarge
    )
    TextField(
        value = value,
        onValueChange = onValueChange,
        keyboardOptions = keyboardOption,
        singleLine = true,
        modifier = modifier.testTag("tag_write_name")
    )
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
    //Edad minima y maxima del jugador
    val minAge = 11f
    val maxAge = 18f
    Text(
        text = stringResource(id = labelId1),
        color = MaterialTheme.colorScheme.onPrimaryContainer,
        style = MaterialTheme.typography.titleLarge,
        modifier = modifier
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
        modifier = modifier.testTag("tag_choose_age")
    )
    Text(
        text = stringResource(id = labelId2, value.toInt()),
        color = MaterialTheme.colorScheme.primaryContainer,
        style = MaterialTheme.typography.titleLarge,
        modifier = modifier
    )
}

@ComponentPreview
@Composable
private fun PreviewRegisterView() {
    OrtografiaMariaMelTheme {
        RegisterView(
            name = "Carlos",
            age = 11,
            onName = {},
            onAge = {},
            onNextClicked = {},
            modifier = Modifier.fillMaxSize()
        )
    }
}