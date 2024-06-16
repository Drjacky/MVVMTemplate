package app.web.drjackycv.presentation.products.productdetail

import androidx.compose.animation.graphics.ExperimentalAnimationGraphicsApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import app.web.drjackycv.presentation.base.view.ErrorItemView
import app.web.drjackycv.presentation.base.view.LoadingItemView
import app.web.drjackycv.presentation.products.entity.BeerUI
import coil.annotation.ExperimentalCoilApi


@ExperimentalCoilApi
@ExperimentalComposeUiApi
@ExperimentalAnimationGraphicsApi
@Composable
fun ProductRoute(
    productId: String,
    themeFAB: @Composable () -> Unit,
    onBackClick: () -> Unit,
    viewModel: ProductViewModel = hiltViewModel()
) {
//    val uiState by viewModel.getProductByCoroutine(productId).collectAsStateWithLifecycle()
    val uiState by viewModel.productByCoroutine.collectAsStateWithLifecycle()

    ProductDetailView(uiState, themeFAB, onBackClick)
}

@ExperimentalCoilApi
@ExperimentalComposeUiApi
@ExperimentalAnimationGraphicsApi
@Composable
fun ProductDetailView(
    uiState: ProductUiState,
    themeFAB: @Composable () -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    when (uiState) {
        is ProductUiState.Success -> {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = {
                            Text(text = "${uiState.item?.name}")
                        },
                        navigationIcon = {
                            IconButton(onClick = onBackClick) {
                                Icon(
                                    imageVector = Icons.Filled.ArrowBack,
                                    contentDescription = "Back"
                                )
                            }
                        }
                    )
                },
                floatingActionButton = themeFAB,
                modifier = modifier
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(it)
                ) {
                    Text(
                        modifier = Modifier.padding(16.dp),
                        text = "${uiState.item?.species}",
                        style = TextStyle(fontSize = 17.sp),
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1
                    )
                }
            }
        }
        is ProductUiState.Error -> {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = {
                            Text(text = "")
                        },
                        navigationIcon = {
                            IconButton(onClick = onBackClick) {
                                Icon(
                                    imageVector = Icons.Filled.ArrowBack,
                                    contentDescription = "Back"
                                )
                            }
                        }
                    )
                },
                floatingActionButton = themeFAB
            ) { padding ->
                Box(modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)) {
                    ErrorItemView(
                        modifier = Modifier.align(Alignment.Center),
                        message = uiState.error.localizedMessage
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
            gender = "Male",
            image = "https://rickandmortyapi.com/api/character/avatar/361.jpeg",
            url = "https://rickandmortyapi.com/api/character/361",
        )
    )
    ProductDetailView(
        uiState = uiState,
        themeFAB = {},
        onBackClick = {},
    )
}