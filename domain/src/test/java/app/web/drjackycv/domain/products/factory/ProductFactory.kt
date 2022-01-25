package app.web.drjackycv.domain.products.factory

import app.web.drjackycv.domain.products.entity.Beer

class ProductFactory {

    companion object {

        fun createProducts(count: Int): List<Beer> {
            return (1..count).map {
                Beer(
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