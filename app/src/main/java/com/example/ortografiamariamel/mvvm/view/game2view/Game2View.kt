package com.example.ortografiamariamel.mvvm.view.game2view

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.ortografiamariamel.R
import com.example.ortografiamariamel.mvvm.model.Game2Model
import com.example.ortografiamariamel.theme.OrtografiaMariaMelTheme
import com.example.ortografiamariamel.theme.Typography
import com.example.ortografiamariamel.mvvm.viewmodel.PlayerViewModel
import com.example.ortografiamariamel.composable.RecyclerButton

//Vista del juego de seleccionar y ubicar respuesta correcta
@Composable
fun GameOfAnswerSelection(
    modifier: Modifier = Modifier,
    vmPlayer: PlayerViewModel = PlayerViewModel(),
    finalResult: Int,
    listQuestionsAnswers: List<Game2Model> = vmPlayer.listUnit1Game2
){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
    ){
        Text(
            text = stringResource(id = R.string.game_title_2),
            style = Typography.titleLarge,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(id = R.dimen.short2_dp))
                .shadow(
                    elevation = dimensionResource(id = R.dimen.short3_dp),
                    shape = RoundedCornerShape(dimensionResource(id = R.dimen.short3_dp))
                )
                .background(MaterialTheme.colorScheme.onPrimary.copy(0.7f))
        )
        listQuestionsAnswers.forEach { item ->
            QuestionView(
                vmPlayer = vmPlayer,
                finalResult = finalResult,
                questions = item,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(id = R.dimen.short3_dp))
            )
        }
    }
}

@Composable
fun EmptySpace(modifier: Modifier = Modifier){
    Card(colors = CardDefaults.cardColors(
        containerColor = MaterialTheme.colorScheme.onPrimary
            .copy(0.8f)),
        border = BorderStroke(
            width = dimensionResource(id = R.dimen.short1_dp),
            color = MaterialTheme.colorScheme.tertiary
        ),
        shape = RoundedCornerShape(dimensionResource(id = R.dimen.short4_dp)),
        modifier = modifier
            .height(dimensionResource(R.dimen.short6_dp))
            .width(dimensionResource(R.dimen.medium1_dp)),
        content = {}
    )
}

@Composable
fun Option(
    modifier: Modifier = Modifier,
    textItemN: String,
    isEnableN: Boolean = true,
    onClicked: () -> Unit
){
    RecyclerButton(
        textButton = textItemN,
        colorButton = MaterialTheme.colorScheme.primary,
        colorText = MaterialTheme.colorScheme.onPrimaryContainer,
        widthBorder = dimensionResource(id = R.dimen.short1_dp),
        isEnabled = isEnableN,
        onClick = onClicked,
        shape = RoundedCornerShape(dimensionResource(id = R.dimen.short4_dp)),
        modifier = modifier
            .height(dimensionResource(R.dimen.short6_dp))
            .width(dimensionResource(R.dimen.medium1_dp))
    )
}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun PreviewGame2View() {
    OrtografiaMariaMelTheme {
        GameOfAnswerSelection(
            modifier = Modifier.fillMaxSize(),
            finalResult = 10
        )
    }
}