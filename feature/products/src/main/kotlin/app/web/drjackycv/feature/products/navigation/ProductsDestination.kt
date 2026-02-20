package app.web.drjackycv.feature.products.navigation

import androidx.compose.runtime.Immutable
import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Immutable
sealed interface ProductsDestination : NavKey {
    @Serializable
    data object Choose : ProductsDestination

    @Serializable
    data class ProductsList(val choose: String) : ProductsDestination

    @Serializable
    data class ProductDetail(val productId: String, val choose: String) : ProductsDestination
}
