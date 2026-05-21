package com.yjotdev.ortografiamariamel.presentation.mvvm.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlin.math.abs
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.yjotdev.ortografiamariamel.presentation.mvvm.state.UiState
import com.yjotdev.ortografiamariamel.domain.usecase.GetGame1DataUseCase
import com.yjotdev.ortografiamariamel.domain.usecase.GetGame2DataUseCase
import com.yjotdev.ortografiamariamel.domain.usecase.GetGame3DataUseCase
import kotlinx.coroutines.launch

@HiltViewModel
class UiViewModel @Inject constructor(
    private val getGame1DataUseCase: GetGame1DataUseCase,
    private val getGame2DataUseCase: GetGame2DataUseCase,
    private val getGame3DataUseCase: GetGame3DataUseCase
): ViewModel() {
    //Estados de las variables del jugador
    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()
    //Puntaje de cada pregunta
    private val scorePerQuestion = 10

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
    fun setSuccess(success: Int){
        _uiState.update { currentState ->
            currentState.copy(
                success = success,
                score = calculateTotalScore(
                    currentSuccess = success
                )
            )
        }
    }
    /** Cambio de preguntas incorrectas por partida **/
    fun setError(error: Int){
        _uiState.update { currentState ->
            currentState.copy(
                error = error,
                score = calculateTotalScore(
                    currentError = error
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
    fun setFinishGame(finishGame: Boolean){
        _uiState.update { currentState ->
            currentState.copy(finishGame = finishGame)
        }
    }
    /** Leccion actual de la actividad **/
    fun setCurrentLesson(currentLesson: Int){
        _uiState.update { currentState ->
            currentState.copy(currentLesson = currentLesson)
        }
    }
    /** Resetea la partida para jugar de nuevo con otro jugador **/
    fun resetPlayer() {
        _uiState.value = UiState()
    }
    /** Carga los datos de los juegos **/
    fun loadGames(unit: Int){
        viewModelScope.launch {
            _uiState.update { currentState ->
                currentState.copy(
                    game1data = getGame1DataUseCase(unit),
                    game2data = getGame2DataUseCase(unit),
                    game3data = getGame3DataUseCase(unit)
                )
            }
        }
    }
    /** Retorna el puntaje total de la partida del jugador **/
    private fun calculateTotalScore(
        currentSuccess: Int = uiState.value.success,
        currentError: Int = uiState.value.error
    ): Int{
        return abs(currentSuccess - currentError) * scorePerQuestion
    }
}