package app.web.drjackycv.presentation.products.productlist

import app.web.drjackycv.domain.products.entity.Item
import app.web.drjackycv.presentation.base.mapper.MapperUI
import app.web.drjackycv.presentation.products.entity.ItemUI

class ItemMapper : MapperUI<Item, ItemUI> {

    override fun mapToUI(obj: Item): ItemUI = with(obj) {
        ItemUI(
            id = id,
            price = price,
            title = title,
            size = size,
            imageUrl = imageUrl
        )
    }

}