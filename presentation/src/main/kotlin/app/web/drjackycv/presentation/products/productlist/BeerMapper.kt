package app.web.drjackycv.presentation.products.productlist

import app.web.drjackycv.domain.products.entity.Beer
import app.web.drjackycv.presentation.base.mapper.MapperUI
import app.web.drjackycv.presentation.products.entity.BeerUI

class BeerMapper : MapperUI<Beer, BeerUI> {

    override fun mapToUI(obj: Beer): BeerUI = with(obj) {
        BeerUI(
            id = id,
            name = name,
            tagline = tagline,
            description = description,
            imageUrl = imageUrl,
            abv = abv
        )
    }

}