package com.example.ortografiamariamel.ui.viewModel

import androidx.lifecycle.ViewModel
import com.example.ortografiamariamel.ui.model.Game2Model
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

//Vista-Modelo del juego de seleccionar y ubicar respuesta correcta
class Game2ViewModel : ViewModel(){
    private val _uiState = MutableStateFlow(Game2Model())
    val uiState: StateFlow<Game2Model> = _uiState.asStateFlow()

    override fun onCleared() {
        resetGame()
    }
    /** Comprueba si se mueven los botones **/
    fun setListVisible(state: Boolean, position: Int){
        _uiState.value.listVisible[position] = state
        _uiState.update { currentState ->
            currentState.copy(listVisible = _uiState.value.listVisible)
        }
    }
    /** Cambia de visibilidad la respuesta 1 **/
    fun setVisibleAnswer1(state: Boolean){
        _uiState.update { currentState ->
            currentState.copy(visibleAnswer1 = state)
        }
    }
    /** Cambia de visibilidad la respuesta 2 **/
    fun setVisibleAnswer2(state: Boolean){
        _uiState.update { currentState ->
            currentState.copy(visibleAnswer2 = state)
        }
    }
    /** Cambio de indice de la respuesta 1 **/
    fun setIndexAnswer1(index: Int){
        _uiState.update { currentState ->
            currentState.copy(indexAnswer1 = index)
        }
    }
    /** Cambio de indice de la respuesta 2 **/
    fun setIndexAnswer2(index: Int){
        _uiState.update { currentState ->
            currentState.copy(indexAnswer2 = index)
        }
    }
    /** Bloquea respuesta 1 correcta **/
    fun setIsEnableAnswer1(state: Boolean){
        _uiState.update { currentState ->
            currentState.copy(enableAnswer1 = state)
        }
    }
    /** Bloquea respuesta 2 correcta **/
    fun setIsEnableAnswer2(state: Boolean){
        _uiState.update { currentState ->
            currentState.copy(enableAnswer2 = state)
        }
    }
    /** Resetea el juego **/
    fun resetGame(){
        _uiState.value = Game2Model()
    }
}