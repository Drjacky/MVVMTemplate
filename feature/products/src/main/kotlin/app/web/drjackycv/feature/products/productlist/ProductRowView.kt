package app.web.drjackycv.feature.products.productlist

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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import app.web.drjackycv.core.ui.extension.clickableOnce
import app.web.drjackycv.core.ui.extension.shimmer
import app.web.drjackycv.core.ui.theme.ThemeState
import app.web.drjackycv.core.ui.view.ErrorRowView
import app.web.drjackycv.feature.products.entity.BeerUI
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
    val defaultColor = MaterialTheme.colorScheme.surface
    val cardColor = remember { mutableStateOf(defaultColor) }
    val animatedCardColor = animateColorAsState(cardColor.value, label = "animatedCardColor")
    val isDark = remember { mutableStateOf(ThemeState.darkModeState.value) }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickableOnce(onClick = { navigateToProduct(product.id.toString()) })
            .padding(4.dp),
        shape = RoundedCornerShape(4.dp),
        colors = CardDefaults.cardColors(containerColor = animatedCardColor.value),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
    ) {
        Row {
            Surface(
                modifier = Modifier
                    .size(72.dp)
                    .padding(4.dp)
                    .shimmer(isShimmerVisible),
                shape = CircleShape,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f),
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
                            isDark = isDark.value,
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
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Normal,
                )
                Text(
                    modifier = Modifier
                        .padding(top = 4.dp)
                        .shimmer(isShimmerVisible),
                    text = product.id.toString(),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
        }
    }
}

@Composable
private fun changeCardColor(
    imagePainter: AsyncImagePainter,
    cardColor: Color,
    isDark: Boolean,
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
                    .darkVibrantSwatch

                vibrant?.let {
                    newCardColor = Color(it.rgb)
                }
            } else {
                val vibrant = Palette.from(bitmap)
                    .generate()
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
