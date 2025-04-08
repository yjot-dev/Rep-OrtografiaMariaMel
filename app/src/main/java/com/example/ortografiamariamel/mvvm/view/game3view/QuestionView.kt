package com.example.ortografiamariamel.mvvm.view.game3view

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import kotlinx.coroutines.delay
import com.example.ortografiamariamel.R
import com.example.ortografiamariamel.composable.RecyclerButton
import com.example.ortografiamariamel.mvvm.model.Game3Model
import com.example.ortografiamariamel.mvvm.viewmodel.PlayerViewModel
import com.example.ortografiamariamel.theme.Typography

@Composable
fun QuestionView(
    modifier: Modifier = Modifier,
    vmPlayer: PlayerViewModel,
    finalResult: Int,
    questions: Game3Model
){
    val uiStatePlayer by vmPlayer.uiState.collectAsState()
    //Variables de la IU
    var answer by remember { mutableStateOf("") }
    var enabled by remember { mutableStateOf(true) }
    var defaultColor by remember { mutableStateOf(true) }
    val colorBorder by animateColorAsState(
        when(defaultColor){
            true -> { MaterialTheme.colorScheme.tertiary }
            false -> { MaterialTheme.colorScheme.error }
        }, label = "ColorAnimation3"
    )
    //Verifica que las opciones correctas ya hayan sido seleccionadas
    LaunchedEffect(
        key1 = uiStatePlayer.success,
        key2 = uiStatePlayer.error
    ){
        if(uiStatePlayer.success == finalResult) {
            delay(800)
            vmPlayer.setFinishGame(true)
        }
        if(uiStatePlayer.error >= 1) {
            delay(200)
            defaultColor = true
        }
    }
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onPrimary
                .copy(0.7f)),
        modifier = modifier
    ){
        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(
                modifier = Modifier.padding(dimensionResource(id = R.dimen.short2_dp)),
                text = questions.question,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                style = Typography.bodyLarge
            )
            TextField(
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.short2_dp))
                    .fillMaxWidth(0.7f),
                value = answer,
                onValueChange = { answer = it }
            )
            RecyclerButton(
                modifier = Modifier
                    .padding(vertical = dimensionResource(id = R.dimen.short2_dp))
                    .fillMaxWidth(0.7f),
                textButton = stringResource(R.string.button_check),
                isEnabled = enabled,
                colorBorder = colorBorder,
                onClick = {
                    if(answer.lowercase() == questions.correctAnswer.lowercase()){
                        vmPlayer.setSuccess(uiStatePlayer.success + 1)
                        enabled = false
                    }else{
                        vmPlayer.setError(uiStatePlayer.error + 1)
                        defaultColor = false
                    }
                }
            )
        }
    }
}