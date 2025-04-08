package com.example.ortografiamariamel.mvvm.viewmodel

import androidx.lifecycle.ViewModel
import com.example.ortografiamariamel.data.DataSourceGame1
import com.example.ortografiamariamel.data.DataSourceGame2
import com.example.ortografiamariamel.data.DataSourceGame3
import com.example.ortografiamariamel.mvvm.model.PlayerModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlin.math.abs

//Puntaje de cada pregunta
private const val SCORE_PER_QUESTION = 10

class PlayerViewModel : ViewModel() {

    //Estados de las variables del jugador
    private val _uiState = MutableStateFlow(PlayerModel())
    val uiState: StateFlow<PlayerModel> = _uiState.asStateFlow()
    //Listas del juego 1 (Una lista por unidad)
    val listUnit1Game1 = DataSourceGame1.listPairOfCardsUnit1
    //Listas del juego 2 (Una lista por unidad)
    val listUnit1Game2 = DataSourceGame2.listQuestionAndAnswersUnit1
    //Listas del juego 3 (Una lista por unidad)
    val listUnit1Game3 = DataSourceGame3.listQuestionAndAnswersUnit1
    //Número de lecciones a revisar por unidad incluyendo la ventana puntaje
    val numLessons = 4
    //Número de vidas del jugador
    val numLife = 3

    override fun onCleared() {
        resetPlayer()
    }
    /** Cambia nombre del jugador **/
    fun setName(name: String){
        _uiState.update { currentState ->
            currentState.copy(name = name)
        }
    }
    /** Cambia edad del jugador **/
    fun setAge(age: Int){
        _uiState.update { currentState ->
            currentState.copy(age = age)
        }
    }
    /** Cambio de preguntas correctas por partida **/
    fun setSuccess(currentSuccess: Int){
        _uiState.update { currentState ->
            currentState.copy(
                success = currentSuccess,
                score = calculateTotalScore(
                    currentSuccess = currentSuccess
                )
            )
        }
    }
    /** Cambio de preguntas incorrectas por partida **/
    fun setError(currentError: Int){
        _uiState.update { currentState ->
            currentState.copy(
                error = currentError,
                score = calculateTotalScore(
                    currentError = currentError
                )
            )
        }
    }
    /** Cambio de id de imagen del escenario **/
    fun setIdScenery(idImage: Int){
        _uiState.update { currentState ->
            currentState.copy(idScenery = idImage)
        }
    }
    /** Finaliza el juego **/
    fun setFinishGame(state: Boolean){
        _uiState.update { currentState ->
            currentState.copy(finishGame = state)
        }
    }
    /** Resetea la partida para jugar de nuevo con otro jugador **/
    fun resetPlayer() {
        _uiState.value = PlayerModel()
    }
    /** Retorna el puntaje total de la partida del jugador **/
    private fun calculateTotalScore(
        currentSuccess: Int = uiState.value.success,
        currentError: Int = uiState.value.error
    ): Int{
        return abs(currentSuccess - currentError) * SCORE_PER_QUESTION
    }
}