package app.web.drjackycv.presentation.products.productlist

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import app.web.drjackycv.presentation.products.entity.BeerUI

@Composable
fun ProductsListView(
    viewModel: ProductsListViewModel,
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