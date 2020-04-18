package app.web.drjackycv.data.products.entity

import app.web.drjackycv.data.base.ResponseObject
import app.web.drjackycv.domain.products.entity.Product
import app.web.drjackycv.domain.products.entity.Products

data class ProductsResponse(
    val products: List<Product>
) : ResponseObject<Products> {

    override fun toDomain(): Products = Products(
        products = products
    )

}