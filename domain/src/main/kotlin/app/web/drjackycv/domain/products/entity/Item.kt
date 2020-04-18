package app.web.drjackycv.domain.products.entity

open class Item(
    open val id: Int,
    open val price: String,
    open val title: String,
    open val size: String,
    open val imageUrl: String
)