package app.web.drjackycv.core.data.products.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.rxjava3.flowable
import app.web.drjackycv.core.data.products.datasource.ProductsPagingSource
import app.web.drjackycv.core.data.products.datasource.ProductsPagingSourceByCoroutine
import app.web.drjackycv.core.data.products.entity.mapIt
import app.web.drjackycv.core.data.products.remote.ProductsApi
import app.web.drjackycv.core.domain.base.Failure
import app.web.drjackycv.core.domain.extension.allowReads
import app.web.drjackycv.core.domain.products.entity.Beer
import app.web.drjackycv.core.domain.products.repository.ProductsListRepository
import app.web.drjackycv.core.network.NetworkResponse
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
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

    override fun getBeer(id: String): Flowable<Beer> =
        productsApi.getBeer(id)
            .flatMap { response ->
                when (response) {
                    is NetworkResponse.Success -> Single.just(response.body.mapIt())
                    is NetworkResponse.ApiError -> Single.error(Failure.Api(response.body?.message))
                    is NetworkResponse.NetworkError -> Single.error(Failure.NoInternet(response.error.message))
                }
            }
            .toFlowable()

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

    override fun getBeerByCoroutine(id: String): Flow<Beer> =
        flow {
            val response = productsApi.getBeerByCoroutine(id)
            emit(response.mapIt())
        }

}