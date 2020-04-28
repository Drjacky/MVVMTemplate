package app.web.drjackycv.data.products.remote

import app.web.drjackycv.data.products.entity.BeerResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductsApi {

    @GET("beers")
    fun getBeers(
        /*@Query("ids") ids: String,*/
        @Query("page") page: Int = 2,
        @Query("per_page") perPage: Int = 25
    ): Call<List<BeerResponse>>

}