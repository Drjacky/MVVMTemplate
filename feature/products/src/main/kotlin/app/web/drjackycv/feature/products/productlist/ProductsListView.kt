package app.web.drjackycv.feature.products.productlist

import androidx.compose.animation.graphics.ExperimentalAnimationGraphicsApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import app.web.drjackycv.core.ui.extension.frostedGlass
import app.web.drjackycv.core.ui.view.ErrorListView
import app.web.drjackycv.core.ui.view.LoadingItemView
import app.web.drjackycv.feature.products.choose.ChoosePathType
import app.web.drjackycv.feature.products.entity.ProductUI
import coil.annotation.ExperimentalCoilApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import app.web.drjackycv.core.ui.R as CoreUiR

@ExperimentalCoroutinesApi
@ExperimentalCoilApi
@ExperimentalComposeUiApi
@ExperimentalAnimationGraphicsApi
@Suppress("LongParameterList")
@Composable
fun ProductsListRoute(
    choose: ChoosePathType,
    navigateToProduct: (String) -> Unit,
    themeFAB: @Composable () -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ProductsListViewModel = hiltViewModel(),
) {
    when (choose) {
        ChoosePathType.RX -> {
            LaunchedEffect(Unit) {
                viewModel.getProductsByRxPath()
            }
            val uiState by viewModel.productsListByRx.collectAsStateWithLifecycle()
            ProductsListContent(
                uiState = uiState,
                themeFAB = themeFAB,
                navigateToProduct = navigateToProduct,
                onBackClick = onBackClick,
                modifier = modifier,
            )
        }

        ChoosePathType.COROUTINE -> {
            val uiState by viewModel.productsListByCoroutine.collectAsStateWithLifecycle()
            ProductsListContent(
                uiState = uiState,
                themeFAB = themeFAB,
                navigateToProduct = navigateToProduct,
                onBackClick = onBackClick,
                modifier = modifier,
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@ExperimentalCoilApi
@ExperimentalComposeUiApi
@ExperimentalAnimationGraphicsApi
@Suppress("LongMethod", "CyclomaticComplexMethod")
@Composable
fun ProductsListContent(
    uiState: ProductsUiState,
    themeFAB: @Composable () -> Unit,
    navigateToProduct: (String) -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "",
                        modifier = Modifier.semantics { heading() },
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { onBackClick() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(CoreUiR.string.content_description_back),
                        )
                    }
                },
                modifier = Modifier.frostedGlass(),
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent,
                ),
            )
        },
        floatingActionButton = themeFAB,
        modifier = modifier,
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
                    state = scrollState,
                ) {
                    items(
                        count = lazyProductList.itemCount,
                        key = lazyProductList.itemKey { product -> product.id },
                        contentType = lazyProductList.itemContentType { "MyPagingItems" },
                    ) { index ->
                        val product = lazyProductList[index]
                        product?.let { item ->
                            ProductRowView(
                                product = item,
                                isShimmerVisible = false,
                                navigateToProduct = navigateToProduct,
                            )
                        }
                    }
                    lazyProductList.apply {
                        if (loadState.refresh == LoadState.Loading) {
                            val productUI = ProductUI(
                                id = -1,
                                name = "",
                                status = "",
                                species = "",
                                type = "",
                                gender = "",
                                origin = app.web.drjackycv.feature.products.entity.LocationUI(name = "", url = ""),
                                location = app.web.drjackycv.feature.products.entity.LocationUI(name = "", url = ""),
                                image = "",
                                episode = emptyList(),
                                url = "",
                                created = "",
                            )
                            items(10) {
                                ProductRowView(
                                    product = productUI,
                                    isShimmerVisible = true,
                                    navigateToProduct = {},
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
                                        onClickRetry = { retry() },
                                    )
                                }
                            }
                        }
                    }
                }
            }

            is ProductsUiState.Loading -> {}
            is ProductsUiState.Error -> {}
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
        ProductUI(
            id = 361,
            name = "Toxic Rick",
            status = "Dead",
            species = "Humanoid",
            type = "Rick's Toxic Side",
            gender = "Male",
            origin = app.web.drjackycv.feature.products.entity.LocationUI(name = "Alien Spa", url = ""),
            location = app.web.drjackycv.feature.products.entity.LocationUI(name = "Earth", url = ""),
            image = "https://rickandmortyapi.com/api/character/avatar/361.jpeg",
            episode = emptyList(),
            url = "https://rickandmortyapi.com/api/character/361",
            created = "2018-01-10T18:20:41.703Z",
        ),
        ProductUI(
            id = 362,
            name = "Traflorkian",
            status = "Alive",
            species = "Alien",
            type = "",
            gender = "unknown",
            origin = app.web.drjackycv.feature.products.entity.LocationUI(name = "unknown", url = ""),
            location = app.web.drjackycv.feature.products.entity.LocationUI(name = "Worldender's lair", url = ""),
            image = "https://rickandmortyapi.com/api/character/avatar/362.jpeg",
            episode = emptyList(),
            url = "https://rickandmortyapi.com/api/character/362",
            created = "2018-01-10T18:52:49.604Z",
        )
    )
    val uiState = ProductsUiState.Success(items = PagingData.from(items))

    ProductsListContent(
        uiState = uiState,
        themeFAB = {},
        navigateToProduct = {},
        onBackClick = {},
    )
}
