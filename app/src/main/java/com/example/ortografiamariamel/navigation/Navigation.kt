package com.example.ortografiamariamel.navigation

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
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.ortografiamariamel.mvvm.viewmodel.PlayerViewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.ortografiamariamel.R
import com.example.ortografiamariamel.theme.Typography
import com.example.ortografiamariamel.mvvm.view.ActivityView
import com.example.ortografiamariamel.composable.CoverView
import com.example.ortografiamariamel.mvvm.view.RegisterView
import com.example.ortografiamariamel.mvvm.view.StartView
import com.example.ortografiamariamel.mvvm.view.TopicView
import com.example.ortografiamariamel.mvvm.view.MenuView

@Composable
fun NavigationView(
    navController: NavHostController = rememberNavController(),
    vmPlayer: PlayerViewModel = viewModel()
){
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = ViewRoutes.valueOf(
        backStackEntry?.destination?.route ?: ViewRoutes.Start.name
    )
    Scaffold(
        topBar = {
            TitleBar(
                routeTitles = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
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
                    vmPlayer = vmPlayer,
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
                    onActivity1Clicked = { navController.navigate(ViewRoutes.ActivityUnit1.name) },
                    onActivity2Clicked = { navController.navigate(ViewRoutes.ActivityUnit2.name) },
                    onActivity3Clicked = { navController.navigate(ViewRoutes.ActivityUnit3.name) },
                    onActivity4Clicked = { navController.navigate(ViewRoutes.ActivityUnit4.name) },
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
                    vmPlayer = vmPlayer,
                    numberUnit = stringResource(R.string.button_unit_1),
                    listGame1 = vmPlayer.listUnit1Game1,
                    listGame2 = vmPlayer.listUnit1Game2,
                    listGame3 = vmPlayer.listUnit1Game3,
                    onReturnClicked = { navController.popBackStack(
                        ViewRoutes.Start.name, false
                    )},
                    modifier = Modifier.fillMaxSize()
                )
            }
            composable(route = ViewRoutes.TopicUnit2.name){
                //Pendiente de realizar
            }
            composable(route = ViewRoutes.ActivityUnit2.name){
                //Pendiente de realizar
            }
            composable(route = ViewRoutes.TopicUnit3.name){
                //Pendiente de realizar
            }
            composable(route = ViewRoutes.ActivityUnit3.name){
                //Pendiente de realizar
            }
            composable(route = ViewRoutes.TopicUnit4.name){
                //Pendiente de realizar
            }
            composable(route = ViewRoutes.ActivityUnit4.name){
                //Pendiente de realizar
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TitleBar(
    routeTitles: ViewRoutes,
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
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.button_back),
                        tint = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
            }
        },
        modifier = modifier.height(dimensionResource(id = R.dimen.short7_dp))
    )
}