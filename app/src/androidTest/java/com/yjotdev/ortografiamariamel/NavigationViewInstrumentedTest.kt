package com.yjotdev.ortografiamariamel

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.swipeRight
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import dagger.hilt.android.testing.HiltAndroidRule
import com.yjotdev.ortografiamariamel.application.navigation.Navigation
import com.yjotdev.ortografiamariamel.application.navigation.ViewRoutes
import com.yjotdev.ortografiamariamel.application.theme.OrtografiaMariaMelTheme

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class NavigationViewInstrumentedTest {

    @get:Rule(order = 0)
    var hiltRule: HiltAndroidRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<HiltTestActivity>()

    lateinit var navController: TestNavHostController // NavController del Test

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun navigationApp() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())

            OrtografiaMariaMelTheme{
                Navigation(navController = navController)
            }
        }
        //Verifica si esta en la pagina inicial
        assertEquals(ViewRoutes.Start.name, navController.currentBackStackEntry?.destination?.route)
        //Navega a la siguiente pagina
        composeTestRule.onNodeWithText("COMENZAR")
            .performClick()
        //Verifica si esta en la pagina de registro
        assertEquals(ViewRoutes.Register.name, navController.currentBackStackEntry?.destination?.route)
        //Escribe el nombre del jugador
        composeTestRule.onNodeWithTag("tag_write_name")
            .performTextInput("Yasser")
        //Elige la edad del jugador
        composeTestRule.onNodeWithTag("tag_choose_age")
            .performTouchInput {
                swipeRight(0f, 0.7f)
            }
        //Navega a la siguiente pagina
        composeTestRule.onNodeWithText("SIGUIENTE")
            .performClick()
        //Verifica si esta en la pagina de menu
        assertEquals(ViewRoutes.Menu.name, navController.currentBackStackEntry?.destination?.route)
        //Muestra el menu
        composeTestRule.onNodeWithTag("Menu").performClick()
        //Navega a la pagina de portada
        composeTestRule.onNodeWithText("PORTADA")
            .performClick()
        //Verifica si esta en la pagina de portada
        assertEquals(ViewRoutes.Cover.name, navController.currentBackStackEntry?.destination?.route)
        //Navega hacia atras
        composeTestRule.onNodeWithContentDescription("Volver")
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
        composeTestRule.onNodeWithContentDescription("Volver")
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