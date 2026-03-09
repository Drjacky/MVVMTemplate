package app.web.drjackycv.core.data.products.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import app.web.drjackycv.core.data.products.entity.ProductResponse
import app.web.drjackycv.core.data.products.entity.mapIt
import app.web.drjackycv.core.data.products.remote.ProductsApi
import app.web.drjackycv.core.domain.base.Failure
import app.web.drjackycv.core.domain.products.entity.Product
import io.reactivex.rxjava3.annotations.NonNull
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.coroutines.cancellation.CancellationException

private const val STARTING_PAGE_INDEX = 1

@Singleton
class ProductsPagingSourceByCoroutine @Inject constructor(
    private val productsApi: ProductsApi,
) : PagingSource<Int, Product>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Product> {
        val position = params.key ?: STARTING_PAGE_INDEX

        return try {
            val response = productsApi.getProductsListByCoroutine(position).results
                .map(ProductResponse::mapIt)

            toLoadResult(response, position)
        } catch (e: CancellationException) {
            throw e
        } catch (@Suppress("TooGenericExceptionCaught") e: Exception) {
            when (e) {
                is UnknownHostException, is SocketTimeoutException -> {
                    LoadResult.Error(Failure.NoInternet(e.message))
                }

                is TimeoutException -> {
                    LoadResult.Error(Failure.Timeout(e.message))
                }

                else -> {
                    LoadResult.Error(Failure.Unknown(e.message))
                }
            }
        }
    }

    override val jumpingSupported = true

    override fun getRefreshKey(state: PagingState<Int, Product>): Int? = state.anchorPosition

    private fun toLoadResult(
        @NonNull response: List<Product>,
        position: Int,
    ): LoadResult<Int, Product> {
        return LoadResult.Page(
            data = response,
            prevKey = if (position == STARTING_PAGE_INDEX) null else position - 1,
            nextKey = if (response.isEmpty()) null else position + 1,
            itemsBefore = LoadResult.Page.COUNT_UNDEFINED,
            itemsAfter = LoadResult.Page.COUNT_UNDEFINED
        )
    }
}
