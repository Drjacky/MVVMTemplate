package app.web.drjackycv.presentation.products.productlist.navigation

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
import app.web.drjackycv.presentation.products.choose.ChoosePathType
import app.web.drjackycv.presentation.products.productlist.ProductsListRoute
import coil.annotation.ExperimentalCoilApi

object ProductsListDestination : MyNavigationDestination {
    const val chooseArg = "choose"
    override val route = "products_list_route/{$chooseArg}"
    override val destination = "products_list_destination"

    fun createNavigationRoute(chooseArg: ChoosePathType): String {
        val encodedId = Uri.encode(chooseArg.name)
        return "products_list_route/$encodedId"
    }

    fun fromNavArgs(entry: NavBackStackEntry): ChoosePathType {
        val encodedId = entry.arguments?.getString(ProductsListDestination.chooseArg)!!
        val decodedIdString = Uri.decode(encodedId)
        return ChoosePathType.valueOf(decodedIdString)
    }
}


@ExperimentalCoilApi
@ExperimentalComposeUiApi
@ExperimentalAnimationGraphicsApi
fun NavGraphBuilder.productsListGraph(
    navigateToProduct: (String) -> Unit,
    themeFAB: @Composable () -> Unit,
    onBackClick: () -> Unit,
) {
    composable(
        route = ProductsListDestination.route,
        arguments = listOf(
            navArgument(ProductsListDestination.chooseArg) { type = NavType.StringType }
        ),
        deepLinks = listOf(navDeepLink {
            uriPattern = "mvvmtemplate://products/{${ProductsListDestination.chooseArg}}"
        })
    ) {
        val choose = ProductsListDestination.fromNavArgs(it)
        ProductsListRoute(
            choose = choose,
            navigateToProduct = navigateToProduct,
            themeFAB = themeFAB,
            onBackClick = onBackClick
        )
    }
}