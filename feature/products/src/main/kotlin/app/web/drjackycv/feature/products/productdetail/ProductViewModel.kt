package app.web.drjackycv.feature.products.productdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import app.web.drjackycv.core.common.base.BaseViewModel
import app.web.drjackycv.core.common.base.Result
import app.web.drjackycv.core.common.base.asResult
import app.web.drjackycv.core.domain.products.usecase.GetBeerByCoroutineUseCase
import app.web.drjackycv.core.domain.products.usecase.GetBeerUseCase
import app.web.drjackycv.feature.products.entity.BeerUI
import app.web.drjackycv.feature.products.entity.mapIt
import autodispose2.autoDispose
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
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
) : BaseViewModel() {

    private val _ldProduct: MutableLiveData<BeerUI> = MutableLiveData()
    val ldProduct: LiveData<BeerUI> = _ldProduct

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
        getBeerUseCase(id)
            .observeOn(AndroidSchedulers.mainThread())
            .autoDispose(this)
            .subscribe {
                _ldProduct.value = it.mapIt()
            }
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
    data class Success(val item: BeerUI? = null) : ProductUiState
    data class Error(val error: Throwable) : ProductUiState
    data object Loading : ProductUiState
}
