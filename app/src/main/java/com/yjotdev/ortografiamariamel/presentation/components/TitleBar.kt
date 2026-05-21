package com.yjotdev.ortografiamariamel.presentation.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import com.yjotdev.ortografiamariamel.R
import com.yjotdev.ortografiamariamel.presentation.navigation.ViewRoutes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TitleBar(
    currentScreen: ViewRoutes,
    canNavigateBack: Boolean,
    numLife: Int,
    numError: Int,
    currentLesson: Int,
    totalLesson: Int,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
){
    // No muestres encabezado para la pantalla del inicio
    if (currentScreen == ViewRoutes.Start) {
        return
    }
    // Define las rutas que mostrarán el progreso del nivel
    val screensWithProgressLevel = setOf(
        ViewRoutes.ActivityUnit1,
        ViewRoutes.ActivityUnit2,
        ViewRoutes.ActivityUnit3,
        ViewRoutes.ActivityUnit4
    )
    // Renderiza la TopAppBar con el contenido correcto
    TopAppBar(
        title = {
            if (currentScreen in screensWithProgressLevel){
                TopBarProgress(
                    modifier = modifier,
                    currentLesson = currentLesson,
                    totalLesson = totalLesson,
                    numLife = numLife,
                    numError = numError
                )
            } else {
                TopBarBanner(
                    modifier = modifier,
                    title = stringResource(currentScreen.title)
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        navigationIcon = {
            if(canNavigateBack){
                IconButton(onClick = navigateUp){
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.arrow_back_48),
                        tint = MaterialTheme.colorScheme.onPrimaryContainer,
                        contentDescription = stringResource(R.string.button_back),
                        modifier = Modifier.size(dimensionResource(R.dimen.medium1_dp))
                    )
                }
            }
        },
        modifier = modifier
    )
}