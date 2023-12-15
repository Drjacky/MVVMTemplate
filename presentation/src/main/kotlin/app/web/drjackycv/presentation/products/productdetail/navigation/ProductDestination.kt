package app.web.drjackycv.presentation.products.productdetail.navigation

import android.net.Uri
import androidx.compose.animation.graphics.ExperimentalAnimationGraphicsApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import app.web.drjackycv.presentation.base.navigation.MyNavigationDestination
import app.web.drjackycv.presentation.products.productdetail.ProductRoute
import coil.annotation.ExperimentalCoilApi

object ProductDestination : MyNavigationDestination {
    const val productArg = "product"
    override val route = "product_details_route/{$productArg}"
    override val destination = "product_details_destination"

    fun createNavigationRoute(productArg: String): String {
        val encodedId = Uri.encode(productArg)
        return "product_details_route/$encodedId"
    }

    fun fromNavArgs(entry: NavBackStackEntry): String {
        val encodedId = entry.arguments?.getString(productArg)!!
        return Uri.decode(encodedId)
    }
}


@ExperimentalCoilApi
@ExperimentalComposeUiApi
@ExperimentalAnimationGraphicsApi
fun NavGraphBuilder.productGraph(
    themeFAB: @Composable () -> Unit,
    onBackClick: () -> Unit,
) {
    composable(
        route = ProductDestination.route,
        arguments = listOf(
            navArgument(ProductDestination.productArg) { type = NavType.StringType }
        ),
        deepLinks = listOf(navDeepLink {
            uriPattern = "mvvmtemplate://product/{${ProductDestination.productArg}}"
        })
    ) {
        val product = ProductDestination.fromNavArgs(it)
        ProductRoute(productId = product, themeFAB = themeFAB, onBackClick = onBackClick)
    }
}