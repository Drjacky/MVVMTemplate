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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.palette.graphics.Palette
import app.web.drjackycv.core.ui.R
import app.web.drjackycv.core.ui.extension.clickableOnce
import app.web.drjackycv.core.ui.extension.shimmer
import app.web.drjackycv.core.ui.theme.ThemeState
import app.web.drjackycv.core.ui.view.ErrorRowView
import app.web.drjackycv.feature.products.entity.ProductUI
import coil.ImageLoader
import coil.annotation.ExperimentalCoilApi
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.request.SuccessResult

@ExperimentalAnimationGraphicsApi
@ExperimentalCoilApi
@ExperimentalComposeUiApi
@Suppress("LongMethod")
@Composable
fun ProductRowView(
    product: ProductUI,
    isShimmerVisible: Boolean,
    navigateToProduct: (product: String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val defaultColor = MaterialTheme.colorScheme.surface
    val cardColor = remember { mutableStateOf(defaultColor) }
    val animatedCardColor = animateColorAsState(cardColor.value, label = "animatedCardColor")
    val isDark = ThemeState.darkModeState.value
    val context = LocalContext.current

    Card(
        modifier = modifier
            .fillMaxWidth()
            .semantics(mergeDescendants = true) {}
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
                    ImageRequest.Builder(context)
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
                        LaunchedEffect(imagePainter.state) {
                            val imageLoader = ImageLoader(context)
                            val result = imageLoader.execute(imagePainter.request)
                            if (result is SuccessResult) {
                                val bitmap = (result.drawable as BitmapDrawable).bitmap
                                val swatch = if (isDark) {
                                    Palette.from(bitmap).generate().darkVibrantSwatch
                                } else {
                                    Palette.from(bitmap).generate().lightVibrantSwatch
                                }
                                swatch?.let { cardColor.value = Color(it.rgb) }
                            }
                        }
                    }

                    is AsyncImagePainter.State.Loading -> {
                        val loadingDesc = stringResource(R.string.content_description_loading_image)
                        CircularProgressIndicator(
                            modifier = Modifier
                                .padding(8.dp)
                                .semantics { contentDescription = loadingDesc },
                        )
                    }

                    is AsyncImagePainter.State.Error -> {
                        ErrorRowView()
                    }

                    AsyncImagePainter.State.Empty -> Unit
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

@ExperimentalAnimationGraphicsApi
@ExperimentalCoilApi
@ExperimentalComposeUiApi
@Preview
@Composable
private fun ProductRowViewPreview() {
    ProductRowView(
        product = ProductUI(
            id = 361,
            name = "Toxic Rick",
            status = "Dead",
            species = "Humanoid",
            type = "Rick's Toxic Side",
            gender = "Male",
            origin = app.web.drjackycv.feature.products.entity.LocationUI(name = "Alien Spa", url = ""),
            location = app.web.drjackycv.feature.products.entity.LocationUI(name = "Earth", url = ""),
            image = "https://rickandmortyapi.com/api/character/avatar/361.jpeg",
            episode = emptyList(),
            url = "https://rickandmortyapi.com/api/character/361",
            created = "2018-01-10T18:20:41.703Z",
        ),
        isShimmerVisible = false,
        navigateToProduct = {}
    )
}
