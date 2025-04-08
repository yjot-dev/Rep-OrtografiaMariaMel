package com.example.ortografiamariamel.mvvm.view

import android.content.res.Configuration
import android.media.MediaPlayer
import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.ortografiamariamel.R
import com.example.ortografiamariamel.mvvm.model.Game1Model
import com.example.ortografiamariamel.mvvm.model.Game2Model
import com.example.ortografiamariamel.mvvm.model.Game3Model
import com.example.ortografiamariamel.theme.OrtografiaMariaMelTheme
import com.example.ortografiamariamel.theme.Typography
import com.example.ortografiamariamel.mvvm.viewmodel.PlayerViewModel
import com.example.ortografiamariamel.composable.BackGroundImage
import com.example.ortografiamariamel.composable.CoverView
import com.example.ortografiamariamel.composable.GifImage
import com.example.ortografiamariamel.composable.RecyclerButton
import com.example.ortografiamariamel.mvvm.view.game1view.GameOfCards
import com.example.ortografiamariamel.mvvm.view.game2view.GameOfAnswerSelection
import com.example.ortografiamariamel.mvvm.view.game3view.GameOfAnswerWriting

@Composable
fun ActivityView(
    modifier: Modifier = Modifier,
    numberUnit: String,
    listGame1: List<Game1Model>,
    listGame2: List<Game2Model>,
    listGame3: List<Game3Model>,
    vmPlayer: PlayerViewModel,
    onReturnClicked: () -> Unit
){
    //Instancias de los estados ViewModel
    val uiStatePlayer by vmPlayer.uiState.collectAsState()
    var nextLesson by remember { mutableIntStateOf(1) }
    //Instancia de reproductor de sonido
    val context = LocalContext.current
    val sound1 = remember { MediaPlayer.create(context, R.raw.sound_one) }
    val sound2 = remember { MediaPlayer.create(context, R.raw.sound_two) }
    val sound3 = remember { MediaPlayer.create(context, R.raw.sound_three) }
    //Condicion para ir a la siguiente leccion y mostrar animacion 1
    val condition1 = uiStatePlayer.finishGame && nextLesson < vmPlayer.numLessons
    //Condicion para finalizar juego si gana el jugador
    val condition2 = nextLesson == vmPlayer.numLessons
    //Condicion para finalizar juego si pierde el jugador y mostrar animacion 2
    val condition3 = uiStatePlayer.error == vmPlayer.numLife
    //Condicion para mostrar la leccion actual
    val condition4 = !uiStatePlayer.finishGame && nextLesson < vmPlayer.numLessons
    DisposableEffect(key1 = Unit){
        onDispose {
            sound1.stop()
            sound2.stop()
            sound3.stop()
            vmPlayer.setSuccess(0)
            vmPlayer.setError(0)
        }
    }
    Box(
        modifier = modifier.background(MaterialTheme.colorScheme.onPrimary),
        contentAlignment = Alignment.Center
    ){
        //Escenario de fondo del juego
        if(uiStatePlayer.idScenery != 0){
            CoverView(
                idImage = uiStatePlayer.idScenery,
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(),
                alpha = 0.7f)
        }
        //Animaciones y vista del juego
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ){
            when{
                condition1 -> {
                    AnimatedVisibility(
                        visible = true,
                        enter = scaleIn(),
                        exit = scaleOut()){
                        MessageAnimation(
                            idMessage = R.string.game_finish_lesson,
                            modifier = Modifier
                                .fillMaxHeight(0.87f)
                                .fillMaxWidth(0.94f)
                        )
                    }
                }
                condition2 -> {
                    sound3.stop()
                    vmPlayer.setIdScenery(0)
                    ScorePlayer(
                        vmPlayer = vmPlayer,
                        numberUnit = numberUnit,
                        modifier = Modifier
                            .fillMaxHeight(0.87f)
                            .fillMaxWidth()
                    )
                }
                condition3 -> {
                    AnimatedVisibility(
                        visible = true,
                        enter = scaleIn(),
                        exit = scaleOut()){
                        MessageAnimation(
                            idMessage = R.string.game_over,
                            modifier = Modifier
                                .fillMaxHeight(0.87f)
                                .fillMaxWidth(0.94f)
                        )
                    }
                }
                condition4 -> {
                    ProgressBar(
                        numLesson = nextLesson,
                        numError = uiStatePlayer.error,
                        vmPlayer = vmPlayer
                    )
                    //Muestra la leccion inicial y siguiente
                    when(nextLesson){
                        1 -> {
                            vmPlayer.setIdScenery(R.drawable.scenery_one)
                            sound1.start()
                            GameOfCards(
                                vmPlayer = vmPlayer,
                                finalResult = 6,
                                listPairOfCard = listGame1,
                                modifier = Modifier
                                    .fillMaxHeight(0.87f)
                                    .fillMaxWidth()
                            )
                        }
                        2 -> {
                            sound1.stop()
                            vmPlayer.setIdScenery(R.drawable.scenery_two)
                            sound2.start()
                            GameOfAnswerSelection(
                                vmPlayer = vmPlayer,
                                finalResult = 10,
                                listQuestionsAnswers = listGame2,
                                modifier = Modifier
                                    .fillMaxHeight(0.87f)
                                    .fillMaxWidth()
                            )
                        }
                        3 -> {
                            sound2.stop()
                            vmPlayer.setIdScenery(R.drawable.scenery_three)
                            sound3.start()
                            GameOfAnswerWriting(
                                vmPlayer = vmPlayer,
                                finalResult = 12,
                                listQuestionsAnswers = listGame3,
                                modifier = Modifier
                                    .fillMaxHeight(0.87f)
                                    .fillMaxWidth()
                            )
                        }
                    }
                }
            }
            RecyclerButton(
                textButton =
                    if(!condition2) stringResource(R.string.button_next)
                    else stringResource(R.string.button_restart),
                isEnabled = condition1 || condition2 || condition3,
                modifier = Modifier
                    .padding(vertical = dimensionResource(id = R.dimen.short2_dp))
                    .fillMaxWidth(0.7f),
                onClick = {
                    //Pasa al siguiente juego y resetea el actual
                    if(condition1){
                        nextLesson += 1
                        vmPlayer.setFinishGame(false)
                    }
                    //Regresa al inicio y finaliza partida
                    if(condition2 || condition3){
                        vmPlayer.resetPlayer()
                        onReturnClicked()
                    }
                }
            )
        }
    }
}

