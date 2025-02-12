package com.example.ortografiamariamel.ui.views

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.ortografiamariamel.R
import com.example.ortografiamariamel.listUnitsGame2
import com.example.ortografiamariamel.ui.model.Game2Model
import com.example.ortografiamariamel.ui.model.PlayerModel
import com.example.ortografiamariamel.ui.theme.OrtografiaMariaMelTheme
import com.example.ortografiamariamel.ui.theme.Typography
import com.example.ortografiamariamel.ui.viewModel.Game2ViewModel
import com.example.ortografiamariamel.ui.viewModel.PlayerViewModel
import com.example.ortografiamariamel.ui.views.utils.RecyclerButton
import kotlinx.coroutines.delay

//Vista del juego de seleccionar y ubicar respuesta correcta
@Composable
fun GameOfAnswerSelection(
    modifier: Modifier = Modifier,
    vmPlayer: PlayerViewModel = PlayerViewModel(),
    vmGame: Game2ViewModel = Game2ViewModel(),
    questionIndex: Int,
    finalResult: Int,
    listQuestionsAnswers: List<Game2Model>
){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxWidth(0.9f)
    ){
        Text(
            text = stringResource(id = R.string.game_title_2),
            style = Typography.titleLarge,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            modifier = Modifier
                .padding(dimensionResource(id = R.dimen.short2_dp))
                .shadow(
                    elevation = dimensionResource(id = R.dimen.short3_dp),
                    shape = RoundedCornerShape(dimensionResource(id = R.dimen.short3_dp))
                )
                .background(MaterialTheme.colorScheme.onPrimary.copy(0.7f))
        )
        Spacer(modifier = Modifier.sizeIn(
            minHeight = dimensionResource(R.dimen.short3_dp),
            maxHeight = dimensionResource(R.dimen.short5_dp)
        ))
        Card(colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.onPrimary
                    .copy(0.7f))
        ){
            ListQuestionView(
                vmPlayer = vmPlayer,
                vmGame = vmGame,
                questionIndex = questionIndex,
                finalResult = finalResult,
                listQuestions = listQuestionsAnswers,
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.short3_dp)))
        }
    }
}

@Composable
private fun ListQuestionView(
    modifier: Modifier = Modifier,
    vmPlayer: PlayerViewModel,
    vmGame: Game2ViewModel,
    questionIndex: Int,
    finalResult: Int,
    listQuestions: List<Game2Model>
){
    val uiStatePlayer by vmPlayer.uiState.collectAsState()
    val uiStateGame by vmGame.uiState.collectAsState()
    //Verifica que las opciones correctas ya hayan sido seleccionadas
    LaunchedEffect(uiStatePlayer.success){
        if(uiStatePlayer.success == finalResult){
            delay(800)
            vmPlayer.setFinishGame(true)
        }
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.fillMaxWidth()
        ){
            val currentAnswer = if(uiStateGame.indexAnswer1 != -1) listQuestions[questionIndex]
                .listOptionsAnswers[uiStateGame.indexAnswer1] else ""
            Text(
                text = listQuestions[questionIndex].question[0],
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                style = Typography.bodyLarge)
            ItemOption(
                isVisibleN = uiStateGame.visibleAnswer1,
                isEnableN = uiStateGame.enableAnswer1,
                textItemN = currentAnswer,
                onClicked = {
                    //Recupera la posicion del item seleccionado de abajo
                    val currentIndex = uiStateGame.indexAnswer1
                    //Muestra la opcion de abajo donde provino el item
                    vmGame.setListVisible(true, currentIndex)
                    //Oculta la opcion de arriba
                    vmGame.setVisibleAnswer1(false)
                }
            )
            Text(
                text = listQuestions[questionIndex].question[1],
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                style = Typography.bodyLarge
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.fillMaxWidth()
        ){
            val currentAnswer = if(uiStateGame.indexAnswer2 != -1) listQuestions[questionIndex]
                .listOptionsAnswers[uiStateGame.indexAnswer2] else ""
            ItemOption(
                isVisibleN = uiStateGame.visibleAnswer2,
                isEnableN = uiStateGame.enableAnswer2,
                textItemN = currentAnswer,
                onClicked = {
                    //Recupera la posicion del item seleccionado de abajo
                    val currentIndex = uiStateGame.indexAnswer2
                    //Muestra la opcion de abajo donde provino el item
                    vmGame.setListVisible(true, currentIndex)
                    //Oculta la opcion de arriba
                    vmGame.setVisibleAnswer2(false)
                }
            )
            Text(
                text = listQuestions[questionIndex].question[2],
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                style = Typography.bodyLarge
            )
        }
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.short2_dp)))
        LazyRow(verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement
                .spacedBy(dimensionResource(id = R.dimen.short2_dp))){
            items(listQuestions[questionIndex].listOptionsAnswers){
                val index = listQuestions[questionIndex]
                    .listOptionsAnswers.indexOf(it)
                ItemOption(
                    isVisibleN = uiStateGame.listVisible[index],
                    textItemN = it,
                    onClicked = {
                        val currentIndex = listQuestions[questionIndex]
                            .listOptionsAnswers.indexOf(it) //Posicion actual del item
                        val currentItem = listQuestions[questionIndex]
                            .listOptionsAnswers[currentIndex] //Item actual
                        if(!uiStateGame.visibleAnswer1 && !uiStateGame.visibleAnswer2){
                            checkAnswer(id = 1, currentIndex = currentIndex,
                                correctItem = listQuestions[questionIndex].listCorrectAnswers[0],
                                currentItem = currentItem,vmGame = vmGame,vmPlayer = vmPlayer,
                                uiStatePlayer = uiStatePlayer)
                            vmGame.setVisibleAnswer1(true)
                        }else if(!uiStateGame.visibleAnswer1){
                            checkAnswer(id = 1, currentIndex = currentIndex,
                                correctItem = listQuestions[questionIndex].listCorrectAnswers[0],
                                currentItem = currentItem,vmGame = vmGame,vmPlayer = vmPlayer,
                                uiStatePlayer = uiStatePlayer)
                            vmGame.setVisibleAnswer1(true)
                        }else if(!uiStateGame.visibleAnswer2){
                            checkAnswer(id = 2, currentIndex = currentIndex,
                                correctItem = listQuestions[questionIndex].listCorrectAnswers[1],
                                currentItem = currentItem,vmGame = vmGame,vmPlayer = vmPlayer,
                                uiStatePlayer = uiStatePlayer)
                            vmGame.setVisibleAnswer2(true)
                        }
                        //Oculta la opcion seleccionada de abajo
                        vmGame.setListVisible(false, currentIndex)
                    }
                )
            }
        }
    }
}

