package com.example.ortografiamariamel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.ortografiamariamel.navigation.NavigationView
import com.example.ortografiamariamel.theme.OrtografiaMariaMelTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OrtografiaMariaMelTheme{
                NavigationView()
            }
        }
    }
}