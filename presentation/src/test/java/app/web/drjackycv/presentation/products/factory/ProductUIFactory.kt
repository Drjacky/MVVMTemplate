package app.web.drjackycv.presentation.products.factory

import app.web.drjackycv.presentation.products.entity.BeerUI

class ProductUIFactory {

    companion object {

        fun createProducts(count: Int): List<BeerUI> {
            return (1..count).map {
                BeerUI( //TODO
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