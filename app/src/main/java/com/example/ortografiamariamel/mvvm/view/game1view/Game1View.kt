package com.example.ortografiamariamel.mvvm.view.game1view

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.delay
import com.example.ortografiamariamel.R
import com.example.ortografiamariamel.mvvm.model.Game1Model
import com.example.ortografiamariamel.theme.OrtografiaMariaMelTheme
import com.example.ortografiamariamel.theme.Typography
import com.example.ortografiamariamel.mvvm.viewmodel.PlayerViewModel

//Vista del juego de pares de cartas
@Composable
fun GameOfCards(
    modifier: Modifier = Modifier,
    vmPlayer: PlayerViewModel = PlayerViewModel(),
    finalResult: Int,
    listPairOfCard: List<Game1Model> = vmPlayer.listUnit1Game1
){
    val uiStatePlayer by vmPlayer.uiState.collectAsState()
    // Dividir la lista en dos sublistas
    val halfIndex = listPairOfCard.size / 2
    val firstColumnItems = listPairOfCard.take(halfIndex)
    val secondColumnItems = remember(listPairOfCard){
        listPairOfCard.drop(halfIndex).shuffled()
    }
    //Variables de la IU
    var previousIndex1 by remember { mutableIntStateOf(-1) }
    var previousIndex2 by remember { mutableIntStateOf(-1) }
    var indexCard1 by remember { mutableIntStateOf(-1) }
    var indexCard2 by remember { mutableIntStateOf(-1) }
    val correctCardList1 = remember { mutableListOf(true, true, true, true, true, true) }
    val correctCardList2 = remember { mutableListOf(true, true, true, true, true, true) }
    val incorrectCardList1 = remember { mutableListOf(false, false, false, false, false, false) }
    val incorrectCardList2 = remember { mutableListOf(false, false, false, false, false, false) }
    val selectedCardList1 = remember { mutableListOf(false, false, false, false, false, false) }
    val selectedCardList2 = remember { mutableListOf(false, false, false, false, false, false) }
    LaunchedEffect(
        key1 = indexCard1,
        key2 = indexCard2
    ){
        when{
            //Verifica que todos los pares de cartas ya hayan sido seleccionados
            uiStatePlayer.success == finalResult -> {
                delay(800L)
                vmPlayer.setFinishGame(true)
            }
            //Verifica que se hayan seleccionado dos cartas
            indexCard1 != -1 && indexCard2 != -1 -> {
                if(firstColumnItems[indexCard1].idCard == secondColumnItems[indexCard2].idCard) {
                    vmPlayer.setSuccess(uiStatePlayer.success + 1)
                    correctCardList1[indexCard1] = false
                    correctCardList2[indexCard2] = false
                }else {
                    vmPlayer.setError(uiStatePlayer.error + 1)
                    incorrectCardList1[indexCard1] = true
                    incorrectCardList2[indexCard2] = true
                }
                indexCard1 = -1
                indexCard2 = -1
            }
            //Verifica que se hayan deseleccionado las cartas
            indexCard1 == -1 && indexCard2 == -1 -> {
                delay(200L)
                incorrectCardList1.forEach { item ->
                    val i = incorrectCardList1.indexOf(item)
                    if(item) incorrectCardList1[i] = false
                }
                incorrectCardList2.forEach { item ->
                    val i = incorrectCardList2.indexOf(item)
                    if(item) incorrectCardList2[i] = false
                }
            }
        }
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
    ){
        Text(
            text = stringResource(id = R.string.game_title_1),
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
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ){
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.weight(0.4f)
            ){
                firstColumnItems.forEachIndexed { index1, _ ->
                    ItemCard(
                        textN = firstColumnItems[index1].textCard,
                        isCorrectN = correctCardList1[index1],
                        isIncorrectN = incorrectCardList1[index1],
                        isSelectedN = selectedCardList1[index1],
                        modifier = Modifier.fillMaxWidth(0.8f),
                        onClick = {
                            //Obtiene indice actual de la carta seleccionada
                            indexCard1 = index1
                            //Deselecciona la carta anterior
                            if(previousIndex1 != -1)
                                selectedCardList1[previousIndex1] = false
                            //Selecciona la carta actual
                            selectedCardList1[index1] = true
                            //Obtiene indice anterior de la carta seleccionada
                            previousIndex1 = index1
                        }
                    )
                }
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.weight(0.6f)
            ){
                secondColumnItems.forEachIndexed { index2, _ ->
                    ItemCard(
                        textN = secondColumnItems[index2].textCard,
                        isCorrectN = correctCardList2[index2],
                        isIncorrectN = incorrectCardList2[index2],
                        isSelectedN = selectedCardList2[index2],
                        modifier = Modifier.fillMaxWidth(0.8f),
                        onClick = {
                            //Obtiene indice actual de la carta seleccionada
                            indexCard2 = index2
                            //Deselecciona la carta anterior
                            if(previousIndex2 != -1)
                                selectedCardList2[previousIndex2] = false
                            //Selecciona la carta actual
                            selectedCardList2[index2] = true
                            //Obtiene indice anterior de la carta seleccionada
                            previousIndex2 = index2
                        }
                    )
                }
            }
        }
    }
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
            finalResult = 6
        )
    }
}
