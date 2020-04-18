package app.web.drjackycv.data.products.remote

import app.web.drjackycv.data.products.entity.ClustersResponse
import app.web.drjackycv.data.products.entity.ProductResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductsApi {

    @GET("products")
    fun getClusters(): Single<ClustersResponse>

    @GET("product/{id}")
    fun getProductDetail(
        @Path("id") productId: Int
    ): Single<ProductResponse>

}