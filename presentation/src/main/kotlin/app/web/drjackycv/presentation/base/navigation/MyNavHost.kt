package app.web.drjackycv.presentation.base.navigation

import androidx.compose.animation.graphics.ExperimentalAnimationGraphicsApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier

import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import app.web.drjackycv.presentation.products.choose.navigation.ChooseDestination
import app.web.drjackycv.presentation.products.choose.navigation.chooseGraph
import app.web.drjackycv.presentation.products.productdetail.navigation.ProductDestination
import app.web.drjackycv.presentation.products.productdetail.navigation.productGraph
import app.web.drjackycv.presentation.products.productlist.navigation.ProductsListDestination
import app.web.drjackycv.presentation.products.productlist.navigation.productsListGraph
import coil.annotation.ExperimentalCoilApi


@ExperimentalCoilApi
@ExperimentalComposeUiApi
@ExperimentalAnimationGraphicsApi
@Composable
fun MyNavHost(
    navController: NavHostController,
    themeFAB: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    onNavigateToDestination: (MyNavigationDestination, String) -> Unit = { _, _ -> },
    onBackClick: () -> Unit = {},
    startDestination: String = ChooseDestination.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        chooseGraph(
            themeFAB = themeFAB,
            navigateToProductsList = {
                onNavigateToDestination(
                    ProductsListDestination,
                    ProductsListDestination.createNavigationRoute(it)
                )
            },
        )
        productsListGraph(
            navigateToProduct = {
                onNavigateToDestination(
                    ProductDestination,
                    ProductDestination.createNavigationRoute(it)
                )
            },
            themeFAB = themeFAB,
            onBackClick = onBackClick
        )
        productGraph(themeFAB = themeFAB, onBackClick = onBackClick)
    }
}