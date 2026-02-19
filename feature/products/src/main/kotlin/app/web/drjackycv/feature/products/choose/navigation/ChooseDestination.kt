package app.web.drjackycv.feature.products.choose.navigation

import androidx.compose.animation.graphics.ExperimentalAnimationGraphicsApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import app.web.drjackycv.core.ui.navigation.MyNavigationDestination
import app.web.drjackycv.feature.products.choose.ChoosePathType
import app.web.drjackycv.feature.products.choose.ChooseRoute
import coil.annotation.ExperimentalCoilApi

object ChooseDestination : MyNavigationDestination {
    override val route = "choose_route"
    override val destination = "choose_destination"
}


@ExperimentalCoilApi
@ExperimentalComposeUiApi
@ExperimentalAnimationGraphicsApi
fun NavGraphBuilder.chooseGraph(
    themeFAB: @Composable () -> Unit,
    navigateToProductsList: (ChoosePathType) -> Unit,
) {
    composable(route = ChooseDestination.route) {
        ChooseRoute(
            themeFAB = themeFAB,
            navigateToProductsList = navigateToProductsList,
        )
    }
}