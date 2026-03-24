package com.yjotdev.ortografiamariamel.application.utils

object Helper {
    fun isValidUser(input: String): Boolean{
        return Regex("^[A-Za-z]{3,10}\$").matches(input)
    }
}