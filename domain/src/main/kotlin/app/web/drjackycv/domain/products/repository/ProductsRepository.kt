package app.web.drjackycv.domain.products.repository

import app.web.drjackycv.domain.products.entity.Clusters
import app.web.drjackycv.domain.products.entity.Product
import io.reactivex.Single

interface ProductsRepository {

    fun getClusters(): Single<Clusters>

    fun getProductDetail(id: Int): Single<Product>

}