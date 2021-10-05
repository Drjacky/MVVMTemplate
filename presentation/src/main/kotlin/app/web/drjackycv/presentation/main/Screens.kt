package app.web.drjackycv.presentation.main

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screens(val route: String, val label: String, val icon: ImageVector? = null) {
    object ChooseScreen : Screens("Choose", "Choose")
    object ProductsScreen : Screens("Products", "Products", Icons.Default.Person)
    object ProductDetailsScreen : Screens("ProductDetails", "ProductDetails")
}