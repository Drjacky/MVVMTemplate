package app.web.drjackycv.data.products.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.rxjava3.flowable
import app.web.drjackycv.data.products.datasource.ProductsPagingSource
import app.web.drjackycv.data.products.datasource.ProductsPagingSourceByCoroutine
import app.web.drjackycv.data.products.entity.mapIt
import app.web.drjackycv.data.products.remote.ProductsApi
import app.web.drjackycv.domain.extension.allowReads
import app.web.drjackycv.domain.products.entity.Beer
import app.web.drjackycv.domain.products.repository.ProductsListRepository
import io.reactivex.rxjava3.core.Flowable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.rx3.asFlowable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductsListRepositoryImpl @Inject constructor(
    private val pagingSource: ProductsPagingSource,
    private val pagingSourceByCoroutine: ProductsPagingSourceByCoroutine,
    private val productsApi: ProductsApi
) : ProductsListRepository {

    override fun getBeersList(): Flowable<PagingData<Beer>> =
        allowReads {
            Pager(
                config = PagingConfig(
                    pageSize = 10,
                    enablePlaceholders = false,
                    maxSize = 30,
                    prefetchDistance = 5,
                    initialLoadSize = 10,
                    jumpThreshold = 60
                ),
                pagingSourceFactory = { pagingSource }
            ).flowable
        }

    override fun getBeer(id: String): Flowable<Beer> { //TODO: Use Rx
        return flow {
            val response = productsApi.getBeerByCoroutine(id)
            val result = response.mapIt()

            emit(result)
        }.asFlowable()
    }

    override fun getBeersListByCoroutine(): Flow<PagingData<Beer>> =
        allowReads {
            Pager(
                config = PagingConfig(
                    pageSize = 10,
                    enablePlaceholders = false,
                    maxSize = 30,
                    prefetchDistance = 5,
                    initialLoadSize = 10,
                    jumpThreshold = 60
                ),
                pagingSourceFactory = { pagingSourceByCoroutine }
            ).flow
        }

    override fun getBeerByCoroutine(id: String): Flow<Beer> = //TODO: Handle exception
        flow {
            val response = productsApi.getBeerByCoroutine(id)
            val result = response.mapIt()

            emit(result)
        }

}