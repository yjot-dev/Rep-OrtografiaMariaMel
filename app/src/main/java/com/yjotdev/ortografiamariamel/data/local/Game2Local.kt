package com.yjotdev.ortografiamariamel.data.local

import com.yjotdev.ortografiamariamel.domain.model.Game2Model
/*
Ejemplo de palabras monosílabas con y sin acentuación
https://ejerciciode.com/ejercicios-de-palabras-monosilabas/?=
*/
object Game2Local {
    val listQuestionAndAnswersUnit1 = listOf(
        Game2Model(
            question = listOf("¿ "," (Te/Té) vienes a tomar un "," (te/té) mañana?"),
            listCorrectAnswers = listOf("Te","té"),
            listOptionsAnswers = listOf("Te","Té","te","té")
        ),
        Game2Model(
            question = listOf("Le he dicho que "," (te/té) lo "," (de/dé) al salir de clases."),
            listCorrectAnswers = listOf("te","dé"),
            listOptionsAnswers = listOf("te","té","de","dé")
        )
    )
}