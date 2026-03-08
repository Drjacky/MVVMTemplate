package app.web.drjackycv.feature.products.productdetail

import android.graphics.drawable.BitmapDrawable
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.graphics.ExperimentalAnimationGraphicsApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.palette.graphics.Palette
import app.web.drjackycv.core.ui.R
import app.web.drjackycv.core.ui.theme.ThemeState
import app.web.drjackycv.core.ui.view.ErrorItemView
import app.web.drjackycv.core.ui.view.LoadingItemView
import app.web.drjackycv.feature.products.choose.ChoosePathType
import app.web.drjackycv.feature.products.entity.BeerUI
import app.web.drjackycv.feature.products.entity.LocationUI
import coil.ImageLoader
import coil.annotation.ExperimentalCoilApi
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.request.SuccessResult

@ExperimentalCoilApi
@ExperimentalComposeUiApi
@ExperimentalAnimationGraphicsApi
@Suppress("LongParameterList")
@Composable
fun ProductRoute(
    productId: String,
    choose: ChoosePathType,
    themeFAB: @Composable () -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ProductViewModel = hiltViewModel(),
) {
    when (choose) {
        ChoosePathType.RX -> {
            LaunchedEffect(productId) {
                viewModel.getProduct(productId)
            }
            val uiState by viewModel.productByRx.collectAsStateWithLifecycle()
            ProductDetailView(
                uiState = uiState,
                themeFAB = themeFAB,
                onBackClick = onBackClick,
                modifier = modifier,
            )
        }

        ChoosePathType.COROUTINE -> {
            LaunchedEffect(productId) {
                viewModel.setProductId(productId)
            }
            val uiState by viewModel.productByCoroutine.collectAsStateWithLifecycle()
            ProductDetailView(
                uiState = uiState,
                themeFAB = themeFAB,
                onBackClick = onBackClick,
                modifier = modifier,
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@ExperimentalCoilApi
@ExperimentalComposeUiApi
@ExperimentalAnimationGraphicsApi
@Suppress("LongMethod", "CyclomaticComplexMethod")
@Composable
fun ProductDetailView(
    uiState: ProductUiState,
    themeFAB: @Composable () -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    when (uiState) {
        is ProductUiState.Success -> {
            val item = uiState.item ?: return
            val defaultColor = MaterialTheme.colorScheme.surface
            val cardColor = remember { mutableStateOf(defaultColor) }
            val animatedCardColor =
                animateColorAsState(cardColor.value, label = "animatedCardColor")
            val isDark = ThemeState.darkModeState.value
            val context = LocalContext.current

            Scaffold(
                floatingActionButton = themeFAB,
                modifier = modifier,
            ) { padding ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding)
                        .verticalScroll(rememberScrollState())
                ) {
                    val shape = RoundedCornerShape(
                        topStart = 0.dp,
                        topEnd = 0.dp,
                        bottomStart = 64.dp,
                        bottomEnd = 64.dp,
                    )

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(290.dp)
                            .shadow(elevation = 9.dp, shape = shape)
                            .background(color = animatedCardColor.value, shape = shape)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp)
                                .statusBarsPadding(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            IconButton(onClick = onBackClick) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                    contentDescription = stringResource(R.string.content_description_back),
                                    tint = MaterialTheme.colorScheme.onSurface
                                )
                            }
                            Text(
                                text = "#${item.id}",
                                color = MaterialTheme.colorScheme.onSurface,
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp,
                            )
                        }

                        val imagePainter = rememberAsyncImagePainter(
                            ImageRequest.Builder(context)
                                .data(data = item.image)
                                .crossfade(true)
                                .allowHardware(false)
                                .build()
                        )

                        Image(
                            modifier = Modifier
                                .align(Alignment.BottomCenter)
                                .padding(bottom = 20.dp)
                                .size(190.dp),
                            painter = imagePainter,
                            contentDescription = item.name,
                            contentScale = ContentScale.Inside
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
                                val loadingDesc =
                                    stringResource(R.string.content_description_loading_image)
                                CircularProgressIndicator(
                                    modifier = Modifier
                                        .align(Alignment.Center)
                                        .semantics { contentDescription = loadingDesc },
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                            }

                            else -> Unit
                        }
                    }

                    Text(
                        modifier = Modifier
                            .padding(top = 24.dp)
                            .fillMaxWidth(),
                        text = item.name,
                        color = MaterialTheme.colorScheme.onBackground,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        fontSize = 36.sp,
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 14.dp),
                        horizontalArrangement = Arrangement.spacedBy(
                            16.dp,
                            Alignment.CenterHorizontally
                        ),
                    ) {
                        val statusColor = when (item.status.lowercase()) {
                            "alive" -> Color(0xFF4CAF50)
                            "dead" -> Color(0xFFF44336)
                            else -> Color(0xFF9E9E9E)
                        }
                        Text(
                            modifier = Modifier
                                .background(
                                    color = statusColor,
                                    shape = RoundedCornerShape(64.dp),
                                )
                                .padding(horizontal = 24.dp, vertical = 4.dp),
                            text = item.status,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            color = Color.White,
                            maxLines = 1,
                            fontSize = 16.sp,
                        )

                        Text(
                            modifier = Modifier
                                .background(
                                    color = MaterialTheme.colorScheme.secondary,
                                    shape = RoundedCornerShape(64.dp),
                                )
                                .padding(horizontal = 24.dp, vertical = 4.dp),
                            text = item.species,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            color = MaterialTheme.colorScheme.onSecondary,
                            maxLines = 1,
                            fontSize = 16.sp,
                        )
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 24.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                    ) {
                        InfoItem(title = item.gender, content = "Gender")
                        InfoItem(title = item.type.ifEmpty { "Unknown" }, content = "Type")
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                    ) {
                        InfoItem(title = item.origin.name, content = "Origin")
                        InfoItem(title = item.location.name, content = "Location")
                    }

                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 24.dp, bottom = 16.dp),
                        text = "${item.episode.size} Episodes",
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.onBackground,
                        fontWeight = FontWeight.Bold,
                        fontSize = 21.sp,
                    )
                }
            }
        }

        is ProductUiState.Error -> {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = {},
                        navigationIcon = {
                            IconButton(onClick = onBackClick) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                    contentDescription = stringResource(R.string.content_description_back),
                                )
                            }
                        },
                    )
                },
                floatingActionButton = themeFAB,
            ) { padding ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding)
                ) {
                    ErrorItemView(
                        modifier = Modifier.align(Alignment.Center),
                        message = uiState.error.localizedMessage,
                    )
                }
            }
        }

        is ProductUiState.Loading -> {
            Box(modifier = Modifier.fillMaxSize()) {
                LoadingItemView(modifier = Modifier.align(Alignment.Center))
            }
        }
    }
}

