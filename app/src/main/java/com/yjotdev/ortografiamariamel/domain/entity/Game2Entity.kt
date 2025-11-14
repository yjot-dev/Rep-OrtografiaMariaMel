package com.yjotdev.ortografiamariamel.domain.entity

//Modelo de juego de seleccionar y ubicar respuesta correcta
data class Game2Entity(
    //Pregunta
    val question: List<String> = listOf(),
    //Respuestas correctas
    val listCorrectAnswers: List<String> = listOf(),
    //Opciones de respuestas a elegir
    val listOptionsAnswers: List<String> = listOf(),
)