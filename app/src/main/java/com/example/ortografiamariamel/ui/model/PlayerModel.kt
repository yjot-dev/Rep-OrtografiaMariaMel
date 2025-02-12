package com.example.ortografiamariamel.ui.model

import androidx.annotation.DrawableRes

data class PlayerModel(
    /* Variables de estado */
    //Nombre de jugador
    val name: String = "",
    //Edad del jugador
    val age: Int = 11,
    //Preguntas correctas por partida
    val success: Int = 0,
    //Preguntas incorrectas por partida
    val error: Int = 0,
    //Puntaje total de la partida
    val score: Int = 0,
    //Id de imagen del escenario
    @DrawableRes val idScenery: Int = 0,
    //Puntero de siguiente leccion
    val nextLesson: Int = 1,
    //Puntero de boton atras y seguir
    val nextUnit: Int = 1,
    //Mostrar y ocultar menu e items
    val visibleMenu: Boolean = false,
    val visibleItem1: Boolean = false,
    val visibleItem2: Boolean = false,
    val visibleItem3: Boolean = false,
    val visibleItem4: Boolean = false,
    //Comprobar si finalizo el juego
    val finishGame: Boolean = false
)