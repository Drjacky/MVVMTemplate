package app.web.drjackycv.feature.products.navigation

import androidx.compose.animation.graphics.ExperimentalAnimationGraphicsApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import app.web.drjackycv.feature.products.choose.ChoosePathType
import app.web.drjackycv.feature.products.choose.ChooseRoute
import app.web.drjackycv.feature.products.productdetail.ProductRoute
import app.web.drjackycv.feature.products.productlist.ProductsListRoute
import coil.annotation.ExperimentalCoilApi

@OptIn(
    ExperimentalCoilApi::class,
    ExperimentalComposeUiApi::class,
    ExperimentalAnimationGraphicsApi::class,
)
fun EntryProviderScope<NavKey>.productsGraph(
    navigator: ProductsNavigator,
    themeFAB: @Composable () -> Unit,
) {
    entry<ProductsDestination.Choose> {
        ChooseRoute(
            themeFAB = themeFAB,
            navigateToProductsList = { navigator.navigateToProductsList(it) },
        )
    }
    entry<ProductsDestination.ProductsList> { key ->
        val choose = ChoosePathType.valueOf(key.choose)
        ProductsListRoute(
            choose = choose,
            navigateToProduct = { navigator.navigateToProduct(it, choose) },
            themeFAB = themeFAB,
            onBackClick = { navigator.navigateBack() },
        )
    }
    entry<ProductsDestination.ProductDetail> { key ->
        val choose = ChoosePathType.valueOf(key.choose)
        ProductRoute(
            productId = key.productId,
            choose = choose,
            themeFAB = themeFAB,
            onBackClick = { navigator.navigateBack() },
        )
    }
}
