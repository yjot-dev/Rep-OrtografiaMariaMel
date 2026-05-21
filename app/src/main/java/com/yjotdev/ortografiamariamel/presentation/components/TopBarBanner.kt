package com.yjotdev.ortografiamariamel.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import com.yjotdev.ortografiamariamel.R

@Composable
fun TopBarBanner(
    modifier: Modifier = Modifier,
    title: String
){
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ){
        Icon(
            painter = painterResource(id = R.drawable.app_student),
            tint = Color.Unspecified,
            contentDescription = null,
            modifier = Modifier.size(dimensionResource(R.dimen.short6_dp))
        )
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )
        Icon(
            painter = painterResource(id = R.drawable.app_name),
            tint = Color.Unspecified,
            contentDescription = null,
            modifier = Modifier.size(dimensionResource(R.dimen.medium1_dp))
        )
    }
}