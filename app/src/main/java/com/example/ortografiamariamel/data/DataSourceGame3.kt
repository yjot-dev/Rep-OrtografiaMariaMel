package com.example.ortografiamariamel.data

import com.example.ortografiamariamel.ui.model.Game3Model

/*
¿Cuáles son los tipos de tilde diacrítica?
https://www.unprofesor.com/lengua-espanola/la-tilde-diacritica-73.html
*/
object DataSourceGame3 {
    val listQuestionAndAnswersUnit1 = listOf(
        Game3Model(
            question = "Escriba cual de las siguientes opciones es un pronombre personal:" +
                    "\nTu, El, Tú, Mi",
            correctAnswer = "Tú"
        ),
        Game3Model(
            question = "Escriba cual de las siguientes opciones es un adjetivo posesivo:" +
                    "\nMi, Él, El, Tú",
            correctAnswer = "Mi"
        )
    )
}