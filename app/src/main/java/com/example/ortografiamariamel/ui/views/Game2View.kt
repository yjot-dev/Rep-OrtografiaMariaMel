package com.example.ortografiamariamel.ui.views

import android.content.res.Configuration
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstrainedLayoutReference
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.ortografiamariamel.R
import com.example.ortografiamariamel.listUnit1Game2
import com.example.ortografiamariamel.ui.model.Game2Model
import com.example.ortografiamariamel.ui.theme.OrtografiaMariaMelTheme
import com.example.ortografiamariamel.ui.theme.Typography
import com.example.ortografiamariamel.ui.viewModel.PlayerViewModel
import com.example.ortografiamariamel.ui.views.utils.RecyclerButton
import kotlinx.coroutines.delay

//Vista del juego de seleccionar y ubicar respuesta correcta
@Composable
fun GameOfAnswerSelection(
    modifier: Modifier = Modifier,
    vmPlayer: PlayerViewModel = PlayerViewModel(),
    finalResult: Int,
    listQuestionsAnswers: List<Game2Model>
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
private fun QuestionView(
    modifier: Modifier = Modifier,
    vmPlayer: PlayerViewModel,
    finalResult: Int,
    questions: Game2Model
){
    val uiStatePlayer by vmPlayer.uiState.collectAsState()
    //Variables de la IU
    val listIsClicked = remember { mutableStateListOf(false, false, false, false) }
    val listIsEmptySpace1 = remember { mutableStateListOf(true, true, true, true) }
    val listIsEnabled = remember { mutableStateListOf(true, true, true, true) }
    val listItemsRef = remember { mutableStateListOf<ConstrainedLayoutReference>() }
    var index1 by remember { mutableIntStateOf(0) }
    var index2 by remember { mutableIntStateOf(0) }
    //Verifica que las opciones correctas ya hayan sido seleccionadas
    LaunchedEffect(uiStatePlayer.success){
        if(uiStatePlayer.success == finalResult){
            delay(800)
            vmPlayer.setFinishGame(true)
        }
    }
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onPrimary
                .copy(0.7f)),
        modifier = modifier
    ){
        ConstraintLayout(
            animateChangesSpec = spring(
                dampingRatio = Spring.DampingRatioLowBouncy,
                stiffness = Spring.StiffnessLow
            ),
            modifier = Modifier
        ){
            val (text1, emptySpace1, text2, line1,
                emptySpace2, text3, line2) = createRefs()
            Text(
                modifier = Modifier.constrainAs(text1){
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    if (!listIsClicked[index1]) end.linkTo(emptySpace1.start)
                    else end.linkTo(listItemsRef[index1].start)
                    bottom.linkTo(line1.top)
                },
                text = questions.question[0],
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                style = Typography.bodyLarge
            )
            //Cambia la posicion del espacio vacio hacia la opcion
            EmptySpace(modifier = Modifier.constrainAs(emptySpace1) {
                if(listIsClicked[index1]) {
                    top.linkTo(line2.bottom)
                    when (index1) {
                        0 -> {
                            start.linkTo(parent.start)
                            end.linkTo(listItemsRef[1].start)
                        }
                        questions.listOptionsAnswers.size - 1 -> {
                            start.linkTo(listItemsRef[index1 - 1].end)
                            end.linkTo(parent.end)
                        }
                        else -> {
                            start.linkTo(listItemsRef[index1 - 1].end)
                            end.linkTo(listItemsRef[index1 + 1].start)
                        }
                    }
                    bottom.linkTo(parent.bottom)
                }else {
                    top.linkTo(parent.top)
                    start.linkTo(text1.end)
                    end.linkTo(text2.start)
                    bottom.linkTo(line1.top)
                }
            })
            Text(
                modifier = Modifier.constrainAs(text2){
                    top.linkTo(parent.top)
                    if (!listIsClicked[index1]) start.linkTo(emptySpace1.end)
                    else start.linkTo(listItemsRef[index1].end)
                    end.linkTo(parent.end)
                    bottom.linkTo(line1.top)
                },
                text = questions.question[1],
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                style = Typography.bodyLarge
            )
            Spacer(modifier = Modifier
                .height(dimensionResource(id = R.dimen.short2_dp))
                .constrainAs(line1) {
                    top.linkTo(text1.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(text3.top)
                })
            //Cambia la posicion del espacio vacio hacia la opcion
            EmptySpace(modifier = Modifier.constrainAs(emptySpace2){
                if (listIsClicked[index2]) {
                    top.linkTo(line2.bottom)
                    when (index2) {
                        0 -> {
                            start.linkTo(parent.start)
                            end.linkTo(listItemsRef[1].start)
                        }
                        questions.listOptionsAnswers.size - 1 -> {
                            start.linkTo(listItemsRef[index2 - 1].end)
                            end.linkTo(parent.end)
                        }
                        else -> {
                            start.linkTo(listItemsRef[index2 - 1].end)
                            end.linkTo(listItemsRef[index2 + 1].start)
                        }
                    }
                    bottom.linkTo(parent.bottom)
                }else {
                    top.linkTo(line1.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(text3.start)
                    bottom.linkTo(line2.top)
                }
            })
            Text(
                modifier = Modifier.constrainAs(text3){
                    top.linkTo(line1.bottom)
                    if (!listIsClicked[index2]) start.linkTo(emptySpace2.end)
                    else start.linkTo(listItemsRef[index2].end)
                    end.linkTo(parent.end)
                    bottom.linkTo(line2.top)
                },
                text = questions.question[2],
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                style = Typography.bodyLarge
            )
            Spacer(modifier = Modifier
                .height(dimensionResource(id = R.dimen.short2_dp))
                .constrainAs(line2) {
                    top.linkTo(text3.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                })
            questions.listOptionsAnswers.forEachIndexed { index, item ->
                val optionRef = createRef()
                Option(
                    modifier = Modifier
                        .constrainAs(optionRef) {
                            //Cambia la posicion de la opcion hacia el espacio vacio
                            if (listIsClicked[index]) {
                                if (listIsEmptySpace1[index]) {
                                    top.linkTo(parent.top)
                                    start.linkTo(text1.end)
                                    end.linkTo(text2.start)
                                    bottom.linkTo(line1.top)
                                } else {
                                    top.linkTo(line1.bottom)
                                    start.linkTo(parent.start)
                                    end.linkTo(text3.start)
                                    bottom.linkTo(line2.top)
                                }
                            } else {
                                top.linkTo(line2.bottom)
                                if (index == 0) start.linkTo(parent.start)
                                else start.linkTo(listItemsRef[index - 1].end)
                                bottom.linkTo(parent.bottom)
                            }
                        }
                        .padding(dimensionResource(id = R.dimen.short2_dp)),
                    textItemN = item,
                    isEnableN = listIsEnabled[index],
                    onClicked = {
                        //Verifica si las dos opciones ya han sido desabilitadas
                        if(listIsEnabled.count { !it } != 2) {
                            //Cambia el estado de la opcion clickeada
                            if (listIsClicked[index]) listIsClicked[index] = false
                            else listIsClicked[index] = true
                            /*
                            Verifica si el espacio vacio 2 esta disponible para que el usuario
                            seleccione la 2da opcion correcta.
                            */
                            if(listIsEnabled.count {!it} == 1){
                                index2 = index
                                listIsEnabled.forEachIndexed { i, it ->
                                    if(it && index != i) listIsClicked[i] = false
                                }
                                if(questions.listCorrectAnswers[1] == item){
                                    vmPlayer.setSuccess(uiStatePlayer.success + 1)
                                    listIsEnabled[index] = false
                                }else {
                                    vmPlayer.setError(uiStatePlayer.error + 1)
                                }
                            }
                            /*
                            Verifica si el espacio vacio 1 es verdadero antes de permitir que el
                            usuario seleccione la 1er opcion correcta.
                            */
                            if(listIsEnabled.count {!it} == 0){
                                index1 = index
                                listIsEnabled.forEachIndexed { i, it ->
                                    if(it && index != i) listIsClicked[i] = false
                                }
                                if(questions.listCorrectAnswers[0] == item) {
                                    vmPlayer.setSuccess(uiStatePlayer.success + 1)
                                    listIsEnabled[index] = false
                                    if (listIsEmptySpace1[index]) {
                                        listIsEmptySpace1.forEachIndexed { i, _ ->
                                            if (index != i) listIsEmptySpace1[i] = false
                                        }
                                    }
                                }else {
                                    vmPlayer.setError(uiStatePlayer.error + 1)
                                }
                            }
                        }
                    }
                )
                listItemsRef.add(optionRef)
            }
        }
    }
}

@Composable
private fun EmptySpace(modifier: Modifier = Modifier){
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
private fun Option(
    modifier: Modifier = Modifier,
    textItemN: String,
    isEnableN: Boolean = true,
    onClicked: () -> Unit
){
    RecyclerButton(
        textButton = textItemN,
        colorButton = MaterialTheme.colorScheme.primary,
        colorText = MaterialTheme.colorScheme.onPrimaryContainer,
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
            finalResult = 10,
            listQuestionsAnswers = listUnit1Game2
        )
    }
}