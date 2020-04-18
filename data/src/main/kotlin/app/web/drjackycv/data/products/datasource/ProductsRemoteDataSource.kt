package app.web.drjackycv.data.products.datasource

import app.web.drjackycv.data.products.remote.ProductsApi
import app.web.drjackycv.domain.products.entity.Clusters
import app.web.drjackycv.domain.products.entity.Product
import io.reactivex.Single
import javax.inject.Inject

class ProductsRemoteDataSource @Inject constructor(
    private val productsApi: ProductsApi
) {

    fun getClusters(): Single<Clusters> =
        productsApi.getClusters()
            .map { it.toDomain() }

    fun getProductDetail(productId: Int): Single<Product> =
        productsApi.getProductDetail(productId = productId)
            .map { it.toDomain() }

}