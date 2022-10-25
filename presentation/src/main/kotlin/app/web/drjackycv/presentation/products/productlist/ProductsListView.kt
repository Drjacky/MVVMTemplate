package app.web.drjackycv.presentation.products.productlist

import androidx.compose.animation.graphics.ExperimentalAnimationGraphicsApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import app.web.drjackycv.presentation.base.view.ErrorListView
import app.web.drjackycv.presentation.base.view.LoadingItemView
import app.web.drjackycv.presentation.products.choose.ChoosePathType
import app.web.drjackycv.presentation.products.entity.BeerUI
import coil.annotation.ExperimentalCoilApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf

@ExperimentalCoroutinesApi
@ExperimentalLifecycleComposeApi
@ExperimentalCoilApi
@ExperimentalComposeUiApi
@ExperimentalAnimationGraphicsApi
@Composable
fun ProductsListRoute(
    choose: ChoosePathType,
    navigateToProduct: (String) -> Unit,
    themeFAB: @Composable () -> Unit,
    onBackClick: () -> Unit,
    viewModel: ProductsListViewModel = hiltViewModel()
) {
    when (choose) {
        ChoosePathType.RX -> {
            LaunchedEffect(Unit) {
                viewModel.getProductsByRxPath()
            }
            val uiState = viewModel.ldProductsList.observeAsState(ProductsUiState.Loading)
            ProductsListView(
                uiState = uiState.value,
                themeFAB = themeFAB,
                navigateToProduct = navigateToProduct,
                onBackClick = onBackClick
            )
        }
        ChoosePathType.COROUTINE -> {
            val uiState by viewModel.productsListByCoroutine.collectAsStateWithLifecycle()
            ProductsListView(
                uiState = uiState,
                themeFAB = themeFAB,
                navigateToProduct = navigateToProduct,
                onBackClick = onBackClick
            )
        }
    }

}

@ExperimentalLifecycleComposeApi
@ExperimentalCoilApi
@ExperimentalComposeUiApi
@ExperimentalAnimationGraphicsApi
@Composable
fun ProductsListView(
    uiState: ProductsUiState,
    themeFAB: @Composable () -> Unit,
    navigateToProduct: (String) -> Unit,
    onBackClick: () -> Unit,
) {
    ProductsListContent(
        uiState = uiState,
        themeFAB = themeFAB,
        navigateToProduct = navigateToProduct,
        onBackClick = onBackClick,
    )
}

@ExperimentalLifecycleComposeApi
@ExperimentalCoilApi
@ExperimentalComposeUiApi
@ExperimentalAnimationGraphicsApi
@Composable
fun ProductsListContent(
    uiState: ProductsUiState,
    themeFAB: @Composable () -> Unit,
    navigateToProduct: (String) -> Unit,
    onBackClick: () -> Unit,
) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Products")
                },
                navigationIcon = {
                    IconButton(onClick = { onBackClick() }) {
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
        val scrollState = rememberLazyListState()
        when (uiState) {
            is ProductsUiState.Success -> {
                val lazyProductList = flowOf(uiState.items).collectAsLazyPagingItems()
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = it,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    state = scrollState
                ) {
                    items(
                        items = lazyProductList,
                        key = { product ->
                            product.id
                        },
                        itemContent = { product ->
                            product?.let { beer ->
                                ProductRowView(
                                    product = beer,
                                    isShimmerVisible = false,
                                    navigateToProduct = navigateToProduct
                                )
                            }
                        }
                    )
                    lazyProductList.apply {
                        if (loadState.refresh == LoadState.Loading) {
                            val beerUI = BeerUI(
                                id = -1,
                                name = "",
                                tagline = "",
                                description = "",
                                imageUrl = "",
                                abv = 0.0
                            )
                            items(10) {
                                ProductRowView(
                                    product = beerUI,
                                    isShimmerVisible = true,
                                    navigateToProduct = {}
                                )
                            }
                        }

                        if (loadState.append == LoadState.Loading) {
                            item {
                                LoadingItemView()
                            }
                        } else {
                            val error = when {
                                loadState.prepend is LoadState.Error ->
                                    loadState.prepend as LoadState.Error
                                loadState.source.prepend is LoadState.Error ->
                                    loadState.prepend as LoadState.Error
                                loadState.append is LoadState.Error ->
                                    loadState.append as LoadState.Error
                                loadState.source.append is LoadState.Error ->
                                    loadState.append as LoadState.Error
                                loadState.refresh is LoadState.Error ->
                                    loadState.refresh as LoadState.Error
                                else -> null
                            }
                            error?.run {
                                item {
                                    ErrorListView(
                                        message = error.error.localizedMessage,
                                        onClickRetry = { retry() }
                                    )
                                }
                            }
                        }
                    }
                }
            }
            else -> {
                //no-op
            }
        }
    }
}

@ExperimentalLifecycleComposeApi
@ExperimentalCoilApi
@ExperimentalComposeUiApi
@ExperimentalAnimationGraphicsApi
@Preview
@Composable
private fun ProductsListContentPreview() {
    val items = listOf(
        BeerUI(
            id = 1,
            name = "name",
            tagline = "tagline",
            description = "description",
            imageUrl = "https://images.punkapi.com/v2/5.png",
            abv = 4.9
        )
    )
    val uiState = ProductsUiState.Success(items = PagingData.from(items))

    ProductsListContent(
        uiState = uiState,
        themeFAB = {},
        navigateToProduct = {},
        onBackClick = {})
}
