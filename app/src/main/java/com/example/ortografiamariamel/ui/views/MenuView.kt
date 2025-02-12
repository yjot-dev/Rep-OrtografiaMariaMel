package com.example.ortografiamariamel.ui.views

import android.content.res.Configuration
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.ortografiamariamel.R
import com.example.ortografiamariamel.ui.theme.OrtografiaMariaMelTheme
import com.example.ortografiamariamel.ui.theme.Typography
import com.example.ortografiamariamel.ui.viewModel.PlayerViewModel
import com.example.ortografiamariamel.ui.views.utils.RecyclerButton

@Composable
fun MenuView(
    modifier: Modifier = Modifier,
    vmPlayer: PlayerViewModel = PlayerViewModel(),
    onCoverClicked: () -> Unit,
    onTopic1Clicked: () -> Unit,
    onTopic2Clicked: () -> Unit,
    onTopic3Clicked: () -> Unit,
    onTopic4Clicked: () -> Unit,
    onActivity1Clicked: () -> Unit,
    onActivity2Clicked: () -> Unit,
    onActivity3Clicked: () -> Unit,
    onActivity4Clicked: () -> Unit
    ){
    //Instancias de los estados ViewModel
    val uiStatePlayer by vmPlayer.uiState.collectAsState()
    val condition1 = uiStatePlayer.nextUnit == 1 //primer vista
    val condition2 = uiStatePlayer.nextUnit == 5 //ultima vista
    Box(
        modifier = modifier.background(MaterialTheme.colorScheme.onPrimary),
        contentAlignment = Alignment.TopStart)
    {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = modifier
        ) {
            when(uiStatePlayer.nextUnit){
                1 -> UnitsCoverGroup(
                    modifier = Modifier.fillMaxHeight(0.87f)
                )
                2 -> UnitView(
                    imageId = R.drawable.unit_one,
                    numberUnit = 1,
                    onTitleClicked = onTopic1Clicked,
                    onActivityClicked = onActivity1Clicked,
                    modifier = Modifier.fillMaxHeight(0.87f)
                )
                3 -> UnitView(
                    imageId = R.drawable.unit_two,
                    numberUnit = 1,
                    onTitleClicked = onTopic2Clicked,
                    onActivityClicked = onActivity2Clicked,
                    modifier = Modifier.fillMaxHeight(0.87f)
                )
                4 -> UnitView(
                    imageId = R.drawable.unit_three,
                    numberUnit = 1,
                    onTitleClicked = onTopic3Clicked,
                    onActivityClicked = onActivity3Clicked,
                    modifier = Modifier.fillMaxHeight(0.87f)
                )
                5 -> UnitView(
                    imageId = R.drawable.unit_four,
                    numberUnit = 1,
                    onTitleClicked = onTopic4Clicked,
                    onActivityClicked = onActivity4Clicked,
                    modifier = Modifier.fillMaxHeight(0.87f)
                )
            }
            Row(
                horizontalArrangement = Arrangement
                    .spacedBy(dimensionResource(id = R.dimen.short4_dp)),
                modifier = Modifier.padding(
                    vertical = dimensionResource(id = R.dimen.short2_dp),
                    horizontal = dimensionResource(id = R.dimen.short5_dp))
            ){
                RecyclerButton(
                    textButton = stringResource(id = R.string.button_down),
                    isEnabled = !condition1,
                    modifier = Modifier
                        .fillMaxHeight(0.9f)
                        .weight(1f),
                    onClick = {
                        vmPlayer.setNextUnit(uiStatePlayer.nextUnit - 1)
                    })
                RecyclerButton(
                    textButton = stringResource(id = R.string.button_up),
                    isEnabled = !condition2,
                    modifier = Modifier
                        .fillMaxHeight(0.9f)
                        .weight(1f),
                    onClick = {
                        vmPlayer.setNextUnit(uiStatePlayer.nextUnit + 1)
                    })
            }
        }
        AnimatedVisibility(
            visible = uiStatePlayer.visibleMenu,
            enter = slideInHorizontally(),
            exit = slideOutHorizontally()
        ){
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(0.47f)
                    .clickable(enabled = false) {}
                    .background(color = MaterialTheme.colorScheme.primaryContainer),
                contentAlignment = Alignment.TopCenter
            ){
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth(0.9f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ){
                    RecyclerButton(
                        textButton = stringResource(id = R.string.button_cover),
                        colorButton = MaterialTheme.colorScheme.primary,
                        colorBorder = MaterialTheme.colorScheme.primary,
                        colorText = MaterialTheme.colorScheme.onPrimaryContainer,
                        styleText = Typography.titleSmall,
                        shape = RoundedCornerShape(
                            dimensionResource(id = R.dimen.short4_dp)),
                        modifier = Modifier
                            .sizeIn(
                                minHeight = dimensionResource(id = R.dimen.short7_dp),
                                maxHeight = dimensionResource(id = R.dimen.medium3_dp)
                            )
                            .fillMaxWidth(),
                        onClick = onCoverClicked
                    )
                    Spacer(modifier = Modifier.sizeIn(
                        minHeight = dimensionResource(id = R.dimen.short2_dp),
                        maxHeight = dimensionResource(id = R.dimen.short4_dp)
                    ))
                    ItemMenu(
                        itemText = "1",
                        isVisible = uiStatePlayer.visibleItem1,
                        onVisible = { vmPlayer.setVisibleItem1(!uiStatePlayer.visibleItem1)
                            vmPlayer.setVisibleItem2(false)
                            vmPlayer.setVisibleItem3(false)
                            vmPlayer.setVisibleItem4(false)
                        },
                        onTopicClicked = onTopic1Clicked,
                        onActivityClicked = onActivity1Clicked,
                        modifier = Modifier
                            .sizeIn(
                                minHeight = dimensionResource(id = R.dimen.short7_dp),
                                maxHeight = dimensionResource(id = R.dimen.medium3_dp)
                            )
                            .fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.sizeIn(
                        minHeight = dimensionResource(id = R.dimen.short2_dp),
                        maxHeight = dimensionResource(id = R.dimen.short4_dp)
                    ))
                    ItemMenu(
                        itemText = "2",
                        isVisible = uiStatePlayer.visibleItem2,
                        onVisible = { vmPlayer.setVisibleItem2(!uiStatePlayer.visibleItem2)
                            vmPlayer.setVisibleItem1(false)
                            vmPlayer.setVisibleItem3(false)
                            vmPlayer.setVisibleItem4(false)
                        },
                        onTopicClicked = onTopic2Clicked,
                        onActivityClicked = onActivity2Clicked,
                        modifier = Modifier
                            .sizeIn(
                                minHeight = dimensionResource(id = R.dimen.short7_dp),
                                maxHeight = dimensionResource(id = R.dimen.medium3_dp)
                            )
                            .fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.sizeIn(
                        minHeight = dimensionResource(id = R.dimen.short2_dp),
                        maxHeight = dimensionResource(id = R.dimen.short4_dp)
                    ))
                    ItemMenu(
                        itemText = "3",
                        isVisible = uiStatePlayer.visibleItem3,
                        onVisible = { vmPlayer.setVisibleItem3(!uiStatePlayer.visibleItem3)
                            vmPlayer.setVisibleItem1(false)
                            vmPlayer.setVisibleItem2(false)
                            vmPlayer.setVisibleItem4(false)
                        },
                        onTopicClicked = onTopic3Clicked,
                        onActivityClicked = onActivity3Clicked,
                        modifier = Modifier
                            .sizeIn(
                                minHeight = dimensionResource(id = R.dimen.short7_dp),
                                maxHeight = dimensionResource(id = R.dimen.medium3_dp)
                            )
                            .fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.sizeIn(
                        minHeight = dimensionResource(id = R.dimen.short2_dp),
                        maxHeight = dimensionResource(id = R.dimen.short4_dp)
                    ))
                    ItemMenu(
                        itemText = "4",
                        isVisible = uiStatePlayer.visibleItem4,
                        onVisible = { vmPlayer.setVisibleItem4(!uiStatePlayer.visibleItem4)
                            vmPlayer.setVisibleItem1(false)
                            vmPlayer.setVisibleItem2(false)
                            vmPlayer.setVisibleItem3(false)
                        },
                        onTopicClicked = onTopic4Clicked,
                        onActivityClicked = onActivity4Clicked,
                        modifier = Modifier
                            .sizeIn(
                                minHeight = dimensionResource(id = R.dimen.short7_dp),
                                maxHeight = dimensionResource(id = R.dimen.medium3_dp)
                            )
                            .fillMaxWidth()
                    )
                }
            }
        }
        FloatingActionButton(
            modifier = Modifier
                .fillMaxWidth(0.16f)
                .fillMaxHeight(0.1f)
                .testTag("Menu"),
            containerColor = MaterialTheme.colorScheme.onPrimary
                .copy(0.8f),
            onClick = {
                vmPlayer.setVisibleMenu(!uiStatePlayer.visibleMenu)
                vmPlayer.setVisibleItem1(false)
                vmPlayer.setVisibleItem2(false)
                vmPlayer.setVisibleItem3(false)
                vmPlayer.setVisibleItem4(false)
            })
        {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                imageVector = Icons.Default.List,
                contentDescription = null,
                colorFilter = ColorFilter
                    .tint(MaterialTheme.colorScheme.primaryContainer))
        }
    }
}

