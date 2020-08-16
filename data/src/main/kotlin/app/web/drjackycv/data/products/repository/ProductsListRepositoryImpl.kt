package app.web.drjackycv.data.products.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.rxjava2.flowable
import app.web.drjackycv.data.products.datasource.ProductsPagingSource
import app.web.drjackycv.domain.base.RecyclerItem
import app.web.drjackycv.domain.products.repository.ProductsListRepository
import io.reactivex.Flowable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductsListRepositoryImpl @Inject constructor(
    private val pagingSource: ProductsPagingSource
) : ProductsListRepository {

    override fun getBeersList(ids: String): Flowable<PagingData<RecyclerItem>> = Pager(
        config = PagingConfig(
            pageSize = 10,
            enablePlaceholders = false,
            maxSize = 30,
            prefetchDistance = 5,
            initialLoadSize = 10
        ),
        pagingSourceFactory = { pagingSource }
    ).flowable

}