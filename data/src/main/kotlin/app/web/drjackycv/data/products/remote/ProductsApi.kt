package app.web.drjackycv.data.products.remote

import app.web.drjackycv.data.network.BaseApiService
import app.web.drjackycv.data.network.GenericNetworkResponse
import app.web.drjackycv.data.products.entity.BeerResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductsApi : BaseApiService {

    @GET("beers")
    fun getBeersList(
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: Int = 40,
    ): Single<GenericNetworkResponse<List<BeerResponse>>>

    @GET("beers/{beer_id}")
    fun getBeer(
        @Path("beer_id") id: String?,
    ): Single<GenericNetworkResponse<List<BeerResponse>>>

    @GET("beers")
    suspend fun getBeersListByCoroutine(
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: Int = 40,
    ): List<BeerResponse>

    @GET("beers/{beer_id}")
    suspend fun getBeerByCoroutine(
        @Path("beer_id") id: String?,
    ): List<BeerResponse>

}