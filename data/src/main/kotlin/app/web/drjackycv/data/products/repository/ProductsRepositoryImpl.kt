package app.web.drjackycv.data.products.repository

import app.web.drjackycv.data.products.datasource.ProductsRemoteDataSource
import app.web.drjackycv.domain.products.entity.Clusters
import app.web.drjackycv.domain.products.entity.Product
import app.web.drjackycv.domain.products.repository.ProductsRepository
import io.reactivex.Single
import javax.inject.Inject

class ProductsRepositoryImpl @Inject constructor(
    private val productsRemoteDataSource: ProductsRemoteDataSource
) : ProductsRepository {

    override fun getClusters(): Single<Clusters> =
        productsRemoteDataSource.getClusters()

    override fun getProductDetail(id: Int): Single<Product> =
        productsRemoteDataSource.getProductDetail(productId = id)

}