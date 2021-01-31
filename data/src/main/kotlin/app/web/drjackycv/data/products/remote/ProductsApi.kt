package app.web.drjackycv.data.products.remote

import app.web.drjackycv.data.products.entity.BeerResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductsApi {

    @GET("beers")
    fun getBeersList(
        /*@Query("ids") ids: String,*/
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: Int = 40
    ): Single<List<BeerResponse>>

    @GET("beers")
    suspend fun getBeersListByCoroutine(
        /*@Query("ids") ids: String,*/
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: Int = 40
    ): List<BeerResponse>

}