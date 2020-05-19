package app.web.drjackycv.data.products.datasource

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import app.web.drjackycv.domain.base.RecyclerItem
import app.web.drjackycv.domain.base.ResultState
import javax.inject.Inject

@SuppressLint("CheckResult")
class ProductsRemotePagedDataSource @Inject constructor(
    private val ids: String,
    private val productsRemoteDataSource: ProductsRemoteDataSource
) : PageKeyedDataSource<Int, RecyclerItem>() {

    val ldError = MutableLiveData<Throwable>()

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, RecyclerItem>
    ) {
        val currentPage = 1
        val nextPage = currentPage + 1

        productsRemoteDataSource.getBeersById(
            ids = ids,
            page = currentPage,
            perPage = params.requestedLoadSize,
            onResult = { responseBody ->
                when (responseBody) {
                    is ResultState.Success -> {
                        val items = responseBody.data ?: emptyList()
                        callback.onResult(items, null, nextPage)
                    }
                    is ResultState.Error -> {
                        val error = responseBody.failure
                        ldError.postValue(error)
                    }
                }
            })
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, RecyclerItem>) {
        val currentPage = params.key
        val nextPage = currentPage + 1

        productsRemoteDataSource.getBeersById(
            ids = ids,
            page = currentPage,
            perPage = params.requestedLoadSize,
            onResult = { responseBody ->
                when (responseBody) {
                    is ResultState.Success -> {
                        val items = responseBody.data ?: emptyList()
                        callback.onResult(items, nextPage)
                    }
                    is ResultState.Error -> {
                        val error = responseBody.failure
                        ldError.postValue(error)
                    }
                }
            })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, RecyclerItem>) {}

}