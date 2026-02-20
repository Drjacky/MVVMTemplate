package app.web.drjackycv.feature.products.navigation

import app.web.drjackycv.feature.products.choose.ChoosePathType

interface ProductsNavigator {
    fun navigateToProductsList(choosePathType: ChoosePathType)
    fun navigateToProduct(productId: String, choosePathType: ChoosePathType)
    fun navigateBack()
}
