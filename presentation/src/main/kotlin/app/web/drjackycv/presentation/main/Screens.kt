package app.web.drjackycv.presentation.main

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screens(
    val route: String,
    val navArgumentKey: String = "",
    val label: String,
    val icon: ImageVector? = null
) {

    object ChooseScreen : Screens(
        route = "Choose",
        label = "Choose"
    )

    object ProductsScreen : Screens(
        route = "Products",
        navArgumentKey = "choosePathType",
        label = "Products",
        icon = Icons.Default.Person
    )

    object ProductDetailsScreen : Screens(
        route = "ProductDetails",
        navArgumentKey = "beerId",
        label = "ProductDetails"
    )

}