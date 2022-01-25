package app.web.drjackycv.data.products.factory

import app.web.drjackycv.data.products.entity.BeerResponse

class ProductResponseFactory {

    companion object {

        fun createProducts(count: Int): List<BeerResponse> {
            return (1..count).map {
                BeerResponse(
                    id = it,
                    name = "name:$it",
                    tagline = "tagline:$it",
                    description = "description:$it",
                    imageUrl = "https://images.punkapi.com/v2/$it.png",
                    abv = it.toDouble()
                )
            }
        }
    }

}