@Composable
private fun InfoItem(
    title: String,
    content: String,
) {
    Column(
        modifier = Modifier.padding(horizontal = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(bottom = 4.dp),
            text = title,
            color = MaterialTheme.colorScheme.onBackground,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            textAlign = TextAlign.Center,
        )

        Text(
            text = content,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp,
        )
    }
}

@ExperimentalCoilApi
@ExperimentalComposeUiApi
@ExperimentalAnimationGraphicsApi
@Preview(showBackground = true)
@Composable
private fun ProductDetailViewPreview() {
    val uiState = ProductUiState.Success(
        item = BeerUI(
            id = 361,
            name = "Toxic Rick",
            status = "Dead",
            species = "Humanoid",
            type = "Rick's Toxic Side",
            gender = "Male",
            origin = LocationUI(
                name = "Alien Spa",
                url = "https://rickandmortyapi.com/api/location/64"
            ),
            location = LocationUI(
                name = "Earth",
                url = "https://rickandmortyapi.com/api/location/20"
            ),
            image = "https://rickandmortyapi.com/api/character/avatar/361.jpeg",
            episode = listOf("https://rickandmortyapi.com/api/episode/27"),
            url = "https://rickandmortyapi.com/api/character/361",
            created = "2018-01-10T18:20:41.703Z",
        )
    )
    ProductDetailView(
        uiState = uiState,
        themeFAB = {},
        onBackClick = {},
    )
}
