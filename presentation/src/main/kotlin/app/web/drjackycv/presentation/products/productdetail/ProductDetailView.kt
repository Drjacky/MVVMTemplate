package app.web.drjackycv.presentation.products.productdetail

import androidx.compose.animation.graphics.ExperimentalAnimationGraphicsApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import app.web.drjackycv.presentation.products.entity.BeerUI
import coil.annotation.ExperimentalCoilApi

@ExperimentalLifecycleComposeApi
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
    uiState: ProductUIState,
    themeFAB: @Composable () -> Unit,
    onBackClick: () -> Unit
) {

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
        floatingActionButton = themeFAB
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            Text(
                modifier = Modifier.padding(16.dp),
                text = "${uiState.item?.description}",
                style = TextStyle(fontSize = 17.sp),
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
        }
    }

}

@ExperimentalCoilApi
@ExperimentalComposeUiApi
@ExperimentalAnimationGraphicsApi
@Preview(showBackground = true)
@Composable
private fun ProductDetailViewPreview() {
    val uiState = ProductUIState(
        item = BeerUI(
            id = 1,
            name = "name",
            tagline = "tagline",
            description = "description",
            imageUrl = "https://images.punkapi.com/v2/5.png",
            abv = 4.9
        )
    )
    ProductDetailView(
        uiState = uiState,
        themeFAB = {},
        onBackClick = {},
    )
}