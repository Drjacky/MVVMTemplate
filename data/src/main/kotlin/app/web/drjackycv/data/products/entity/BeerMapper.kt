package app.web.drjackycv.data.products.entity

import app.web.drjackycv.domain.base.mapper.Mapper
import app.web.drjackycv.domain.products.entity.Beer

class BeerMapper : Mapper<BeerResponse, Beer> {

    override fun mapLeftToRight(obj: BeerResponse): Beer = with(obj) {
        Beer(
            id = id,
            name = name,
            tagline = tagline,
            description = description,
            imageUrl = imageUrl,
            abv = abv
        )
    }

}