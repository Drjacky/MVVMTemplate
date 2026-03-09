package app.web.drjackycv.core.data.products.remote

import app.web.drjackycv.core.data.products.entity.CharactersResponse
import app.web.drjackycv.core.data.products.entity.ProductResponse
import app.web.drjackycv.core.network.BaseApiService
import app.web.drjackycv.core.network.GenericNetworkResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductsApi : BaseApiService {

    @GET("character")
    fun getProductsList(
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: Int = 40,
    ): Single<GenericNetworkResponse<CharactersResponse>>

    @GET("character/{product_id}")
    fun getProduct(
        @Path("product_id") id: String?,
    ): Single<GenericNetworkResponse<ProductResponse>>

    @GET("character")
    suspend fun getProductsListByCoroutine(
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: Int = 40,
    ): CharactersResponse

    @GET("character/{product_id}")
    suspend fun getProductByCoroutine(
        @Path("product_id") id: String?,
    ): ProductResponse
}
