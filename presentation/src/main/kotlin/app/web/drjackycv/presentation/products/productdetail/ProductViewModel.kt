package app.web.drjackycv.presentation.products.productdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import app.web.drjackycv.domain.products.usecase.GetBeerByCoroutineUseCase
import app.web.drjackycv.domain.products.usecase.GetBeerUseCase
import app.web.drjackycv.presentation.base.entity.Result
import app.web.drjackycv.presentation.base.entity.asResult
import app.web.drjackycv.presentation.base.viewmodel.BaseViewModel
import app.web.drjackycv.presentation.products.entity.BeerUI
import app.web.drjackycv.presentation.products.entity.mapIt
import app.web.drjackycv.presentation.products.productdetail.navigation.ProductDestination
import autodispose2.autoDispose
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val getBeerUseCase: GetBeerUseCase,
    private val getBeerByCoroutineUseCase: GetBeerByCoroutineUseCase,
    private val savedStateHandle: SavedStateHandle,
) : BaseViewModel() {

    private val _ldProduct: MutableLiveData<BeerUI> = MutableLiveData()
    val ldProduct: LiveData<BeerUI> = _ldProduct

    private val productId = savedStateHandle.get<String>(ProductDestination.productArg)!!
    val productByCoroutine: StateFlow<ProductUiState> =
        getProductByCoroutine(productId)
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = ProductUiState.Loading
            )

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
    object Loading : ProductUiState
}