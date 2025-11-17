package com.yjotdev.ortografiamariamel.application.mvvm.model

import androidx.annotation.DrawableRes
import com.yjotdev.ortografiamariamel.domain.entity.Game1Entity
import com.yjotdev.ortografiamariamel.domain.entity.Game2Entity
import com.yjotdev.ortografiamariamel.domain.entity.Game3Entity

data class PlayerModel(
    //Datos del jugador
    val name: String = "",
    val age: Int = 11,
    /* Variables de estado */
    //Datos de los juegos
    val game1data: List<Game1Entity> = emptyList(),
    val game2data: List<Game2Entity> = emptyList(),
    val game3data: List<Game3Entity> = emptyList(),
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