package app.web.drjackycv.presentation.products.productlist

import androidx.compose.animation.graphics.ExperimentalAnimationGraphicsApi
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
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
    onSelectedProduct: (productId: Int) -> Unit
) {
    val lazyProductList = viewModel.productsListByCoroutine.collectAsLazyPagingItems()

    ProductsListContent(
        lazyProductList = lazyProductList,
        themeFAB = themeFAB,
        popBackStack = popBackStack,
        onSelectedProduct = onSelectedProduct
    )
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
        LazyColumn(contentPadding = it) {
            items(lazyProductList) { product ->
                product?.let { beer ->
                    ProductRowView(beer, onSelectedProduct)
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
