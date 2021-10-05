package app.web.drjackycv.presentation.products.productlist

import androidx.compose.animation.graphics.ExperimentalAnimationGraphicsApi
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import app.web.drjackycv.presentation.products.entity.BeerUI
import coil.annotation.ExperimentalCoilApi

@ExperimentalCoilApi
@ExperimentalAnimationGraphicsApi
@Composable
fun ProductsListView(
    viewModel: ProductsListViewModel = hiltViewModel(),
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
/*

@ExperimentalCoilApi
@ExperimentalAnimationGraphicsApi
@Preview
@Composable
fun ProductsListContentPreview() {
    ProductsListContent(lazyProductList = ,themeFAB = {}, popBackStack = {}, onSelectedProduct = { 1 })
}*/
