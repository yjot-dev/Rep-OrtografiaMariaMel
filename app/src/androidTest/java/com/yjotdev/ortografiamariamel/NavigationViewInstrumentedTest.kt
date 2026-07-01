package com.yjotdev.ortografiamariamel

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
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
import com.yjotdev.ortografiamariamel.presentation.navigation.Navigation
import com.yjotdev.ortografiamariamel.presentation.navigation.ViewRoutes
import com.yjotdev.ortografiamariamel.presentation.theme.OrtografiaMariaMelTheme
import com.yjotdev.ortografiamariamel.presentation.utils.TestTags

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class NavigationViewInstrumentedTest {

    @get:Rule(order = 0)
    var hiltRule: HiltAndroidRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<HiltTestActivity>()

    private lateinit var navController: TestNavHostController // NavController del Test

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
        //Navega a la siguiente página
        composeTestRule.onNodeWithTag(TestTags.START_VIEW_BTN_START)
            .performClick()
        //Verifica si esta en la pagina de registro
        assertEquals(ViewRoutes.Register.name, navController.currentBackStackEntry?.destination?.route)
        //Escribe el nombre del jugador
        composeTestRule.onNodeWithTag(TestTags.TEXT_FIELD_WRITE_NAME)
            .performTextInput("Yasser")
        //Elige la edad del jugador
        composeTestRule.onNodeWithTag(TestTags.SLIDER_CHOOSE_AGE)
            .performTouchInput {
                swipeRight(0f, 0.7f)
            }
        //Navega a la siguiente página
        composeTestRule.onNodeWithTag(TestTags.REGISTER_VIEW_BTN_NEXT)
            .performClick()
        //Verifica si está en la pagina de menu
        assertEquals(ViewRoutes.Menu.name, navController.currentBackStackEntry?.destination?.route)
        //Muestra el menu
        composeTestRule.onNodeWithTag(TestTags.MENU).performClick()
        //Navega a la página de portada
        composeTestRule.onNodeWithTag(TestTags.MENU_BTN_COVER)
            .performClick()
        //Verifica si está en la página de portada
        assertEquals(ViewRoutes.Cover.name, navController.currentBackStackEntry?.destination?.route)
        //Navega hacia atrás
        composeTestRule.onNodeWithTag(TestTags.BTN_BACK)
            .performClick()
        //Verifica si está en la página de menu
        assertEquals(ViewRoutes.Menu.name, navController.currentBackStackEntry?.destination?.route)
        //Muestra el menu
        composeTestRule.onNodeWithTag(TestTags.MENU).performClick()
        //Despliega opciones de la unidad 1
        composeTestRule.onNodeWithTag(TestTags.UNIT_ITEM)
            .performClick()
        //Navega a la pagina del tema de la unidad 1
        composeTestRule.onNodeWithTag(TestTags.THEME_ITEM)
            .performClick()
        //Verifica si esta en la pagina del tema de la unidad 1
        assertEquals(ViewRoutes.TopicUnit1.name, navController.currentBackStackEntry?.destination?.route)
        //Navega hacia atrás
        composeTestRule.onNodeWithTag(TestTags.BTN_BACK)
            .performClick()
        //Verifica si está en la página de menu
        assertEquals(ViewRoutes.Menu.name, navController.currentBackStackEntry?.destination?.route)
        //Muestra el menu
        composeTestRule.onNodeWithTag(TestTags.MENU).performClick()
        //Despliega opciones de la unidad 1
        composeTestRule.onNodeWithTag(TestTags.UNIT_ITEM)
            .performClick()
        //Navega a la página de la actividad de la unidad 1
        composeTestRule.onNodeWithTag(TestTags.ACTIVITY_ITEM)
            .performClick()
        //Verifica si está en la página de la actividad de la unidad 1
        assertEquals(ViewRoutes.ActivityUnit1.name, navController.currentBackStackEntry?.destination?.route)
    }
}