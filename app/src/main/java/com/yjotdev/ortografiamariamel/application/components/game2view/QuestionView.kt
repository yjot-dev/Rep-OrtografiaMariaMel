package com.yjotdev.ortografiamariamel.application.components.game2view

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.constraintlayout.compose.ConstrainedLayoutReference
import androidx.constraintlayout.compose.ConstraintLayout
import kotlinx.coroutines.delay
import com.yjotdev.ortografiamariamel.R
import com.yjotdev.ortografiamariamel.domain.entity.Game2Entity

@Composable
fun QuestionView(
    modifier: Modifier = Modifier,
    success: Int,
    error: Int,
    finalResult: Int,
    questions: Game2Entity,
    onSuccess: (Int) -> Unit,
    onError: (Int) -> Unit,
    onFinishGame: (Boolean) -> Unit
){
    //Variables de la IU
    val listIsClicked = remember { mutableStateListOf(false, false, false, false) }
    val listIsEmptySpace1 = remember { mutableStateListOf(true, true, true, true) }
    val listIsEnabled = remember { mutableStateListOf(true, true, true, true) }
    val listItemsRef = remember { mutableStateListOf<ConstrainedLayoutReference>() }
    var indexJ by remember { mutableIntStateOf(-1) }
    //Verifica que las opciones correctas ya hayan sido seleccionadas
    LaunchedEffect(success){
        if(success == finalResult){
            delay(800)
            onFinishGame(true)
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
                    end.linkTo(emptySpace1.start)
                    bottom.linkTo(line1.top)
                },
                text = questions.question[0],
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                style = MaterialTheme.typography.bodyLarge
            )
            EmptySpace(modifier = Modifier.constrainAs(emptySpace1) {
                top.linkTo(parent.top)
                start.linkTo(text1.end)
                end.linkTo(text2.start)
                bottom.linkTo(line1.top)
            })
            Text(
                modifier = Modifier.constrainAs(text2){
                    top.linkTo(parent.top)
                    start.linkTo(emptySpace1.end)
                    end.linkTo(parent.end)
                    bottom.linkTo(line1.top)
                },
                text = questions.question[1],
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier
                .height(dimensionResource(id = R.dimen.short2_dp))
                .constrainAs(line1) {
                    top.linkTo(text1.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(text3.top)
                })
            EmptySpace(modifier = Modifier.constrainAs(emptySpace2){
                top.linkTo(line1.bottom)
                start.linkTo(parent.start)
                end.linkTo(text3.start)
                bottom.linkTo(line2.top)
            })
            Text(
                modifier = Modifier.constrainAs(text3){
                    top.linkTo(line1.bottom)
                    start.linkTo(emptySpace2.end)
                    end.linkTo(parent.end)
                    bottom.linkTo(line2.top)
                },
                text = questions.question[2],
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier
                .height(dimensionResource(id = R.dimen.short2_dp))
                .constrainAs(line2) {
                    top.linkTo(text3.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                })
            questions.listOptionsAnswers.forEachIndexed { indexI, item ->
                val optionRef = createRef()
                Option(
                    modifier = Modifier
                        .constrainAs(optionRef) {
                            if (listIsClicked[indexI]) {
                                //Mueve la opcion actual a uno de los 2 espacios vacios
                                if (listIsEmptySpace1[indexI]) {
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
                            }else{
                                //Mueve la opcion actual a su posicion original
                                top.linkTo(line2.bottom)
                                if (indexI == 0) start.linkTo(parent.start)
                                else start.linkTo(listItemsRef[indexI - 1].end)
                                bottom.linkTo(parent.bottom)
                            }
                            if(indexI > 0){
                                if(listIsClicked[indexI - 1]){
                                    //Reorganiza las opciones adyacentes
                                    top.linkTo(line2.bottom)
                                    when(indexJ){
                                        0 -> { if(indexI == 1) start.linkTo(parent.start) }
                                        1 -> { if(indexI == 2) start.linkTo(listItemsRef[0].end) }
                                        2 -> { if(indexI == 3) start.linkTo(listItemsRef[1].end) }
                                    }
                                    bottom.linkTo(parent.bottom)
                                }
                            }
                        }
                        .padding(dimensionResource(id = R.dimen.short2_dp)),
                    textItemN = item,
                    isEnableN = listIsEnabled[indexI],
                    onClicked = {
                        indexJ = indexI
                        //Verifica si las dos opciones ya han sido desabilitadas
                        if(listIsEnabled.count { !it } != 2) {
                            //Cambia el estado de la opcion clickeada
                            if (listIsClicked[indexI]) listIsClicked[indexI] = false
                            else listIsClicked[indexI] = true
                            /*
                            Verifica si el espacio vacio 2 esta disponible para que el usuario
                            seleccione la 2da opcion correcta.
                            */
                            if(listIsEnabled.count {!it} == 1){
                                listIsEnabled.forEachIndexed { i, it ->
                                    if(it && indexI != i) listIsClicked[i] = false
                                }
                                if(questions.listCorrectAnswers[1] == item){
                                    onSuccess(success + 1)
                                    listIsEnabled[indexI] = false
                                }else {
                                    onError(error + 1)
                                }
                            }
                            /*
                            Verifica si el espacio vacio 1 es verdadero antes de permitir que el
                            usuario seleccione la 1er opcion correcta.
                            */
                            if(listIsEnabled.count {!it} == 0){
                                listIsEnabled.forEachIndexed { i, it ->
                                    if(it && indexI != i) listIsClicked[i] = false
                                }
                                if(questions.listCorrectAnswers[0] == item) {
                                    onSuccess(success + 1)
                                    listIsEnabled[indexI] = false
                                    if (listIsEmptySpace1[indexI]) {
                                        listIsEmptySpace1.forEachIndexed { i, _ ->
                                            if (indexI != i) listIsEmptySpace1[i] = false
                                        }
                                    }
                                }else {
                                    onError(error + 1)
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