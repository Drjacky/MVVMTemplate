package app.web.drjackycv.data.products.repository

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import app.web.drjackycv.data.products.datasource.ProductsRemoteDataSource
import app.web.drjackycv.data.products.datasource.ProductsRemoteDataSourceFactory
import app.web.drjackycv.domain.base.Listing
import app.web.drjackycv.domain.products.entity.Beer
import app.web.drjackycv.domain.products.repository.ProductsRepository
import java.util.concurrent.Executor
import javax.inject.Inject

class ProductsRepositoryImpl @Inject constructor(
    private val productsRemoteDataSource: ProductsRemoteDataSource,
    private val networkExecutor: Executor
) : ProductsRepository {

    override fun getBeersById(ids: String): Listing<Beer> {
        val factory = productRemoteDataSourceFactory(ids)
        val config = pagedListConfig()
        val livePagedList: LiveData<PagedList<Beer>> = LivePagedListBuilder(factory, config)
            .setFetchExecutor(networkExecutor)
            .build()

        return Listing(livePagedList)
    }


    private fun pagedListConfig(pageSize: Int = 15): PagedList.Config {
        return PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(pageSize * 2)
            .setPageSize(pageSize)
            .build()
    }

    private fun productRemoteDataSourceFactory(ids: String) =
        ProductsRemoteDataSourceFactory(ids, productsRemoteDataSource)

}