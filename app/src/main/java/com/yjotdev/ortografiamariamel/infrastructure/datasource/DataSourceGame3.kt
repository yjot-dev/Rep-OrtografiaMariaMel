package com.yjotdev.ortografiamariamel.infrastructure.datasource

import com.yjotdev.ortografiamariamel.domain.entity.Game3Entity
/*
¿Cuáles son los tipos de tilde diacrítica?
https://www.unprofesor.com/lengua-espanola/la-tilde-diacritica-73.html
*/
object DataSourceGame3 {
    val listQuestionAndAnswersUnit1 = listOf(
        Game3Entity(
            question = "Escriba cual de las siguientes opciones es un pronombre personal:" +
                    "\nTu, El, Tú, Mi",
            correctAnswer = "Tú"
        ),
        Game3Entity(
            question = "Escriba cual de las siguientes opciones es un adjetivo posesivo:" +
                    "\nMi, Él, El, Tú",
            correctAnswer = "Mi"
        )
    )
}