package app.web.drjackycv.feature.products.productdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.web.drjackycv.core.common.base.Result
import app.web.drjackycv.core.common.base.asResult
import app.web.drjackycv.core.domain.products.usecase.GetBeerByCoroutineUseCase
import app.web.drjackycv.core.domain.products.usecase.GetBeerUseCase
import app.web.drjackycv.feature.products.entity.BeerUI
import app.web.drjackycv.feature.products.entity.mapIt
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class ProductViewModel @Inject constructor(
    private val getBeerUseCase: GetBeerUseCase,
    private val getBeerByCoroutineUseCase: GetBeerByCoroutineUseCase,
) : ViewModel() {

    private val disposables = CompositeDisposable()

    private val _productByRx = MutableStateFlow<ProductUiState>(ProductUiState.Loading)
    val productByRx: StateFlow<ProductUiState> = _productByRx.asStateFlow()

    private val _productId = MutableStateFlow("")

    val productByCoroutine: StateFlow<ProductUiState> =
        _productId
            .filter { it.isNotEmpty() }
            .flatMapLatest { id -> getProductByCoroutine(id) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = ProductUiState.Loading
            )

    fun setProductId(id: String) {
        _productId.value = id
    }

    fun getProduct(id: String) {
        _productByRx.value = ProductUiState.Loading
        getBeerUseCase(id)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ beer ->
                _productByRx.value = ProductUiState.Success(beer.mapIt())
            }, { e ->
                _productByRx.value = ProductUiState.Error(e)
            }).also { disposables.add(it) }
    }

    override fun onCleared() {
        disposables.clear()
        super.onCleared()
    }

    private fun getProductByCoroutine(ids: String) =
        getBeerByCoroutineUseCase(ids)
            .asResult()
            .map { result ->
                when (result) {
                    is Result.Success -> {
                        val item = result.data
                        ProductUiState.Success(item.mapIt())
                    }

                    is Result.Error -> {
                        ProductUiState.Error(result.failure)
                    }

                    is Result.Loading -> {
                        ProductUiState.Loading
                    }
                }
            }
}

sealed interface ProductUiState {
    data class Success(val item: BeerUI?) : ProductUiState
    data class Error(val error: Throwable) : ProductUiState
    data object Loading : ProductUiState
}
