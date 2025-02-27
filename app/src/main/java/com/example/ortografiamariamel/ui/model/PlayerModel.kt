package com.example.ortografiamariamel.ui.model

import androidx.annotation.DrawableRes

data class PlayerModel(
    //Datos del jugador
    val name: String = "",
    val age: Int = 11,
    /* Variables de estado */
    //Preguntas correctas por partida
    val success: Int = 0,
    //Preguntas incorrectas por partida
    val error: Int = 0,
    //Puntaje total de la partida
    val score: Int = 0,
    //Id de imagen del escenario
    @DrawableRes val idScenery: Int = 0,
    //Comprobar si finalizo el juego
    val finishGame: Boolean = false
)