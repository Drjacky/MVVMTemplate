package app.web.drjackycv.data.products.datasource

import androidx.paging.PagingState
import androidx.paging.rxjava3.RxPagingSource
import app.web.drjackycv.data.network.NetworkResponse
import app.web.drjackycv.data.products.entity.BeerMapper
import app.web.drjackycv.data.products.remote.ProductsApi
import app.web.drjackycv.domain.base.Failure
import app.web.drjackycv.domain.products.entity.Beer
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException
import javax.inject.Inject
import javax.inject.Singleton


private const val STARTING_PAGE_INDEX = 1

@Singleton
class ProductsPagingSource @Inject constructor(
    private val productsApi: ProductsApi,
    //private val query: String
) : RxPagingSource<Int, Beer>() {

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, Beer>> {
        val position = params.key ?: STARTING_PAGE_INDEX
        //val apiQuery = query

        return productsApi.getBeersList(position, params.loadSize)
            .subscribeOn(Schedulers.io())
            .map { response ->
                when (response) {
                    is NetworkResponse.Success -> {
                        val list = response.body.map {
                            BeerMapper().mapLeftToRight(it)
                        }

                        toLoadResult(list, position)
                    }
                    is NetworkResponse.ApiError -> {
                        LoadResult.Error(Failure.Api(response.body?.message))
                    }
                    is NetworkResponse.NetworkError -> {
                        LoadResult.Error(Failure.NoInternet(response.error.message))
                    }
                    else -> {
                        LoadResult.Error(Failure.Unknown())
                    }
                }
            }
            .onErrorReturn { throwable ->
                when (throwable) {
                    is UnknownHostException, is SocketTimeoutException -> {
                        LoadResult.Error(Failure.NoInternet(throwable.message))
                    }
                    is TimeoutException -> {
                        LoadResult.Error(Failure.Timeout(throwable.message))
                    }
                    else -> {
                        LoadResult.Error(Failure.Unknown(throwable.message))
                    }
                }
            }
    }

    override val jumpingSupported = true

    override fun getRefreshKey(state: PagingState<Int, Beer>): Int? = state.anchorPosition

    private fun toLoadResult(
        @NonNull response: List<Beer>,
        position: Int,
    ): LoadResult<Int, Beer> {
        return LoadResult.Page(
            data = response,
            prevKey = if (position == STARTING_PAGE_INDEX) null else position - 1,
            nextKey = if (response.isEmpty()) null else position + 1,
            itemsBefore = LoadResult.Page.COUNT_UNDEFINED,
            itemsAfter = LoadResult.Page.COUNT_UNDEFINED
        )
    }

}