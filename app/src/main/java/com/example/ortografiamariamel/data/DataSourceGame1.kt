package com.example.ortografiamariamel.data

import com.example.ortografiamariamel.ui.model.Game1Model

/*
¿Cuáles son los tipos de tilde diacrítica?
https://www.unprofesor.com/lengua-espanola/la-tilde-diacritica-73.html
*/
object DataSourceGame1 {
    val listPairOfCards1 = listOf(
        Game1Model(1,"Tú",1,"Pronombre personal"),
        Game1Model(2,"Tu",2,"Adjetivo posesivo"),
        Game1Model(1,"Él",1,"Pronombre personal"),
        Game1Model(3,"El",3,"Artículo"),
        Game1Model(1,"Mí",1,"Pronombre personal"),
        Game1Model(2,"Mi",2,"Adjetivo posesivo")
    )
}