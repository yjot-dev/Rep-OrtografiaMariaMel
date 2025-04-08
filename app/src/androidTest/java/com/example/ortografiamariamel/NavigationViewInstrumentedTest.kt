package com.example.ortografiamariamel

import android.content.Context
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.swipeRight
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.ortografiamariamel.navigation.NavigationView
import com.example.ortografiamariamel.navigation.ViewRoutes
import com.example.ortografiamariamel.theme.OrtografiaMariaMelTheme
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NavigationViewInstrumentedTest {
    @get:Rule
    val composeTestRule = createComposeRule()
    // Contexto del test de la app.
    private val context: Context = ApplicationProvider.getApplicationContext()

    @Test
    fun navigationApp() {
        // NavController del Test
        val navController = TestNavHostController(context).apply {
            navigatorProvider.addNavigator(ComposeNavigator())
        }
        composeTestRule.setContent {
            OrtografiaMariaMelTheme{
                NavigationView(navController = navController)
            }
        }
        //Verifica si esta en la pagina inicial
        assertEquals(ViewRoutes.Start.name, navController.currentBackStackEntry?.destination?.route)
        //Navega a la siguiente pagina
        composeTestRule.onNodeWithText(context.getString(R.string.button_start))
            .performClick()
        //Verifica si esta en la pagina de registro
        assertEquals(ViewRoutes.Register.name, navController.currentBackStackEntry?.destination?.route)
        //Escribe el nombre del jugador
        composeTestRule.onNodeWithTag("tag_write_name")
            .performTextInput("Yasser")
        //Elige la edad del jugador
        val maxAge = 18
        val targetAge = (14/maxAge).toFloat()
        composeTestRule.onNodeWithTag("tag_choose_age")
            .performTouchInput {
                swipeRight(0f, targetAge)
            }
        //Navega a la siguiente pagina
        composeTestRule.onNodeWithText(context.getString(R.string.button_next))
            .performClick()
        //Verifica si esta en la pagina de menu
        assertEquals(ViewRoutes.Menu.name, navController.currentBackStackEntry?.destination?.route)
        //Muestra el menu
        composeTestRule.onNodeWithTag("Menu").performClick()
        //Navega a la pagina de portada
        composeTestRule.onNodeWithText(context.getString(R.string.button_cover))
            .performClick()
        //Verifica si esta en la pagina de portada
        assertEquals(ViewRoutes.Cover.name, navController.currentBackStackEntry?.destination?.route)
        //Navega hacia atras
        composeTestRule.onNodeWithContentDescription(context.getString(R.string.button_back))
            .performClick()
        //Verifica si esta en la pagina de menu
        assertEquals(ViewRoutes.Menu.name, navController.currentBackStackEntry?.destination?.route)
        //Muestra el menu
        composeTestRule.onNodeWithTag("Menu").performClick()
        //Despliega opciones de la unidad 1
        composeTestRule.onNodeWithTag("unidad_1")
            .performClick()
        //Navega a la pagina del tema de la unidad 1
        composeTestRule.onNodeWithTag("tema_1")
            .performClick()
        //Verifica si esta en la pagina del tema de la unidad 1
        assertEquals(ViewRoutes.TopicUnit1.name, navController.currentBackStackEntry?.destination?.route)
        //Navega hacia atras
        composeTestRule.onNodeWithContentDescription(context.getString(R.string.button_back))
            .performClick()
        //Verifica si esta en la pagina de menu
        assertEquals(ViewRoutes.Menu.name, navController.currentBackStackEntry?.destination?.route)
        //Muestra el menu
        composeTestRule.onNodeWithTag("Menu").performClick()
        //Despliega opciones de la unidad 1
        composeTestRule.onNodeWithTag("unidad_1")
            .performClick()
        //Navega a la pagina de la actividad de la unidad 1
        composeTestRule.onNodeWithTag("actividad_1")
            .performClick()
        //Verifica si esta en la pagina de la actividad de la unidad 1
        assertEquals(ViewRoutes.ActivityUnit1.name, navController.currentBackStackEntry?.destination?.route)
    }
}