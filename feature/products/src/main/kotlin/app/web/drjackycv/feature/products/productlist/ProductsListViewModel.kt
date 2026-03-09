package app.web.drjackycv.feature.products.productlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import androidx.paging.rxjava3.cachedIn
import app.web.drjackycv.core.common.base.Result
import app.web.drjackycv.core.common.base.asResult
import app.web.drjackycv.core.domain.base.Failure
import app.web.drjackycv.core.domain.products.usecase.GetProductsListByCoroutineUseCase
import app.web.drjackycv.core.domain.products.usecase.GetProductsListUseCase
import app.web.drjackycv.feature.products.entity.ProductUI
import app.web.drjackycv.feature.products.entity.mapIt
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException
import javax.inject.Inject

@HiltViewModel
class ProductsListViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsListUseCase,
    private val getProductsListByCoroutineUseCase: GetProductsListByCoroutineUseCase,
) : ViewModel() {

    private val disposables = CompositeDisposable()

    private val _productsListByRx = MutableStateFlow<ProductsUiState>(ProductsUiState.Loading)
    val productsListByRx: StateFlow<ProductsUiState> = _productsListByRx.asStateFlow()

    val productsListByCoroutine: StateFlow<ProductsUiState> =
        getProductsByCoroutinePath()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = ProductsUiState.Loading
            )

    @ExperimentalCoroutinesApi
    fun getProductsByRxPath() {
        getProductsUseCase()
            .cachedIn(viewModelScope)
            .doOnSubscribe {
                _productsListByRx.value = ProductsUiState.Loading
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->
                result.map {
                    it.mapIt()
                }.let {
                    _productsListByRx.value = ProductsUiState.Success(it)
                }
            }, { e ->
                when (e) {
                    is UnknownHostException, is SocketTimeoutException -> {
                        _productsListByRx.value =
                            ProductsUiState.Error(Failure.NoInternet(e.message))
                    }

                    is TimeoutException -> {
                        _productsListByRx.value = ProductsUiState.Error(Failure.Timeout(e.message))
                    }

                    else -> {
                        _productsListByRx.value = ProductsUiState.Error(Failure.Unknown(e.message))
                    }
                }
            }).also { disposables.add(it) }
    }

    override fun onCleared() {
        disposables.clear()
        super.onCleared()
    }

    private fun getProductsByCoroutinePath(): Flow<ProductsUiState> =
        getProductsListByCoroutineUseCase()
            .cachedIn(viewModelScope)
            .asResult()
            .map { result ->
                when (result) {
                    is Result.Success -> {
                        val items = result.data
                        items.map {
                            it.mapIt()
                        }.let {
                            ProductsUiState.Success(it)
                        }
                    }

                    is Result.Error -> {
                        ProductsUiState.Error(result.failure)
                    }

                    is Result.Loading -> {
                        ProductsUiState.Loading
                    }
                }
            }
}
sealed interface ProductsUiState {
    data class Success(val items: PagingData<ProductUI> = PagingData.empty()) : ProductsUiState
    data class Error(val error: Throwable) : ProductsUiState
    data object Loading : ProductsUiState
}
