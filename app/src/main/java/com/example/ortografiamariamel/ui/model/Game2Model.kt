package com.example.ortografiamariamel.ui.model

//Modelo de juego de seleccionar y ubicar respuesta correcta
data class Game2Model(
    //Pregunta
    val question: List<String> = listOf(),
    //Respuestas correctas
    val listCorrectAnswers: List<String> = listOf(),
    //Opciones de respuestas a elegir
    val listOptionsAnswers: List<String> = listOf(),
    /* Variables de estado */
    //Comprueba si se mueven los botones
    val listVisible: MutableList<Boolean> =
        mutableListOf(true, true, true, true),
    val visibleAnswer1: Boolean = false,
    val visibleAnswer2: Boolean = false,
    //Valor actual de respuesta
    val indexAnswer1: Int = -1,
    val indexAnswer2: Int = -1,
    //Bloquear respuestas correctas
    val enableAnswer1: Boolean = true,
    val enableAnswer2: Boolean = true,
)