package app.web.drjackycv.presentation.products.productlist

import android.graphics.drawable.BitmapDrawable
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.graphics.ExperimentalAnimationGraphicsApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.palette.graphics.Palette
import app.web.drjackycv.presentation.base.view.ErrorRowView
import app.web.drjackycv.presentation.extension.clickableOnce
import app.web.drjackycv.presentation.extension.shimmer
import app.web.drjackycv.presentation.main.MainActivity
import app.web.drjackycv.presentation.products.entity.BeerUI
import coil.ImageLoader
import coil.annotation.ExperimentalCoilApi
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.request.SuccessResult


@ExperimentalAnimationGraphicsApi
@ExperimentalCoilApi
@ExperimentalComposeUiApi
@Composable
fun ProductRowView(
    product: BeerUI,
    isShimmerVisible: Boolean,
    navigateToProduct: (product: String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val defaultColor = MaterialTheme.colors.surface
    val cardColor = remember { mutableStateOf(defaultColor) }
    val animatedCardColor = animateColorAsState(cardColor.value, label = "animatedCardColor")
    val isDark = remember { mutableStateOf(MainActivity.ThemeState.darkModeState.value) }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickableOnce(onClick = { navigateToProduct(product.id.toString()) })
            .padding(4.dp),
        shape = RoundedCornerShape(4.dp),
        backgroundColor = animatedCardColor.value,
        elevation = 2.dp
    ) {
        Row {
            Surface(
                modifier = Modifier
                    .size(72.dp)
                    .padding(4.dp)
                    .shimmer(isShimmerVisible),
                shape = CircleShape,
                color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f)
            ) {
                val imagePainter = rememberAsyncImagePainter(
                    ImageRequest.Builder(LocalContext.current)
                        .data(data = product.image)
                        .crossfade(true)
                        .allowHardware(false)
                        .build()
                )

                Image(
                    modifier = Modifier.size(72.dp),
                    painter = imagePainter,
                    contentDescription = product.name,
                )

                when (imagePainter.state) {
                    is AsyncImagePainter.State.Success -> {
                        val newCardColor = changeCardColor(
                            imagePainter = imagePainter,
                            cardColor = cardColor.value,
                            isDark = isDark.value
                        )
                        cardColor.value = newCardColor
                    }
                    is AsyncImagePainter.State.Loading -> {
                        CircularProgressIndicator(modifier = Modifier.padding(8.dp))
                    }
                    is AsyncImagePainter.State.Error -> {
                        ErrorRowView()
                    }
                    AsyncImagePainter.State.Empty -> {
                    }
                }
            }

            Column(modifier = Modifier.padding(4.dp)) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .shimmer(isShimmerVisible),
                    text = product.name,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.h6,
                    fontWeight = FontWeight.Normal
                )
                CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                    Text(
                        modifier = Modifier
                            .padding(top = 4.dp)
                            .shimmer(isShimmerVisible),
                        text = product.id.toString(),
                        style = MaterialTheme.typography.body2
                    )
                }
            }
        }
    }
}

@Composable
private fun changeCardColor(
    imagePainter: AsyncImagePainter,
    cardColor: Color,
    isDark: Boolean
): Color {
    val context = LocalContext.current
    val imageLoader = ImageLoader(context)
    var newCardColor = cardColor

    LaunchedEffect(key1 = imagePainter) {
        val result = imageLoader.execute(imagePainter.request)

        if (result is SuccessResult) {
            val bitmap = (result.drawable as BitmapDrawable).bitmap

            if (isDark) {
                val vibrant = Palette.from(bitmap)
                    .generate()
                    //.vibrantSwatch
                    .darkVibrantSwatch

                vibrant?.let {
                    newCardColor = Color(it.rgb)
                }
            } else {
                val vibrant = Palette.from(bitmap)
                    .generate()
                    //.vibrantSwatch
                    .lightVibrantSwatch

                vibrant?.let {
                    newCardColor = Color(it.rgb)
                }
            }
        }
    }

    return newCardColor
}


@ExperimentalAnimationGraphicsApi
@ExperimentalCoilApi
@ExperimentalComposeUiApi
@Preview
@Composable
private fun ProductRowViewPreview() {
    ProductRowView(
        product = BeerUI(
            id = 361,
            name = "Toxic Rick",
            status = "Dead",
            species = "Humanoid",
            gender = "Male",
            image = "https://rickandmortyapi.com/api/character/avatar/361.jpeg",
            url = "https://rickandmortyapi.com/api/character/361",
        ),
        isShimmerVisible = false,
        navigateToProduct = {}
    )
}
