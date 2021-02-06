package app.web.drjackycv.data.products.datasource

import androidx.paging.PagingState
import androidx.paging.rxjava2.RxPagingSource
import app.web.drjackycv.data.products.remote.ProductsApi
import app.web.drjackycv.domain.base.Failure
import app.web.drjackycv.domain.base.RecyclerItem
import io.reactivex.Single
import io.reactivex.annotations.NonNull
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton


private const val STARTING_PAGE_INDEX = 1

@Singleton
class ProductsPagingSource @Inject constructor(
    private val productsApi: ProductsApi
    //private val query: String
) : RxPagingSource<Int, RecyclerItem>() {

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, RecyclerItem>> {
        val position = params.key ?: STARTING_PAGE_INDEX
        //val apiQuery = query

        return productsApi.getBeersList(position, params.loadSize)
            .subscribeOn(Schedulers.io())
            .map { listBeerResponse ->
                listBeerResponse.map { beerResponse ->
                    beerResponse.toDomain()
                }
            }
            .map { toLoadResult(it, position) }
            .onErrorReturn {
                LoadResult.Error(
                    Failure.FailureWithMessage(it.message)
                )
            }
    }

    override val jumpingSupported = true

    override fun getRefreshKey(state: PagingState<Int, RecyclerItem>): Int? = state.anchorPosition

    private fun toLoadResult(
        @NonNull response: List<RecyclerItem>,
        position: Int
    ): LoadResult<Int, RecyclerItem> {
        return LoadResult.Page(
            data = response,
            prevKey = if (position == STARTING_PAGE_INDEX) null else position - 1,
            nextKey = if (response.isEmpty()) null else position + 1,
            itemsBefore = LoadResult.Page.COUNT_UNDEFINED,
            itemsAfter = LoadResult.Page.COUNT_UNDEFINED
        )
    }

}