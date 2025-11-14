package com.yjotdev.ortografiamariamel.infrastructure.datasource

import com.yjotdev.ortografiamariamel.domain.entity.Game1Entity
/*
¿Cuáles son los tipos de tilde diacrítica?
https://www.unprofesor.com/lengua-espanola/la-tilde-diacritica-73.html
*/
object DataSourceGame1 {
    val listPairOfCardsUnit1 = listOf(
        //Columna 1
        Game1Entity(1,"Tú"),
        Game1Entity(2,"Tu"),
        Game1Entity(1,"Él"),
        Game1Entity(3,"El"),
        Game1Entity(1,"Mí"),
        Game1Entity(2,"Mi"),
        //Columna 2
        Game1Entity(1,"Pronombre personal"),
        Game1Entity(2,"Adjetivo posesivo"),
        Game1Entity(1,"Pronombre personal"),
        Game1Entity(3,"Artículo"),
        Game1Entity(1,"Pronombre personal"),
        Game1Entity(2,"Adjetivo posesivo")
    )
}