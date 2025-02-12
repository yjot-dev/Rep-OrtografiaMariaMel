package com.example.ortografiamariamel.ui.viewModel

import androidx.lifecycle.ViewModel
import com.example.ortografiamariamel.ui.model.Game1Model
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

//Vista-Modelo del juego de pares de cartas
class Game1ViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(Game1Model())
    val uiState: StateFlow<Game1Model> = _uiState.asStateFlow()

    override fun onCleared() {
        resetGame()
    }
    /** Cambio de indice de tarjeta 1 **/
    fun setIndexCard1(index: Int){
        _uiState.update { currentState ->
            currentState.copy(indexCard1 = index)
        }
    }
    /** Cambio segun posicion de tarjeta 1 correcta **/
    fun setCorrectCard1(item: Boolean, position: Int){
        _uiState.value.listCorrectCard1[position] = item
        _uiState.update { currentState ->
            currentState.copy(listCorrectCard1 = _uiState.value.listCorrectCard1)
        }
    }
    /** Cambio segun posicion de tarjeta 1 incorrecta **/
    fun setIncorrectCard1(item: Boolean, position: Int){
        _uiState.value.listIncorrectCard1[position] = item
        _uiState.update { currentState ->
            currentState.copy(listIncorrectCard1 = _uiState.value.listIncorrectCard1)
        }
    }
    /** Cambio segun posicion de tarjeta 1 seleccionada **/
    fun setSelectedCard1(item: Boolean, position: Int){
        _uiState.value.listSelectedCard1[position] = item
        _uiState.update { currentState ->
            currentState.copy(listSelectedCard1 = _uiState.value.listSelectedCard1)
        }
    }
    /** Cambio de indice de tarjeta 2 **/
    fun setIndexCard2(index: Int){
        _uiState.update { currentState ->
            currentState.copy(indexCard2 = index)
        }
    }
    /** Cambio segun posicion de tarjeta 2 correcta **/
    fun setCorrectCard2(item: Boolean, position: Int){
        _uiState.value.listCorrectCard2[position] = item
        _uiState.update { currentState ->
            currentState.copy(listCorrectCard2 = _uiState.value.listCorrectCard2)
        }
    }
    /** Cambio segun posicion de tarjeta 2 incorrecta **/
    fun setIncorrectCard2(item: Boolean, position: Int){
        _uiState.value.listIncorrectCard2[position] = item
        _uiState.update { currentState ->
            currentState.copy(listIncorrectCard2 = _uiState.value.listIncorrectCard2)
        }
    }
    /** Cambio segun posicion de tarjeta 2 seleccionada **/
    fun setSelectedCard2(item: Boolean, position: Int){
        _uiState.value.listSelectedCard2[position] = item
        _uiState.update { currentState ->
            currentState.copy(listSelectedCard2 = _uiState.value.listSelectedCard2)
        }
    }
    /** Resetea el juego **/
    fun resetGame(){
        _uiState.value = Game1Model()
    }
}