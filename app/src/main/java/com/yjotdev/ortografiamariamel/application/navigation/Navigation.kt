package com.yjotdev.ortografiamariamel.application.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.yjotdev.ortografiamariamel.R
import com.yjotdev.ortografiamariamel.application.mvvm.viewmodel.PlayerViewModel
import com.yjotdev.ortografiamariamel.application.mvvm.view.ActivityView
import com.yjotdev.ortografiamariamel.application.components.CoverView
import com.yjotdev.ortografiamariamel.application.components.TitleBar
import com.yjotdev.ortografiamariamel.application.mvvm.view.RegisterView
import com.yjotdev.ortografiamariamel.application.mvvm.view.StartView
import com.yjotdev.ortografiamariamel.application.mvvm.view.TopicView
import com.yjotdev.ortografiamariamel.application.mvvm.view.MenuView

@Composable
fun Navigation(
    navController: NavHostController = rememberNavController(),
    vmPlayer: PlayerViewModel = hiltViewModel()
){
    val uiState by vmPlayer.uiState.collectAsState()
    //Número de lecciones a revisar por unidad incluyendo la ventana puntaje
    val numLessons = 4
    //Número de vidas del jugador
    val numLife = 3
    //Vista ToolBarMenu
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = ViewRoutes.valueOf(
        backStackEntry?.destination?.route ?: ViewRoutes.Start.name
    )
    //UI
    Scaffold(
        topBar = {
            TitleBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                numLife = numLife,
                numError = uiState.error,
                currentLesson = uiState.currentLesson,
                totalLesson = numLessons,
                navigateUp = { navController.navigateUp() },
                modifier = Modifier.fillMaxWidth()
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = ViewRoutes.Start.name,
            modifier = Modifier.padding(innerPadding)
        ){
            composable(route = ViewRoutes.Start.name){
                StartView(
                    onStartClicked = { navController.navigate(ViewRoutes.Register.name) },
                    modifier = Modifier.fillMaxSize()
                )
            }
            composable(route = ViewRoutes.Register.name){
                RegisterView(
                    name = uiState.name,
                    age = uiState.age,
                    onName = { vmPlayer.setName(it) },
                    onAge = { vmPlayer.setAge(it) },
                    onNextClicked = { navController.navigate(ViewRoutes.Menu.name) },
                    modifier = Modifier.fillMaxSize()
                )
            }
            composable(route = ViewRoutes.Menu.name){
                MenuView(
                    onCoverClicked = { navController.navigate(ViewRoutes.Cover.name) },
                    onTopic1Clicked = { navController.navigate(ViewRoutes.TopicUnit1.name) },
                    onTopic2Clicked = { navController.navigate(ViewRoutes.TopicUnit2.name) },
                    onTopic3Clicked = { navController.navigate(ViewRoutes.TopicUnit3.name) },
                    onTopic4Clicked = { navController.navigate(ViewRoutes.TopicUnit4.name) },
                    onActivity1Clicked = {
                        vmPlayer.loadGames(1)
                        navController.navigate(ViewRoutes.ActivityUnit1.name)
                    },
                    onActivity2Clicked = {
                        vmPlayer.loadGames(2)
                        navController.navigate(ViewRoutes.ActivityUnit2.name)
                    },
                    onActivity3Clicked = {
                        vmPlayer.loadGames(3)
                        navController.navigate(ViewRoutes.ActivityUnit3.name)
                    },
                    onActivity4Clicked = {
                        vmPlayer.loadGames(4)
                        navController.navigate(ViewRoutes.ActivityUnit4.name)
                    },
                    modifier = Modifier.fillMaxSize()
                )
            }
            composable(route = ViewRoutes.TopicUnit1.name){
                TopicView(
                    subtitleId = R.string.subtitle_unit_1,
                    summaryId = R.string.summary_unit_1,
                    imageId = R.drawable.logo_topic_one,
                    modifier = Modifier.fillMaxSize()
                )
            }
            composable(route = ViewRoutes.ActivityUnit1.name){
                ActivityView(
                    numberUnit = stringResource(R.string.button_unit_1),
                    listGame1 = uiState.game1data,
                    listGame2 = uiState.game2data,
                    listGame3 = uiState.game3data,
                    numLessons = numLessons,
                    currentLesson = uiState.currentLesson,
                    numLife = numLife,
                    name = uiState.name,
                    age = uiState.age,
                    finishGame = uiState.finishGame,
                    success = uiState.success,
                    error = uiState.error,
                    score = uiState.score,
                    idScenery = uiState.idScenery,
                    onSuccess = { vmPlayer.setSuccess(it) },
                    onError = { vmPlayer.setError(it) },
                    onIdScenery = { vmPlayer.setIdScenery(it) },
                    onFinishGame = { vmPlayer.setFinishGame(it) },
                    onCurrentLesson = { vmPlayer.setCurrentLesson(it) },
                    onReturnClicked = {
                        vmPlayer.resetPlayer()
                        navController.popBackStack(ViewRoutes.Start.name, false)
                    },
                    modifier = Modifier.fillMaxSize()
                )
            }
            composable(route = ViewRoutes.TopicUnit2.name){
                TODO("Pendiente de realizar")
            }
            composable(route = ViewRoutes.ActivityUnit2.name){
                TODO("Pendiente de realizar")
            }
            composable(route = ViewRoutes.TopicUnit3.name){
                TODO("Pendiente de realizar")
            }
            composable(route = ViewRoutes.ActivityUnit3.name){
                TODO("Pendiente de realizar")
            }
            composable(route = ViewRoutes.TopicUnit4.name){
                TODO("Pendiente de realizar")
            }
            composable(route = ViewRoutes.ActivityUnit4.name){
                TODO("Pendiente de realizar")
            }
            composable(route = ViewRoutes.Cover.name){
                Column(modifier = Modifier
                    .background(MaterialTheme.colorScheme.onPrimary)) {
                    CoverView(
                        modifier = Modifier.fillMaxSize(),
                        idImage = R.drawable.logo_cover
                    )
                }
            }
        }
    }
}