package app.web.drjackycv.domain.products.repository

import app.web.drjackycv.domain.base.Listing
import app.web.drjackycv.domain.products.entity.Beer

interface ProductsRepository {

    fun getBeersById(ids: String): Listing<Beer>

}