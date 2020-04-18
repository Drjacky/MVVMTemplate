package app.web.drjackycv.domain.products.entity

data class Product(
    val id: Int,
    val price: String,
    val title: String,
    val imageUrl: String,
    val description: String,
    val allergyInformation: String
)