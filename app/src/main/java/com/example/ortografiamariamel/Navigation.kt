package com.example.ortografiamariamel

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.ortografiamariamel.ui.viewModel.PlayerViewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.ortografiamariamel.data.DataSourceGame1
import com.example.ortografiamariamel.data.DataSourceGame2
import com.example.ortografiamariamel.ui.theme.Typography
import com.example.ortografiamariamel.ui.views.ActivityView
import com.example.ortografiamariamel.ui.views.utils.CoverView
import com.example.ortografiamariamel.ui.views.RegisterView
import com.example.ortografiamariamel.ui.views.StartView
import com.example.ortografiamariamel.ui.views.TopicView
import com.example.ortografiamariamel.ui.views.MenuView

enum class RoutesViews(@StringRes val title: Int){
    Start(title = R.string.start_view),
    Register(title = R.string.register_view),
    Menu(title = R.string.units_view),
    TopicUnit1(title = R.string.button_unit_1),
    ActivityUnit1(title = R.string.button_unit_1),
    TopicUnit2(title = R.string.button_unit_2),
    ActivityUnit2(title = R.string.button_unit_2),
    TopicUnit3(title = R.string.button_unit_3),
    ActivityUnit3(title = R.string.button_unit_3),
    TopicUnit4(title = R.string.button_unit_4),
    ActivityUnit4(title = R.string.button_unit_4),
    Cover(title = R.string.button_cover),
}

//Listas del juego 1 (Una lista por unidad)
val listUnit1Game1 = DataSourceGame1.listPairOfCards1
//Listas del juego 2 ()
val listUnitsGame2 = DataSourceGame2.listQuestionAndAnswers

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarScreen(
    routeTitles: RoutesViews,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
){
    TopAppBar(
        title = {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(horizontal = dimensionResource(id = R.dimen.short2_dp))
            ){
                Image(
                    painter = painterResource(id = R.drawable.app_shield),
                    colorFilter = ColorFilter
                        .tint(MaterialTheme.colorScheme.onPrimaryContainer),
                    contentDescription = null,
                    modifier = Modifier.size(dimensionResource(id = R.dimen.short7_dp))
                )
                Text(
                    text = stringResource(routeTitles.title),
                    style = Typography.titleLarge,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    modifier = Modifier.weight(1f)
                )
                Image(
                    painter = painterResource(id = R.drawable.app_name),
                    contentDescription = null,
                    modifier = Modifier.size(dimensionResource(id = R.dimen.short7_dp))
                )
            }
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        navigationIcon = {
            if(canNavigateBack){
                IconButton(onClick = navigateUp){
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = stringResource(R.string.button_back),
                        tint = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
            }
        },
        modifier = modifier.height(dimensionResource(id = R.dimen.short7_dp))
    )
}

@Composable
fun AppScreen(
    navController: NavHostController
){
    val vmPlayer = PlayerViewModel()
    val uiStatePlayer by vmPlayer.uiState.collectAsState()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = RoutesViews.valueOf(
        backStackEntry?.destination?.route ?: RoutesViews.Start.name)
    Scaffold(
        topBar = {
            TopBarScreen(
                routeTitles = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = RoutesViews.Start.name,
            modifier = Modifier.padding(innerPadding)
        ){
            composable(route = RoutesViews.Start.name){
                StartView(
                    onStartClicked = { navController.navigate(RoutesViews.Register.name) },
                    modifier = Modifier.fillMaxSize()
                )
            }
            composable(route = RoutesViews.Register.name){
                RegisterView(
                    valueName = uiStatePlayer.name,
                    onValueChangeName = { vmPlayer.setName(it) },
                    valueAge = uiStatePlayer.age.toFloat(),
                    onValueChangeAge = { vmPlayer.setAge(it.toInt()) },
                    onNextClicked = { navController.navigate(RoutesViews.Menu.name) },
                    enabledNextButton = uiStatePlayer.name.isNotBlank(),
                    modifier = Modifier.fillMaxSize()
                )
            }
            composable(route = RoutesViews.Menu.name){
                MenuView(
                    vmPlayer = vmPlayer,
                    onCoverClicked = { navController.navigate(RoutesViews.Cover.name) },
                    onTopic1Clicked = { navController.navigate(RoutesViews.TopicUnit1.name) },
                    onTopic2Clicked = { navController.navigate(RoutesViews.TopicUnit2.name) },
                    onTopic3Clicked = { navController.navigate(RoutesViews.TopicUnit3.name) },
                    onTopic4Clicked = { navController.navigate(RoutesViews.TopicUnit4.name) },
                    onActivity1Clicked = { navController.navigate(RoutesViews.ActivityUnit1.name) },
                    onActivity2Clicked = { navController.navigate(RoutesViews.ActivityUnit2.name) },
                    onActivity3Clicked = { navController.navigate(RoutesViews.ActivityUnit3.name) },
                    onActivity4Clicked = { navController.navigate(RoutesViews.ActivityUnit4.name) },
                    modifier = Modifier.fillMaxSize()
                )
            }
            composable(route = RoutesViews.TopicUnit1.name){
                TopicView(
                    subtitleId = R.string.subtitle_unit_1,
                    summaryId = R.string.summary_unit_1,
                    imageId = R.drawable.logo_topic_one,
                    modifier = Modifier.fillMaxSize()
                )
            }
            composable(route = RoutesViews.ActivityUnit1.name){
                ActivityView(
                    vmPlayer = vmPlayer,
                    numberUnit = stringResource(R.string.button_unit_1),
                    list1Game1 = listUnit1Game1,
                    list2Game1 = listUnit1Game1.shuffled(),
                    listGame2 = listUnitsGame2,
                    indexGame2 = 0, //0 y 1
                    onReturnClicked = { navController.popBackStack(
                        RoutesViews.Start.name, false
                    ) },
                    modifier = Modifier.fillMaxSize()
                )
            }
            composable(route = RoutesViews.TopicUnit2.name){
                //Pendiente de realizar
            }
            composable(route = RoutesViews.ActivityUnit2.name){
                //Pendiente de realizar
            }
            composable(route = RoutesViews.TopicUnit3.name){
                //Pendiente de realizar
            }
            composable(route = RoutesViews.ActivityUnit3.name){
                //Pendiente de realizar
            }
            composable(route = RoutesViews.TopicUnit4.name){
                //Pendiente de realizar
            }
            composable(route = RoutesViews.ActivityUnit4.name){
                //Pendiente de realizar
            }
            composable(route = RoutesViews.Cover.name){
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