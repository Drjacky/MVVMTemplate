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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import app.web.drjackycv.presentation.base.view.ErrorItemView
import app.web.drjackycv.presentation.base.view.LoadingItemView
import app.web.drjackycv.presentation.extension.shimmer
import app.web.drjackycv.presentation.products.choose.ChoosePathType
import app.web.drjackycv.presentation.products.entity.BeerUI
import coil.annotation.ExperimentalCoilApi
import kotlinx.coroutines.flow.flowOf

@ExperimentalCoilApi
@ExperimentalAnimationGraphicsApi
@Composable
fun ProductsListView(
    viewModel: ProductsListViewModel,
    themeFAB: @Composable () -> Unit,
    popBackStack: () -> Unit,
    choosePathType: ChoosePathType,
    onSelectedProduct: (productId: Int) -> Unit
) {
    when (choosePathType) {
        ChoosePathType.RX -> {
            val productsList by viewModel.ldProductsList.observeAsState(PagingData.empty())
            val lazyProductList = flowOf(productsList).collectAsLazyPagingItems()

            ProductsListContent(
                lazyProductList = lazyProductList,
                themeFAB = themeFAB,
                popBackStack = popBackStack,
                onSelectedProduct = onSelectedProduct
            )
        }
        ChoosePathType.COROUTINE -> {
            val lazyProductList = viewModel.productsListByCoroutine.collectAsLazyPagingItems()

            ProductsListContent(
                lazyProductList = lazyProductList,
                themeFAB = themeFAB,
                popBackStack = popBackStack,
                onSelectedProduct = onSelectedProduct
            )
        }
    }

}

@ExperimentalCoilApi
@ExperimentalAnimationGraphicsApi
@Composable
fun ProductsListContent(
    lazyProductList: LazyPagingItems<BeerUI>,
    themeFAB: @Composable () -> Unit,
    popBackStack: () -> Unit,
    onSelectedProduct: (productId: Int) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Products")
                },
                navigationIcon = {
                    IconButton(onClick = { popBackStack() }) {
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

        LazyColumn(
            contentPadding = it,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            state = scrollState,
            modifier = Modifier.fillMaxSize()
        ) {
            items(lazyProductList) { product ->
                product?.let { beer ->
                    ProductRowView(
                        product = beer,
                        onSelectedProduct = onSelectedProduct,
                        modifier = Modifier.shimmer(false)
                    )
                }
            }
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
                            onSelectedProduct = {},
                            modifier = Modifier.shimmer(true)
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
                            ErrorItemView(
                                message = error.error.localizedMessage,
                                onClickRetry = { retry() }
                            )
                        }
                    }
                }
            }
        }
    }
}

@ExperimentalCoilApi
@ExperimentalAnimationGraphicsApi
@Preview
@Composable
fun ProductsListContentPreview() {
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
    val lazyProductList = flowOf(PagingData.from(items)).collectAsLazyPagingItems()

    ProductsListContent(
        lazyProductList = lazyProductList,
        themeFAB = {},
        popBackStack = {},
        onSelectedProduct = { 1 })
}
