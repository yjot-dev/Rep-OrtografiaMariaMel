package com.yjotdev.ortografiamariamel.application.components.game3view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.yjotdev.ortografiamariamel.R
import com.yjotdev.ortografiamariamel.application.theme.OrtografiaMariaMelTheme
import com.yjotdev.ortografiamariamel.application.utils.ComponentPreview
import com.yjotdev.ortografiamariamel.domain.entity.Game3Entity

//Vista del juego de escribir la respuesta correcta
@Composable
fun GameOfAnswerWriting(
    modifier: Modifier = Modifier,
    success: Int,
    error: Int,
    finalResult: Int,
    listQuestionsAnswers: List<Game3Entity> = emptyList(),
    onFinishGame: (Boolean) -> Unit,
    onSuccess: (Int) -> Unit,
    onError: (Int) -> Unit
){
    val scrollState = rememberScrollState()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.verticalScroll(scrollState)
    ){
        Text(
            text = stringResource(id = R.string.game_title_3),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(id = R.dimen.short3_dp))
                .shadow(
                    elevation = dimensionResource(id = R.dimen.short3_dp),
                    shape = RoundedCornerShape(dimensionResource(id = R.dimen.short3_dp))
                )
                .background(MaterialTheme.colorScheme.onPrimary.copy(0.7f))
        )
        listQuestionsAnswers.forEach { item ->
            QuestionView(
                success = success,
                error = error,
                finalResult = finalResult,
                questions = item,
                onFinishGame = onFinishGame,
                onSuccess = onSuccess,
                onError = onError,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(id = R.dimen.short3_dp))
            )
        }
    }
}

@ComponentPreview
@Composable
private fun PreviewGameOfAnswerWriting() {
    OrtografiaMariaMelTheme {
        GameOfAnswerWriting(
            modifier = Modifier.fillMaxSize(),
            success = 3,
            error = 1,
            finalResult = 12,
            onFinishGame = {},
            onSuccess = {},
            onError = {}
        )
    }
}