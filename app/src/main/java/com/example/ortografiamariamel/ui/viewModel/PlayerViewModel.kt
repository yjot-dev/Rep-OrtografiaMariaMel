package com.example.ortografiamariamel.ui.viewModel

import androidx.lifecycle.ViewModel
import com.example.ortografiamariamel.ui.model.PlayerModel
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

    override fun onCleared() {
        resetPlayer()
    }
    /** Cambio de nombre del jugador por partida **/
    fun setName(currentName: String){
        _uiState.update { currentState ->
            currentState.copy(name = currentName)
        }
    }
    /** Cambio de la edad del jugador por partida **/
    fun setAge(currentAge: Int){
        _uiState.update { currentState ->
            currentState.copy(age = currentAge)
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
    /** Cambio de puntero de siguiente leccion **/
    fun setNextLesson(currentLesson: Int){
        _uiState.update { currentState ->
            currentState.copy(nextLesson = currentLesson)
        }
    }
    /** Cambio de puntero del boton atras y seguir **/
    fun setNextUnit(currentUnit: Int){
        _uiState.update { currentState ->
            currentState.copy(nextUnit = currentUnit)
        }
    }
    /** Mostrar y ocultar menu e items **/
    fun setVisibleMenu(state: Boolean){
        _uiState.update { currentState ->
            currentState.copy(visibleMenu = state)
        }
    }
    /** Cambio de estado item1 **/
    fun setVisibleItem1(state: Boolean){
        _uiState.update { currentState ->
            currentState.copy(visibleItem1 = state)
        }
    }
    /** Cambio de estado item2 **/
    fun setVisibleItem2(state: Boolean){
        _uiState.update { currentState ->
            currentState.copy(visibleItem2 = state)
        }
    }
    /** Cambio de estado item3 **/
    fun setVisibleItem3(state: Boolean){
        _uiState.update { currentState ->
            currentState.copy(visibleItem3 = state)
        }
    }
    /** Cambio de estado item4 **/
    fun setVisibleItem4(state: Boolean){
        _uiState.update { currentState ->
            currentState.copy(visibleItem4 = state)
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