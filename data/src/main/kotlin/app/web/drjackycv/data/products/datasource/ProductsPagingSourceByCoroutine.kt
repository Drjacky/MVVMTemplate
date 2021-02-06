package app.web.drjackycv.data.products.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import app.web.drjackycv.data.products.remote.ProductsApi
import app.web.drjackycv.domain.base.Failure
import app.web.drjackycv.domain.base.RecyclerItem
import io.reactivex.annotations.NonNull
import javax.inject.Inject
import javax.inject.Singleton


private const val STARTING_PAGE_INDEX = 1

@Singleton
class ProductsPagingSourceByCoroutine @Inject constructor(
    private val productsApi: ProductsApi
    //private val query: String
) : PagingSource<Int, RecyclerItem>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RecyclerItem> {
        val position = params.key ?: STARTING_PAGE_INDEX
        //val apiQuery = query

        return try {
            val response = productsApi.getBeersListByCoroutine(position)
                .map {
                    it.toDomain()
                }

            toLoadResult(response, position)

        } catch (e: Exception) {
            LoadResult.Error(
                Failure.FailureWithMessage(e.message)
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