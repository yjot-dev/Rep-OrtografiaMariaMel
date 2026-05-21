package com.yjotdev.ortografiamariamel.data.local

import com.yjotdev.ortografiamariamel.domain.model.Game3Model
/*
¿Cuáles son los tipos de tilde diacrítica?
https://www.unprofesor.com/lengua-espanola/la-tilde-diacritica-73.html
*/
object Game3Local {
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