@Composable
private fun MessageAnimation(
    modifier: Modifier = Modifier,
    @StringRes idMessage: Int)
{
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
    ){
        Text(
            text = stringResource(id = idMessage),
            style = Typography.titleLarge,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = dimensionResource(R.dimen.short3_dp))
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ){
            GifImage(idImage = R.drawable.student_one_animated,
                modifier = Modifier.weight(1f)
            )
            GifImage(idImage = R.drawable.student_two_animated,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
private fun ProgressBar(
    modifier: Modifier = Modifier,
    numLesson: Int,
    numError: Int,
    vmPlayer: PlayerViewModel
){
    Column(
        modifier = modifier
            .padding(vertical = dimensionResource(R.dimen.short2_dp))
            .background(MaterialTheme.colorScheme.onPrimary.copy(0.7f))
    ) {
        Text(
            text = stringResource(id = R.string.activity_unit),
            style = Typography.titleLarge,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            modifier = Modifier.fillMaxWidth()
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(
                space = dimensionResource(id = R.dimen.short1_dp)),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(horizontal = dimensionResource(R.dimen.short4_dp))
        ){
            LinearProgressIndicator(
                progress = { numLesson.toFloat() / (vmPlayer.numLessons - 1) },
                modifier = Modifier
                    .weight(1f)
                    .sizeIn(
                        minHeight = dimensionResource(R.dimen.short2_dp),
                        maxHeight = dimensionResource(R.dimen.short3_dp)
                    ),
                color = MaterialTheme.colorScheme.primaryContainer,
            )
            Icon(
                imageVector = Icons.Default.Favorite,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.error
            )
            Text(
                text = (vmPlayer.numLife - numError).toString(),
                style = Typography.bodyLarge,
                color = MaterialTheme.colorScheme.error
            )
        }
    }
}

@Composable
private fun ScorePlayer(
    modifier: Modifier = Modifier,
    numberUnit: String,
    vmPlayer: PlayerViewModel
){
    val uiStatePlayer by vmPlayer.uiState.collectAsState()
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ){
        BackGroundImage(modifier = modifier)
        Card(
            border = BorderStroke(
                width = dimensionResource(id = R.dimen.short2_dp),
                color = MaterialTheme.colorScheme.tertiary
            ),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primary
                    .copy(alpha = 0.9f)
            )
        ){
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start,
                modifier = Modifier.padding(dimensionResource(id = R.dimen.short4_dp))
            ){
                Text(
                    text = stringResource(id = R.string.game_finish_unit, numberUnit),
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    style = Typography.titleLarge
                )
                Spacer(modifier = Modifier.sizeIn(
                    minHeight = dimensionResource(R.dimen.short3_dp),
                    maxHeight = dimensionResource(R.dimen.short4_dp)
                ))
                ScoreItems(
                    textItem = stringResource(id = R.string.game_player, uiStatePlayer.name.uppercase()),
                    iconItem = Icons.Default.AccountBox
                )
                Spacer(modifier = Modifier.sizeIn(
                    minHeight = dimensionResource(R.dimen.short1_dp),
                    maxHeight = dimensionResource(R.dimen.short2_dp)
                ))
                ScoreItems(
                    textItem = stringResource(id = R.string.game_age, uiStatePlayer.age),
                    iconItem = Icons.Default.Face
                )
                Spacer(modifier = Modifier.sizeIn(
                    minHeight = dimensionResource(R.dimen.short1_dp),
                    maxHeight = dimensionResource(R.dimen.short2_dp)
                ))
                ScoreItems(
                    textItem = stringResource(id = R.string.game_score, uiStatePlayer.score),
                    iconItem = Icons.Default.Star
                )
                Spacer(modifier = Modifier.sizeIn(
                    minHeight = dimensionResource(R.dimen.short1_dp),
                    maxHeight = dimensionResource(R.dimen.short2_dp)
                ))
                ScoreItems(
                    textItem = stringResource(id = R.string.game_success, uiStatePlayer.success),
                    iconItem = Icons.Default.Check
                )
                Spacer(modifier = Modifier.sizeIn(
                    minHeight = dimensionResource(R.dimen.short1_dp),
                    maxHeight = dimensionResource(R.dimen.short2_dp)
                ))
                ScoreItems(
                    textItem = stringResource(id = R.string.game_error, uiStatePlayer.error),
                    iconItem = Icons.Default.Clear
                )
            }
        }
    }
}

@Composable
private fun ScoreItems(
    modifier: Modifier = Modifier,
    iconItem: ImageVector,
    textItem: String
){
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ){
        Icon(
            imageVector = iconItem,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onPrimaryContainer
        )
        Spacer(modifier = modifier.sizeIn(
            minWidth = dimensionResource(R.dimen.short1_dp),
            maxWidth = dimensionResource(R.dimen.short2_dp)
        ))
        Text(
            text = textItem,
            style = Typography.bodyLarge,
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )
    }
}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun PreviewScorePlayer() {
    OrtografiaMariaMelTheme {
        ScorePlayer(
            modifier = Modifier.fillMaxSize(),
            numberUnit = "1",
            vmPlayer = PlayerViewModel()
        )
    }
}