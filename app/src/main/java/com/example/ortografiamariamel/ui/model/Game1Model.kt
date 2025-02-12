package com.example.ortografiamariamel.ui.model

//Modelo del juego de pares de cartas
data class Game1Model(
    //Carta actual de la lista 1
    val idCard1: Int = 0,
    val card1: String = "",
    //Carta actual de la lista 2
    val idCard2: Int = 0,
    val card2: String = "",
    /* Variables de estado */
    //Indice de opcion selecionada para casillero 1
    val indexCard1: Int = -1,
    //Se deshabilita la carta 1 si es correcta
    val listCorrectCard1: MutableList<Boolean> =
        mutableListOf(true, true, true, true, true, true),
    //Se cambia color de borde en la carta 1 si es incorrecta
    val listIncorrectCard1: MutableList<Boolean> =
        mutableListOf(false, false, false, false, false, false),
    //Se cambia color de fondo si se selecciona la carta 1
    val listSelectedCard1: MutableList<Boolean> =
        mutableListOf(false, false, false, false, false, false),
    //Indice de opcion selecionada para casillero 2
    val indexCard2: Int = -1,
    //Se deshabilita la carta 2 si es correcta
    val listCorrectCard2: MutableList<Boolean> =
        mutableListOf(true, true, true, true, true, true),
    //Se cambia color de borde en la carta 2 si es incorrecta
    val listIncorrectCard2: MutableList<Boolean> =
        mutableListOf(false, false, false, false, false, false),
    //Se cambia color de fondo si se selecciona la carta 2
    val listSelectedCard2: MutableList<Boolean> =
        mutableListOf(false, false, false, false, false, false),
)