@Composable
private fun ItemMenu(
    modifier: Modifier = Modifier,
    itemText: String,
    isVisible: Boolean,
    onVisible: () -> Unit,
    onTopicClicked: () -> Unit,
    onActivityClicked: () -> Unit
){
    val backgroundColor by animateColorAsState(
        if (isVisible) MaterialTheme.colorScheme.onPrimary
        else MaterialTheme.colorScheme.primary, label = "")
    Card(
        modifier = Modifier.animateContentSize(),
        shape = RoundedCornerShape(
            dimensionResource(id = R.dimen.short4_dp)),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary,
        )
    ){
        RecyclerButton(
            textButton = "UNIDAD $itemText",
            colorButton = backgroundColor,
            colorBorder = backgroundColor,
            colorText = MaterialTheme.colorScheme.onPrimaryContainer,
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = dimensionResource(R.dimen.short2_dp)
            ),
            shape = RoundedCornerShape(dimensionResource(id = R.dimen.short4_dp)),
            styleText = Typography.titleSmall,
            onClick = onVisible,
            modifier = modifier.testTag("unidad_$itemText")
        )
        AnimatedVisibility(visible = isVisible){
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ){
                RecyclerButton(
                    textButton = stringResource(id = R.string.title_unit),
                    colorButton = MaterialTheme.colorScheme.primary,
                    colorBorder = MaterialTheme.colorScheme.primary,
                    colorText = MaterialTheme.colorScheme.onPrimaryContainer,
                    styleText = Typography.titleSmall,
                    onClick = onTopicClicked,
                    modifier = modifier.testTag("tema_$itemText")
                )
                RecyclerButton(
                    textButton = stringResource(id = R.string.activity_unit),
                    colorButton = MaterialTheme.colorScheme.primary,
                    colorBorder = MaterialTheme.colorScheme.primary,
                    colorText = MaterialTheme.colorScheme.onPrimaryContainer,
                    styleText = Typography.titleSmall,
                    onClick = onActivityClicked,
                    modifier = modifier.testTag("actividad_$itemText")
                )
            }
        }
    }
}

