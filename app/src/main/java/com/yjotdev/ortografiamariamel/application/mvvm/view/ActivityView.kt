package com.yjotdev.ortografiamariamel.application.mvvm.view

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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
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
import androidx.compose.ui.res.vectorResource
import com.yjotdev.ortografiamariamel.R
import com.yjotdev.ortografiamariamel.application.theme.OrtografiaMariaMelTheme
import com.yjotdev.ortografiamariamel.application.components.BackGroundImage
import com.yjotdev.ortografiamariamel.application.components.CoverView
import com.yjotdev.ortografiamariamel.application.components.GifImage
import com.yjotdev.ortografiamariamel.application.components.RecyclerButton
import com.yjotdev.ortografiamariamel.application.components.game1view.GameOfCards
import com.yjotdev.ortografiamariamel.application.components.game2view.GameOfAnswerSelection
import com.yjotdev.ortografiamariamel.application.components.game3view.GameOfAnswerWriting
import com.yjotdev.ortografiamariamel.application.utils.ComponentPreview
import com.yjotdev.ortografiamariamel.domain.entity.Game1Entity
import com.yjotdev.ortografiamariamel.domain.entity.Game2Entity
import com.yjotdev.ortografiamariamel.domain.entity.Game3Entity

@Composable
fun ActivityView(
    modifier: Modifier = Modifier,
    numberUnit: String,
    listGame1: List<Game1Entity>,
    listGame2: List<Game2Entity>,
    listGame3: List<Game3Entity>,
    numLessons: Int,
    numLife: Int,
    name: String,
    age: Int,
    finishGame: Boolean,
    success: Int,
    error: Int,
    score: Int,
    idScenery: Int,
    onSuccess: (Int) -> Unit,
    onError: (Int) -> Unit,
    onIdScenery: (Int) -> Unit,
    onFinishGame: (Boolean) -> Unit,
    onReturnClicked: () -> Unit
){
    //Instancias de los estados ViewModel
    var nextLesson by remember { mutableIntStateOf(1) }
    //Instancia de reproductor de sonido
    val context = LocalContext.current
    val sound1 = remember { MediaPlayer.create(context, R.raw.sound_one) }
    val sound2 = remember { MediaPlayer.create(context, R.raw.sound_two) }
    val sound3 = remember { MediaPlayer.create(context, R.raw.sound_three) }
    //Condicion para ir a la siguiente leccion y mostrar animacion 1
    val condition1 = finishGame && nextLesson < numLessons
    //Condicion para finalizar juego si gana el jugador
    val condition2 = nextLesson == numLessons
    //Condicion para finalizar juego si pierde el jugador y mostrar animacion 2
    val condition3 = error == numLife
    //Condicion para mostrar la leccion actual
    val condition4 = !finishGame && nextLesson < numLessons
    DisposableEffect(key1 = Unit){
        onDispose {
            sound1.stop()
            sound2.stop()
            sound3.stop()
            onSuccess(0)
            onError(0)
        }
    }
    Box(
        modifier = modifier.background(MaterialTheme.colorScheme.onPrimary),
        contentAlignment = Alignment.Center
    ){
        //Escenario de fondo del juego
        if(idScenery != 0){
            CoverView(
                idImage = idScenery,
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
                    onIdScenery(0)
                    ScorePlayer(
                        numberUnit = numberUnit,
                        name = name,
                        age = age,
                        score = score,
                        success = success,
                        error = error,
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
                        currentLesson = nextLesson,
                        totalLesson = numLessons,
                        numLife = numLife,
                        numError = error
                    )
                    //Muestra la leccion inicial y siguiente
                    when(nextLesson){
                        1 -> {
                            onIdScenery(R.drawable.scenery_one)
                            sound1.start()
                            GameOfCards(
                                success = success,
                                error = error,
                                finalResult = 6,
                                listPairOfCard = listGame1,
                                onFinishGame = onFinishGame,
                                onSuccess = onSuccess,
                                onError = onError,
                                modifier = Modifier
                                    .fillMaxHeight(0.87f)
                                    .fillMaxWidth()
                            )
                        }
                        2 -> {
                            sound1.stop()
                            onIdScenery(R.drawable.scenery_two)
                            sound2.start()
                            GameOfAnswerSelection(
                                success = success,
                                error = error,
                                finalResult = 10,
                                listQuestionsAnswers = listGame2,
                                onFinishGame = onFinishGame,
                                onSuccess = onSuccess,
                                onError = onError,
                                modifier = Modifier
                                    .fillMaxHeight(0.87f)
                                    .fillMaxWidth()
                            )
                        }
                        3 -> {
                            sound2.stop()
                            onIdScenery(R.drawable.scenery_three)
                            sound3.start()
                            GameOfAnswerWriting(
                                success = success,
                                error = error,
                                finalResult = 12,
                                listQuestionsAnswers = listGame3,
                                onFinishGame = onFinishGame,
                                onSuccess = onSuccess,
                                onError = onError,
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
                        onFinishGame(false)
                    }
                    //Regresa al inicio y finaliza partida
                    if(condition2 || condition3){
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
            style = MaterialTheme.typography.titleLarge,
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
    currentLesson: Int,
    totalLesson: Int,
    numLife: Int,
    numError: Int
){
    Column(
        modifier = modifier
            .padding(vertical = dimensionResource(R.dimen.short2_dp))
            .background(MaterialTheme.colorScheme.onPrimary.copy(0.7f))
    ) {
        Text(
            text = stringResource(id = R.string.activity_unit),
            style = MaterialTheme.typography.titleLarge,
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
                progress = { currentLesson.toFloat() / (totalLesson - 1) },
                modifier = Modifier
                    .weight(1f)
                    .sizeIn(
                        minHeight = dimensionResource(R.dimen.short2_dp),
                        maxHeight = dimensionResource(R.dimen.short3_dp)
                    ),
                color = MaterialTheme.colorScheme.primaryContainer,
            )
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.favorite_48),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.error
            )
            Text(
                text = (numLife - numError).toString(),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.error
            )
        }
    }
}

@Composable
private fun ScorePlayer(
    modifier: Modifier = Modifier,
    numberUnit: String,
    name: String,
    age: Int,
    score: Int,
    success: Int,
    error: Int
){
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
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(modifier = Modifier.sizeIn(
                    minHeight = dimensionResource(R.dimen.short3_dp),
                    maxHeight = dimensionResource(R.dimen.short4_dp)
                ))
                ScoreItems(
                    textItem = stringResource(id = R.string.game_player, name.uppercase()),
                    iconItem = ImageVector.vectorResource(id = R.drawable.player_48)
                )
                Spacer(modifier = Modifier.sizeIn(
                    minHeight = dimensionResource(R.dimen.short1_dp),
                    maxHeight = dimensionResource(R.dimen.short2_dp)
                ))
                ScoreItems(
                    textItem = stringResource(id = R.string.game_age, age),
                    iconItem = ImageVector.vectorResource(id = R.drawable.age_48)
                )
                Spacer(modifier = Modifier.sizeIn(
                    minHeight = dimensionResource(R.dimen.short1_dp),
                    maxHeight = dimensionResource(R.dimen.short2_dp)
                ))
                ScoreItems(
                    textItem = stringResource(id = R.string.game_score, score),
                    iconItem = ImageVector.vectorResource(id = R.drawable.score_48)
                )
                Spacer(modifier = Modifier.sizeIn(
                    minHeight = dimensionResource(R.dimen.short1_dp),
                    maxHeight = dimensionResource(R.dimen.short2_dp)
                ))
                ScoreItems(
                    textItem = stringResource(id = R.string.game_success, success),
                    iconItem = ImageVector.vectorResource(id = R.drawable.correct_48)
                )
                Spacer(modifier = Modifier.sizeIn(
                    minHeight = dimensionResource(R.dimen.short1_dp),
                    maxHeight = dimensionResource(R.dimen.short2_dp)
                ))
                ScoreItems(
                    textItem = stringResource(id = R.string.game_error, error),
                    iconItem = ImageVector.vectorResource(id = R.drawable.incorrect_48)
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
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )
    }
}

@ComponentPreview
@Composable
private fun PreviewScorePlayer() {
    OrtografiaMariaMelTheme {
        ScorePlayer(
            modifier = Modifier.fillMaxSize(),
            numberUnit = "1",
            name = "Carlos",
            age = 11,
            score = 100,
            success = 5,
            error = 2
        )
    }
}