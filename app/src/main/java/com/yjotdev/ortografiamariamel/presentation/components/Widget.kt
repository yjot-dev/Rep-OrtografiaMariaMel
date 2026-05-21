package com.yjotdev.ortografiamariamel.presentation.components

import android.os.Build.VERSION.SDK_INT
import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import coil.size.Size
import com.yjotdev.ortografiamariamel.R
import com.yjotdev.ortografiamariamel.presentation.theme.Typography

@Composable
fun RecyclerButton(
    modifier: Modifier = Modifier,
    textButton: String,
    colorButton: Color = MaterialTheme.colorScheme.primaryContainer,
    colorBorder: Color = MaterialTheme.colorScheme.tertiary,
    colorText: Color = MaterialTheme.colorScheme.onPrimary,
    widthBorder: Dp = dimensionResource(id = R.dimen.short2_dp),
    shape: Shape = RoundedCornerShape(dimensionResource(id = R.dimen.short5_dp)),
    elevation: ButtonElevation? = null,
    styleText: TextStyle = Typography.titleLarge,
    isEnabled: Boolean = true,
    onClick: () -> Unit
){
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = colorButton,
            disabledContainerColor = MaterialTheme
                .colorScheme.onTertiaryContainer.copy(0.8f)
        ),
        border = BorderStroke(
            width = widthBorder,
            color = colorBorder
        ),
        shape = shape,
        elevation = elevation,
        enabled = isEnabled,
        modifier = modifier
    ){
        Text(
            text = textButton,
            color = colorText,
            style = styleText
        )
    }
}

@Composable
fun GifImage(
    modifier: Modifier = Modifier,
    @DrawableRes idImage: Int
){
    val context = LocalContext.current
    val imageLoader = ImageLoader.Builder(context)
        .components {
            if(SDK_INT >= 28) add(ImageDecoderDecoder.Factory())
            else add(GifDecoder.Factory())}
        .build()
    Image(
        modifier = modifier,
        painter = rememberAsyncImagePainter(
            model = ImageRequest.Builder(context).data(idImage)
                .apply{ size(Size.ORIGINAL) }.build(),
            imageLoader = imageLoader),
        contentScale = ContentScale.Fit,
        contentDescription = null
    )
}

@Composable
fun BackGroundImage(
    modifier: Modifier = Modifier,
    alpha: Float = 0.5f
){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = modifier
    ){
        Image(
            painter = painterResource(id = R.drawable.app_student),
            alpha = alpha,
            contentDescription = null,
            contentScale = ContentScale.Fit
        )
        Text(
            text = stringResource(id = R.string.start_title),
            color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = alpha),
            style = Typography.titleLarge
        )
        Image(
            painter = painterResource(id = R.drawable.app_name),
            alpha = alpha,
            contentDescription = null,
            contentScale = ContentScale.Fit
        )
    }
}

@Composable
fun CoverView(
    modifier: Modifier = Modifier,
    alpha: Float = 1f,
    @DrawableRes idImage: Int
){
    Image(
        modifier = modifier,
        painter = painterResource(id = idImage),
        contentScale = ContentScale.FillBounds,
        alpha = alpha,
        contentDescription = null)
}