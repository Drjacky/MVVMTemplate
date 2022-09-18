package app.web.drjackycv.presentation.products.productdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import app.web.drjackycv.domain.products.entity.Beer
import app.web.drjackycv.domain.products.usecase.GetBeerByCoroutineUseCase
import app.web.drjackycv.domain.products.usecase.GetBeerUseCase
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
    val productByCoroutine: StateFlow<ProductUIState> = getProductByCoroutine(productId)

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
            .map(Beer::mapIt)
            .map {
                ProductUIState(it)
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = ProductUIState(isLoading = true)
            )

}

data class ProductUIState(
    val item: BeerUI? = null,
    val isLoading: Boolean = false,
    val userMessage: Int? = null
)