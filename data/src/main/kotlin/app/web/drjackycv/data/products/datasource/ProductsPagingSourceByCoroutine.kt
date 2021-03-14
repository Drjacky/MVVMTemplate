package app.web.drjackycv.data.products.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import app.web.drjackycv.data.products.entity.BeerMapper
import app.web.drjackycv.data.products.remote.ProductsApi
import app.web.drjackycv.domain.base.Failure
import app.web.drjackycv.domain.products.entity.Beer
import io.reactivex.annotations.NonNull
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException
import javax.inject.Inject
import javax.inject.Singleton


private const val STARTING_PAGE_INDEX = 1

@Singleton
class ProductsPagingSourceByCoroutine @Inject constructor(
    private val productsApi: ProductsApi,
    //private val query: String
) : PagingSource<Int, Beer>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Beer> {
        val position = params.key ?: STARTING_PAGE_INDEX
        //val apiQuery = query

        return try {
            val response = productsApi.getBeersListByCoroutine(position)
                .map {
                    BeerMapper().mapLeftToRight(it)
                }

            toLoadResult(response, position)
        } catch (e: Exception) {
            when (e) {
                is UnknownHostException, is SocketTimeoutException -> {
                    LoadResult.Error(
                        Failure.NoInternet(e.message)
                    )
                }
                is TimeoutException -> {
                    LoadResult.Error(
                        Failure.Timeout(e.message)
                    )
                }
                else -> {
                    LoadResult.Error(
                        Failure.Unknown(e.message)
                    )
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