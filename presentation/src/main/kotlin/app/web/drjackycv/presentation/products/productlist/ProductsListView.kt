package app.web.drjackycv.presentation.products.productlist

//import androidx.paging.compose.items
import androidx.compose.animation.graphics.ExperimentalAnimationGraphicsApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import app.web.drjackycv.presentation.base.view.ErrorListView
import app.web.drjackycv.presentation.base.view.LoadingItemView
import app.web.drjackycv.presentation.products.choose.ChoosePathType
import app.web.drjackycv.presentation.products.entity.BeerUI
import coil.annotation.ExperimentalCoilApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf

@ExperimentalCoroutinesApi

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


@ExperimentalCoilApi
@ExperimentalComposeUiApi
@ExperimentalAnimationGraphicsApi
@Composable
fun ProductsListContent(
    uiState: ProductsUiState,
    themeFAB: @Composable () -> Unit,
    navigateToProduct: (String) -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
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
        floatingActionButton = themeFAB,
        modifier = modifier
    ) {
        val scrollState = rememberLazyListState()
        when (uiState) {
            is ProductsUiState.Success -> {
                val lazyProductList = flowOf(uiState.items).collectAsLazyPagingItems()
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = it,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(2.dp),
                    state = scrollState
                ) {
                    items(
                        count = lazyProductList.itemCount,
                        key = lazyProductList.itemKey { product -> product.id },
                        contentType = lazyProductList.itemContentType { "MyPagingItems" }
                    ) { index ->
                        val product = lazyProductList[index]
                        product?.let { beer ->
                            ProductRowView(
                                product = beer,
                                isShimmerVisible = false,
                                navigateToProduct = navigateToProduct
                            )
                        }
                    }
                    lazyProductList.apply {
                        if (loadState.refresh == LoadState.Loading) {
                            val beerUI = BeerUI(
                                id = -1,
                                name = "",
                                status = "",
                                species = "",
                                gender = "",
                                image = "",
                                url = "",
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


@ExperimentalCoilApi
@ExperimentalComposeUiApi
@ExperimentalAnimationGraphicsApi
@Preview
@Composable
private fun ProductsListContentPreview() {
    val items = listOf(
        BeerUI(
            id = 361,
            name = "Toxic Rick",
            status = "Dead",
            species = "Humanoid",
            gender = "Male",
            image = "https://rickandmortyapi.com/api/character/avatar/361.jpeg",
            url = "https://rickandmortyapi.com/api/character/361",
        ),
        BeerUI(
            id = 362,
            name = "Traflorkian",
            status = "Alive",
            species = "Alien",
            gender = "unknown",
            image = "https://rickandmortyapi.com/api/character/avatar/362.jpeg",
            url = "https://rickandmortyapi.com/api/character/362",
        )
    )
    val uiState = ProductsUiState.Success(items = PagingData.from(items))

    ProductsListContent(
        uiState = uiState,
        themeFAB = {},
        navigateToProduct = {},
        onBackClick = {})
}