private fun checkAnswer(id: Int,currentIndex: Int,correctItem: String,
    currentItem: String,vmGame: Game2ViewModel,vmPlayer: PlayerViewModel,
    uiStatePlayer: PlayerModel){
    //Muestra el item seleccionado arriba
    when(id){
        1 -> vmGame.setIndexAnswer1(currentIndex)
        2 -> vmGame.setIndexAnswer2(currentIndex)
    }
    //Verifica la respuesta correcta
    if(correctItem == currentItem){
        vmPlayer.setSuccess(uiStatePlayer.success + 1)
        when(id){
            1 -> vmGame.setIsEnableAnswer1(false)
            2 -> vmGame.setIsEnableAnswer2(false)
        }
    }else vmPlayer.setError(uiStatePlayer.error + 1)
}

@Composable
private fun ItemOption(
    isVisibleN: Boolean,
    textItemN: String,
    isEnableN: Boolean = true,
    onClicked: () -> Unit
){
    if(isVisibleN){
        RecyclerButton(
            textButton = textItemN,
            colorButton = MaterialTheme.colorScheme.primary,
            colorText = MaterialTheme.colorScheme.onPrimaryContainer,
            isEnabled = isEnableN,
            onClick = onClicked,
            shape = RoundedCornerShape(dimensionResource(id = R.dimen.short4_dp)),
            modifier = Modifier.sizeIn(
                minHeight = dimensionResource(R.dimen.short6_dp),
                maxHeight = dimensionResource(R.dimen.short7_dp),
                minWidth = dimensionResource(id = R.dimen.short7_dp),
                maxWidth = dimensionResource(id = R.dimen.medium2_dp))
        )
    }else{
        Card(colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onPrimary
                .copy(0.8f)),
            border = BorderStroke(
                width = dimensionResource(id = R.dimen.short1_dp),
                color = MaterialTheme.colorScheme.tertiary
            ),
            shape = RoundedCornerShape(dimensionResource(id = R.dimen.short4_dp)),
            modifier = Modifier.sizeIn(
                minHeight = dimensionResource(R.dimen.short6_dp),
                maxHeight = dimensionResource(R.dimen.short7_dp),
                minWidth = dimensionResource(id = R.dimen.short7_dp),
                maxWidth = dimensionResource(id = R.dimen.medium2_dp))
        ){}
    }
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
            vmPlayer = PlayerViewModel(),
            vmGame = Game2ViewModel(),
            questionIndex = 0,
            finalResult = 8,
            listQuestionsAnswers = listUnitsGame2
        )
    }
}