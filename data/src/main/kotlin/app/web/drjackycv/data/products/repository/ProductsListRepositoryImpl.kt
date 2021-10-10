package app.web.drjackycv.data.products.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.rxjava3.flowable
import app.web.drjackycv.data.products.datasource.ProductsPagingSource
import app.web.drjackycv.data.products.datasource.ProductsPagingSourceByCoroutine
import app.web.drjackycv.data.products.entity.BeerMapper
import app.web.drjackycv.data.products.remote.ProductsApi
import app.web.drjackycv.domain.extension.allowReads
import app.web.drjackycv.domain.products.entity.Beer
import app.web.drjackycv.domain.products.repository.ProductsListRepository
import io.reactivex.rxjava3.core.Flowable
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

    override fun getBeer(ids: String): Flowable<Beer> { //TODO
        return Flowable.just(
            Beer(
                id = 1,
                name = "name",
                tagline = "tagline",
                description = "description",
                imageUrl = "https://images.punkapi.com/v2/5.png",
                abv = 4.9
            )

        )
    }
    /*override fun getBeer(ids: String): Flowable<Beer> {
        return productsApi.getBeer(ids = ids)
            .subscribeOn(Schedulers.io())
            .map { response ->
                when (response) {
                    is NetworkResponse.Success -> {
                        BeerMapper().mapLeftToRight(response.body))
                    }
                    is NetworkResponse.ApiError -> {
                        Failure.Api(response.body?.message)
                    }
                    is NetworkResponse.NetworkError -> {
                        Failure.NoInternet(response.error.message)
                    }
                    else -> {
                        Failure.Unknown()
                    }
                }
            }
            .onErrorReturn { throwable ->
                when (throwable) {
                    is UnknownHostException, is SocketTimeoutException -> {
                        PagingSource.LoadResult.Error(Failure.NoInternet(throwable.message))
                    }
                    is TimeoutException -> {
                        PagingSource.LoadResult.Error(Failure.Timeout(throwable.message))
                    }
                    else -> {
                        PagingSource.LoadResult.Error(Failure.Unknown(throwable.message))
                    }
                }
            }
    }*/

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

    override fun getBeerByCoroutine(ids: String): Flow<Beer> {
        return flow {
            val response = productsApi.getBeerByCoroutine(ids)
            val result = BeerMapper().mapLeftToRight(response.first())

            emit(result)
        }
    }

}