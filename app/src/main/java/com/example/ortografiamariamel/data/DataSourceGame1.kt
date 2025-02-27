package com.example.ortografiamariamel.data

import com.example.ortografiamariamel.ui.model.Game1Model

/*
¿Cuáles son los tipos de tilde diacrítica?
https://www.unprofesor.com/lengua-espanola/la-tilde-diacritica-73.html
*/
object DataSourceGame1 {
    val listPairOfCardsUnit1 = listOf(
        //Columna 1
        Game1Model(1,"Tú"),
        Game1Model(2,"Tu"),
        Game1Model(1,"Él"),
        Game1Model(3,"El"),
        Game1Model(1,"Mí"),
        Game1Model(2,"Mi"),
        //Columna 2
        Game1Model(1,"Pronombre personal"),
        Game1Model(2,"Adjetivo posesivo"),
        Game1Model(1,"Pronombre personal"),
        Game1Model(3,"Artículo"),
        Game1Model(1,"Pronombre personal"),
        Game1Model(2,"Adjetivo posesivo")
    )
}