package com.yjotdev.ortografiamariamel.presentation.mvvm.state

import androidx.annotation.DrawableRes
import com.yjotdev.ortografiamariamel.domain.model.Game1Model
import com.yjotdev.ortografiamariamel.domain.model.Game2Model
import com.yjotdev.ortografiamariamel.domain.model.Game3Model

data class UiState(
    //Datos del jugador
    val name: String = "",
    val age: Int = 11,
    /* Variables de estado */
    //Datos de los juegos
    val game1data: List<Game1Model> = emptyList(),
    val game2data: List<Game2Model> = emptyList(),
    val game3data: List<Game3Model> = emptyList(),
    //Preguntas correctas por partida
    val success: Int = 0,
    //Preguntas incorrectas por partida
    val error: Int = 0,
    //Puntaje total de la partida
    val score: Int = 0,
    //Leccion actual de la actividad
    val currentLesson: Int = 1,
    //Id de imagen del escenario
    @DrawableRes val idScenery: Int = 0,
    //Comprobar si finalizo el juego
    val finishGame: Boolean = false
)