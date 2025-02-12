package com.example.ortografiamariamel.ui.views

import android.content.res.Configuration
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.example.ortografiamariamel.listUnit1Game1
import com.example.ortografiamariamel.ui.model.Game1Model
import com.example.ortografiamariamel.ui.theme.OrtografiaMariaMelTheme
import com.example.ortografiamariamel.ui.theme.Typography
import com.example.ortografiamariamel.ui.viewModel.Game1ViewModel
import com.example.ortografiamariamel.ui.viewModel.PlayerViewModel
import com.example.ortografiamariamel.ui.views.utils.RecyclerButton
import kotlinx.coroutines.delay

//Vista del juego de pares de cartas
@Composable
fun GameOfCards(
    modifier: Modifier = Modifier,
    vmPlayer: PlayerViewModel = PlayerViewModel(),
    vmGame: Game1ViewModel = Game1ViewModel(),
    finalResult: Int,
    listPairOfCard: List<Game1Model>,
    listRandom: List<Game1Model>
){
    val uiStatePlayer by vmPlayer.uiState.collectAsState()
    val uiStateGame by vmGame.uiState.collectAsState()
    //Verifica que todos los pares de tarjetas ya hayan sido seleccionados
    LaunchedEffect(key1 = uiStatePlayer.success){
        if(uiStatePlayer.success == finalResult){
            delay(800)
            vmPlayer.setFinishGame(true)
        }
    }
    //Limpia los pares incorrectos despues de un tiempo
    LaunchedEffect(key1 = uiStatePlayer.error){
        if(uiStateGame.indexCard1 > -1 && uiStateGame.indexCard2 > -1){
            delay(200)
            vmGame.setIncorrectCard1(false, uiStateGame.indexCard1)
            vmGame.setIncorrectCard2(false, uiStateGame.indexCard2)
            vmGame.setSelectedCard1(false, uiStateGame.indexCard1)
            vmGame.setSelectedCard2(false, uiStateGame.indexCard2)
            vmGame.setIndexCard1(-1)
            vmGame.setIndexCard2(-1)
        }
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxWidth(0.8f)
    ){
        Text(
            text = stringResource(id = R.string.game_title_1),
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
        Row{
            LazyColumn(
                verticalArrangement = Arrangement
                    .spacedBy(dimensionResource(id = R.dimen.short2_dp)),
                modifier = Modifier.fillMaxWidth(0.35f)
            ){
                items(listPairOfCard){ item ->
                    val index = listPairOfCard.indexOf(item)
                    ItemCard(
                        textN = listPairOfCard[index].card1,
                        isCorrectN = uiStateGame.listCorrectCard1[index],
                        isIncorrectN = uiStateGame.listIncorrectCard1[index],
                        isSelectedN = uiStateGame.listSelectedCard1[index],
                        modifier = Modifier
                            .sizeIn(
                                minHeight = dimensionResource(R.dimen.short7_dp),
                                maxHeight = dimensionResource(R.dimen.medium1_dp)
                            )
                            .fillMaxWidth(),
                        onClick = {
                            val index1 = listPairOfCard.indexOf(item)
                            vmGame.setIndexCard1(listPairOfCard.indexOf(item))
                            val index2 = uiStateGame.indexCard2
                            //Deselecciona y desmarca los errores
                            uiStateGame.listSelectedCard1.forEachIndexed { index, it ->
                                if(it) vmGame.setSelectedCard1(false, index)
                            }
                            vmGame.setSelectedCard1(true, index1)
                            if(index2 != -1){
                                if(listPairOfCard[index1].idCard1 == listRandom[index2].idCard2){
                                    vmPlayer.setSuccess(uiStatePlayer.success + 1)
                                    vmGame.setCorrectCard1(false, index1)
                                    vmGame.setCorrectCard2(false, index2)
                                    vmGame.setIndexCard1(-1)
                                    vmGame.setIndexCard2(-1)
                                }else {
                                    vmPlayer.setError(uiStatePlayer.error + 1)
                                    vmGame.setIncorrectCard1(true, index1)
                                    vmGame.setIncorrectCard2(true, index2)
                                }
                            }
                        })
                }
            }
            Spacer(modifier = Modifier.fillMaxWidth(0.05f))
            LazyColumn(
                verticalArrangement = Arrangement
                    .spacedBy(dimensionResource(id = R.dimen.short2_dp))
            ){
                items(listRandom){ item ->
                    val index = listRandom.indexOf(item)
                    ItemCard(
                        textN = listRandom[index].card2,
                        isCorrectN = uiStateGame.listCorrectCard2[index],
                        isIncorrectN = uiStateGame.listIncorrectCard2[index],
                        isSelectedN = uiStateGame.listSelectedCard2[index],
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {
                            val index2 = listRandom.indexOf(item)
                            vmGame.setIndexCard2(listRandom.indexOf(item))
                            val index1 = uiStateGame.indexCard1
                            //Deselecciona y desmarca los errores
                            uiStateGame.listSelectedCard2.forEachIndexed { index, it ->
                                if(it) vmGame.setSelectedCard2(false, index)
                            }
                            vmGame.setSelectedCard2(true, index2)
                            if(index1 != -1){
                                if(listPairOfCard[index1].idCard1 == listRandom[index2].idCard2){
                                    vmPlayer.setSuccess(uiStatePlayer.success + 1)
                                    vmGame.setCorrectCard1(false, index1)
                                    vmGame.setCorrectCard2(false, index2)
                                    vmGame.setIndexCard2(-1)
                                    vmGame.setIndexCard1(-1)
                                }else {
                                    vmPlayer.setError(uiStatePlayer.error + 1)
                                    vmGame.setIncorrectCard1(true, index1)
                                    vmGame.setIncorrectCard2(true, index2)
                                }
                            }
                        })
                }
            }
        }
    }
}

@Composable
private fun ItemCard(
    modifier: Modifier,
    textN: String,
    isCorrectN: Boolean,
    isIncorrectN: Boolean,
    isSelectedN: Boolean,
    onClick: ()-> Unit,
){
    val selectedColor by animateColorAsState(
        if (isSelectedN) MaterialTheme.colorScheme.primaryContainer
        else MaterialTheme.colorScheme.primary, label = "")
    val incorrectColor by animateColorAsState(
        if (isIncorrectN) MaterialTheme.colorScheme.error
        else MaterialTheme.colorScheme.tertiary, label = "")
    RecyclerButton(
        textButton = textN,
        isEnabled = isCorrectN,
        colorButton = selectedColor,
        colorBorder = incorrectColor,
        colorText = MaterialTheme.colorScheme.onPrimaryContainer,
        modifier = modifier,
        onClick = onClick
    )
}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun PreviewGame1View() {
    OrtografiaMariaMelTheme {
        GameOfCards(
            modifier = Modifier.fillMaxSize(),
            vmPlayer = PlayerViewModel(),
            vmGame = Game1ViewModel(),
            finalResult = 6,
            listPairOfCard = listUnit1Game1,
            listRandom = listUnit1Game1.shuffled()
        )
    }
}
