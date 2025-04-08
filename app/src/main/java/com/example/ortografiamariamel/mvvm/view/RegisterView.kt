package com.example.ortografiamariamel.mvvm.view

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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import com.example.ortografiamariamel.theme.OrtografiaMariaMelTheme
import com.example.ortografiamariamel.theme.Typography
import com.example.ortografiamariamel.mvvm.viewmodel.PlayerViewModel
import com.example.ortografiamariamel.composable.RecyclerButton

//Edad minima y maxima del jugador
private const val minAge: Float = 11f
private const val maxAge: Float = 18f

@Composable
fun RegisterView(
    vmPlayer: PlayerViewModel,
    onNextClicked: () -> Unit,
    modifier: Modifier = Modifier
){
    //Variables de la IU
    val uiStatePlayer by vmPlayer.uiState.collectAsState()
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
            value = uiStatePlayer.name,
            onValueChange = { vmPlayer.setName(it) },
            modifier = Modifier.fillMaxWidth(0.7f)
        )
        ChooseAge(
            labelId1 = R.string.choose_age,
            labelId2 = R.string.slider_age,
            value = uiStatePlayer.age.toFloat(),
            onValueChange = { vmPlayer.setAge(it.toInt()) },
            modifier = Modifier.fillMaxWidth(0.7f)
        )
        RecyclerButton(
            textButton = stringResource(R.string.button_next),
            isEnabled = uiStatePlayer.name.isNotBlank(),
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
        style = Typography.titleLarge
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
    Text(
        text = stringResource(id = labelId1),
        color = MaterialTheme.colorScheme.onPrimaryContainer,
        style = Typography.titleLarge,
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
        style = Typography.titleLarge,
        modifier = modifier
    )
}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun PreviewRegisterView() {
    OrtografiaMariaMelTheme {
        RegisterView(
            vmPlayer = PlayerViewModel(),
            onNextClicked = {},
            modifier = Modifier.fillMaxSize()
        )
    }
}