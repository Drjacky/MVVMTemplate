package app.web.drjackycv.presentation.products.productlist

import androidx.compose.animation.graphics.ExperimentalAnimationGraphicsApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import app.web.drjackycv.presentation.extension.fade
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
            //TODO
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
        LazyColumn(contentPadding = it) {
            if (lazyProductList.loadState.refresh == LoadState.Loading) {
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
                        modifier = Modifier.fade(true)
                    )
                }
            }

            items(lazyProductList) { product ->
                product?.let { beer ->
                    ProductRowView(
                        product = beer,
                        onSelectedProduct = onSelectedProduct,
                        modifier = Modifier.fade(false)
                    )
                }
            }

            if (lazyProductList.loadState.append == LoadState.Loading) {
                item {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentWidth(Alignment.CenterHorizontally)
                    )
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