@Composable
private fun UnitsCoverGroup(
    modifier: Modifier = Modifier
){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
    ){
        Image(
            painter = painterResource(id = R.drawable.logo_units),
            modifier = Modifier.weight(1f),
            contentScale = ContentScale.Fit,
            contentDescription = null
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.weight(0.8f)
        ){
            UnitsCover(
                textId = R.string.button_unit_1,
                imageId = R.drawable.unit_one
            )
            Spacer(modifier = Modifier.sizeIn(
                minWidth = dimensionResource(id = R.dimen.short5_dp),
                maxWidth = dimensionResource(id = R.dimen.short6_dp)
            ))
            UnitsCover(
                textId = R.string.button_unit_2,
                imageId = R.drawable.unit_two
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.weight(0.8f)
        ){
            UnitsCover(
                textId = R.string.button_unit_3,
                imageId = R.drawable.unit_three
            )
            Spacer(modifier = Modifier.sizeIn(
                minWidth = dimensionResource(id = R.dimen.short5_dp),
                maxWidth = dimensionResource(id = R.dimen.short6_dp)
            ))
            UnitsCover(
                textId = R.string.button_unit_4,
                imageId = R.drawable.unit_four
            )
        }
    }
}

@Composable
private fun UnitsCover(
    modifier: Modifier = Modifier,
    @StringRes textId: Int,
    @DrawableRes imageId: Int
){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
    ){
        Image(
            painter = painterResource(id = imageId),
            modifier = Modifier.fillMaxHeight(0.7f),
            contentScale = ContentScale.Fit,
            contentDescription = null
        )
        Text(
            text = stringResource(id = textId),
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            style = Typography.titleLarge
        )
    }
}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun PreviewMenuView(){
    OrtografiaMariaMelTheme {
        MenuView(
            modifier = Modifier.fillMaxSize(),
            onCoverClicked = { /*TODO*/ },
            onTopic1Clicked = { /*TODO*/ },
            onTopic2Clicked = { /*TODO*/ },
            onTopic3Clicked = { /*TODO*/ },
            onTopic4Clicked = { /*TODO*/ },
            onActivity1Clicked = { /*TODO*/ },
            onActivity2Clicked = { /*TODO*/ },
            onActivity3Clicked = { /*TODO*/ },
            onActivity4Clicked = { /*TODO*/ }
        )
    }
}