package app.web.drjackycv.presentation.products.productlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import androidx.paging.rxjava3.cachedIn
import app.web.drjackycv.domain.base.Failure
import app.web.drjackycv.domain.products.usecase.GetBeersListByCoroutineUseCase
import app.web.drjackycv.domain.products.usecase.GetBeersListUseCase
import app.web.drjackycv.presentation.base.entity.Result
import app.web.drjackycv.presentation.base.entity.asResult
import app.web.drjackycv.presentation.base.viewmodel.BaseViewModel
import app.web.drjackycv.presentation.products.entity.BeerUI
import app.web.drjackycv.presentation.products.entity.mapIt
import autodispose2.autoDispose
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException
import javax.inject.Inject

@HiltViewModel
class ProductsListViewModel @Inject constructor(
    private val getBeersUseCase: GetBeersListUseCase,
    private val getBeersListByCoroutineUseCase: GetBeersListByCoroutineUseCase,
    private val savedStateHandle: SavedStateHandle,
) : BaseViewModel() {

    private val _ldProductsList: MutableLiveData<ProductsUiState> = MutableLiveData()
    val ldProductsList: LiveData<ProductsUiState> = _ldProductsList

    val productsListByCoroutine: StateFlow<ProductsUiState> =
        getProductsByCoroutinePath()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = ProductsUiState.Loading
            )

    @ExperimentalCoroutinesApi
    fun getProductsByRxPath() {
        getBeersUseCase()
            .cachedIn(viewModelScope)
            .doOnSubscribe {
                _ldProductsList.value = ProductsUiState.Loading
            }
            .observeOn(AndroidSchedulers.mainThread())
            .autoDispose(this)
            .subscribe({ result ->
                result.map {
                    it.mapIt()
                }.let {
                    _ldProductsList.value = ProductsUiState.Success(it)
                }
            }, { e -> //no-op: already handled by paging loadState
                when (e) {
                    is UnknownHostException, is SocketTimeoutException -> {
                        _ldProductsList.value = ProductsUiState.Error(Failure.NoInternet(e.message))
                    }
                    is TimeoutException -> {
                        _ldProductsList.value = ProductsUiState.Error(Failure.Timeout(e.message))
                    }
                    else -> {
                        _ldProductsList.value = ProductsUiState.Error(Failure.Unknown(e.message))
                    }
                }
            })
    }

    private fun getProductsByCoroutinePath(): Flow<ProductsUiState> =
        getBeersListByCoroutineUseCase()
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
    data class Success(val items: PagingData<BeerUI> = PagingData.empty()) : ProductsUiState
    data class Error(val error: Throwable) : ProductsUiState
    object Loading : ProductsUiState
}