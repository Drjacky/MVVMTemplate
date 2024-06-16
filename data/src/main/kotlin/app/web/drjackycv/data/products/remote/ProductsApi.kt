package app.web.drjackycv.data.products.remote

import app.web.drjackycv.data.network.BaseApiService
import app.web.drjackycv.data.network.GenericNetworkResponse
import app.web.drjackycv.data.products.entity.BeerResponse
import app.web.drjackycv.data.products.entity.CharactersResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductsApi : BaseApiService {

    @GET("character")
    fun getBeersList(
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: Int = 40,
    ): Single<GenericNetworkResponse<CharactersResponse>>

    @GET("character/{beer_id}")
    fun getBeer(
        @Path("beer_id") id: String?,
    ): Single<GenericNetworkResponse<BeerResponse>>

    @GET("character")
    suspend fun getBeersListByCoroutine(
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: Int = 40,
    ): CharactersResponse

    @GET("character/{beer_id}")
    suspend fun getBeerByCoroutine(
        @Path("beer_id") id: String?,
    ): BeerResponse

}