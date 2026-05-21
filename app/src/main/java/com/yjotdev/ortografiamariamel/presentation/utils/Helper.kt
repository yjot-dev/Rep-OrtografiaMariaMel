package com.yjotdev.ortografiamariamel.presentation.utils

object Helper {
    fun isValidUser(input: String): Boolean{
        return Regex("^[A-Za-z]{3,10}\$").matches(input)
    }
}