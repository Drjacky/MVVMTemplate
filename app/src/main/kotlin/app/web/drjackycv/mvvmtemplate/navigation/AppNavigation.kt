package app.web.drjackycv.mvvmtemplate.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import app.web.drjackycv.feature.products.choose.ChoosePathType
import app.web.drjackycv.feature.products.navigation.ProductsDestination
import app.web.drjackycv.feature.products.navigation.ProductsNavigator
import app.web.drjackycv.feature.products.navigation.productsGraph

@Composable
fun AppNavigation(
    themeFAB: @Composable () -> Unit,
    modifier: Modifier = Modifier,
) {
    val backStack = rememberNavBackStack(ProductsDestination.Choose as NavKey)

    val navigator = remember(backStack) {
        object : ProductsNavigator {
            override fun navigateToProductsList(choosePathType: ChoosePathType) {
                backStack.add(ProductsDestination.ProductsList(choosePathType.name))
            }

            override fun navigateToProduct(productId: String, choosePathType: ChoosePathType) {
                backStack.add(ProductsDestination.ProductDetail(productId, choosePathType.name))
            }

            override fun navigateBack() {
                backStack.removeLastOrNull()
            }
        }
    }

    NavDisplay(
        backStack = backStack,
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator(),
        ),
        entryProvider = entryProvider {
            productsGraph(navigator, themeFAB)
        },
        onBack = { backStack.removeLastOrNull() },
        modifier = modifier,
    )
}
