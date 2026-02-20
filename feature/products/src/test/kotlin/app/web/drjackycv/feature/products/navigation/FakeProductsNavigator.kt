package app.web.drjackycv.feature.products.navigation

import app.web.drjackycv.feature.products.choose.ChoosePathType

class FakeProductsNavigator : ProductsNavigator {

    private val _navigationEvents = mutableListOf<String>()
    val navigationEvents: List<String> get() = _navigationEvents

    override fun navigateToProductsList(choosePathType: ChoosePathType) {
        _navigationEvents.add("navigateToProductsList:${choosePathType.name}")
    }

    override fun navigateToProduct(productId: String) {
        _navigationEvents.add("navigateToProduct:$productId")
    }

    override fun navigateBack() {
        _navigationEvents.add("navigateBack")
    }

    fun clearEvents() {
        _navigationEvents.clear()
    }

    fun getLastEvent(): String? = _navigationEvents.lastOrNull()
}
