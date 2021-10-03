package app.web.drjackycv.presentation.products.productlist

import androidx.compose.animation.graphics.ExperimentalAnimationGraphicsApi
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
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
    selectedProduct: (product: BeerUI) -> Unit,
) {
    val lazyProductList = viewModel.productsListByCoroutine.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Products")
                }
            )
        },
        floatingActionButton = themeFAB
    ) {
        LazyColumn(contentPadding = it) {
            items(lazyProductList) { product ->
                product?.let {
                    ProductRowView(product, selectedProduct)
                }
            }
        }
    }

}