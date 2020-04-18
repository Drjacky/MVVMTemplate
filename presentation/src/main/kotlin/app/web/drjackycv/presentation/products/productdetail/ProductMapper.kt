package app.web.drjackycv.presentation.products.productdetail

import app.web.drjackycv.domain.products.entity.Product
import app.web.drjackycv.presentation.base.mapper.MapperUI
import app.web.drjackycv.presentation.products.entity.ProductUI

class ProductMapper : MapperUI<Product, ProductUI> {

    override fun mapToUI(obj: Product): ProductUI = with(obj) {
        ProductUI(
            id = id,
            price = price,
            title = title,
            imageUrl = imageUrl,
            description = description,
            allergyInformation = allergyInformation
        )
    }

}