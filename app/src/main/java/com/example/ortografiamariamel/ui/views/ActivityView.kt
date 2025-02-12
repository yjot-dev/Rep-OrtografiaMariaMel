package com.example.ortografiamariamel.ui.views

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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.ortografiamariamel.R
import com.example.ortografiamariamel.ui.model.Game1Model
import com.example.ortografiamariamel.ui.model.Game2Model
import com.example.ortografiamariamel.ui.theme.OrtografiaMariaMelTheme
import com.example.ortografiamariamel.ui.theme.Typography
import com.example.ortografiamariamel.ui.viewModel.Game2ViewModel
import com.example.ortografiamariamel.ui.viewModel.Game1ViewModel
import com.example.ortografiamariamel.ui.viewModel.PlayerViewModel
import com.example.ortografiamariamel.ui.views.utils.BackGroundImage
import com.example.ortografiamariamel.ui.views.utils.CoverView
import com.example.ortografiamariamel.ui.views.utils.GifImage
import com.example.ortografiamariamel.ui.views.utils.RecyclerButton
import kotlinx.coroutines.delay

//Número de lecciones a revisar por unidad incluyendo la ventana puntaje
private const val NUM_LESSONS = 4
//Número de vidas del jugador
private const val NUM_LIFE = 3

@Composable
fun ActivityView(
    modifier: Modifier = Modifier,
    numberUnit: String,
    list1Game1: List<Game1Model>,
    list2Game1: List<Game1Model>,
    listGame2: List<Game2Model>,
    indexGame2: Int,
    vmPlayer: PlayerViewModel,
    onReturnClicked: () -> Unit
){
    //Instancias de los estados ViewModel
    val uiStatePlayer by vmPlayer.uiState.collectAsState()
    val vmGame1 = Game1ViewModel()
    val vmGame2 = Game2ViewModel()
    //Instancia de reproductor de sonido
    val context = LocalContext.current
    val sound1 = remember { MediaPlayer.create(context, R.raw.sound_one) }
    val sound2 = remember { MediaPlayer.create(context, R.raw.sound_two) }
    val sound3 = remember { MediaPlayer.create(context, R.raw.sound_three) }
    //Condicion para ir a siguiente o reiniciar
    val condition0 = uiStatePlayer.finishGame && uiStatePlayer.error == NUM_LIFE
    val condition1 = uiStatePlayer.nextLesson < NUM_LESSONS
    val condition2 = uiStatePlayer.nextLesson == NUM_LESSONS || condition0
    val condition3 = uiStatePlayer.finishGame && condition1
    LaunchedEffect(key1 = uiStatePlayer.error){
        if(!uiStatePlayer.finishGame && uiStatePlayer.error == NUM_LIFE){
            delay(800)
            vmPlayer.setFinishGame(true)
        }
    }
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
        if(uiStatePlayer.idScenery != 0){
            CoverView(
                idImage = uiStatePlayer.idScenery,
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(),
                alpha = 0.7f)
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = modifier
        ){
            AnimatedVisibility(
                visible = condition0,
                enter = scaleIn(),
                exit = scaleOut()){
                MessageAnimation(
                    idMessage = R.string.game_over,
                    modifier = Modifier
                        .fillMaxHeight(0.87f)
                        .fillMaxWidth(0.8f)
                )
            }
            if(!condition0){
                AnimatedVisibility(
                    visible = condition3,
                    enter = scaleIn(),
                    exit = scaleOut()){
                    MessageAnimation(
                        idMessage = R.string.game_finish_lesson,
                        modifier = Modifier
                            .fillMaxHeight(0.87f)
                            .fillMaxWidth(0.8f)
                    )
                }
                if(!condition3){
                    if(condition1){
                        ProgressBar(
                            numLesson = uiStatePlayer.nextLesson,
                            numError = uiStatePlayer.error,
                            modifier = Modifier.weight(1f)
                        )
                    }
                    //Muestra la leccion inicial y siguiente
                    when(uiStatePlayer.nextLesson){
                        1 -> {
                            vmPlayer.setIdScenery(R.drawable.scenery_one)
                            sound1.start()
                            GameOfCards(
                                vmPlayer = vmPlayer,
                                vmGame = vmGame1,
                                finalResult = 6,
                                listPairOfCard = list1Game1,
                                listRandom = list2Game1,
                                modifier = Modifier.fillMaxHeight(0.76f)
                            )
                        }
                        2 -> {
                            sound1.stop()
                            vmPlayer.setIdScenery(R.drawable.scenery_two)
                            sound2.start()
                            GameOfAnswerSelection(
                                vmPlayer = vmPlayer,
                                vmGame = vmGame2,
                                finalResult = 8,
                                questionIndex = indexGame2,
                                listQuestionsAnswers = listGame2,
                                modifier = Modifier.fillMaxHeight(0.76f)
                            )
                        }
                        3 -> {
                            sound2.stop()
                            vmPlayer.setIdScenery(R.drawable.scenery_three)
                            sound3.start()
                            GameOfAnswerSelection(
                                vmPlayer = vmPlayer,
                                vmGame = vmGame2,
                                finalResult = 10,
                                questionIndex = indexGame2 + 1,
                                listQuestionsAnswers = listGame2,
                                modifier = Modifier.fillMaxHeight(0.76f)
                            )
                        }
                        4 -> {
                            sound3.stop()
                            vmPlayer.setIdScenery(0)
                            ScorePlayer(
                                vmPlayer = vmPlayer,
                                numberUnit = numberUnit,
                                modifier = Modifier.fillMaxHeight(0.87f)
                            )
                        }
                    }
                }else vmPlayer.setIdScenery(0)
            }else vmPlayer.setIdScenery(0)
            RecyclerButton(
                textButton =
                    if(condition1) stringResource(R.string.button_next)
                    else stringResource(R.string.button_restart),
                isEnabled = uiStatePlayer.finishGame,
                modifier = Modifier
                    .weight(1f)
                    .padding(vertical = dimensionResource(id = R.dimen.short2_dp))
                    .fillMaxWidth(0.5f),
                onClick = {
                    //Pasa al siguiente juego y finaliza el actual
                    if(condition1){
                        vmPlayer.setNextLesson(uiStatePlayer.nextLesson + 1)
                        vmGame1.resetGame()
                        vmGame2.resetGame()
                        vmPlayer.setFinishGame(false)
                    }
                    //Regresa al inicio y finaliza partida
                    if(condition2){
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
    numError: Int){
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
                progress = numLesson.toFloat()/(NUM_LESSONS - 1),
                color = MaterialTheme.colorScheme.primaryContainer,
                modifier = Modifier
                    .weight(1f)
                    .sizeIn(
                        minHeight = dimensionResource(R.dimen.short2_dp),
                        maxHeight = dimensionResource(R.dimen.short3_dp)
                    )
            )
            Icon(
                imageVector = Icons.Default.Favorite,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.error
            )
            Text(
                text = (NUM_LIFE - numError).toString(),
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
    vmPlayer.setFinishGame(true)
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxWidth()
    ){
        BackGroundImage(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight())
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
            modifier = Modifier.fillMaxHeight(1f),
            numberUnit = stringResource(R.string.button_unit_1),
            vmPlayer = PlayerViewModel()
        )
    }
}