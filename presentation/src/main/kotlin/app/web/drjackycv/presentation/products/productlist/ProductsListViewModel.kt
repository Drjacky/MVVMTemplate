package app.web.drjackycv.presentation.products.productlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import androidx.paging.rxjava3.cachedIn
import app.web.drjackycv.domain.products.entity.Beer
import app.web.drjackycv.domain.products.usecase.GetBeersListByCoroutineUseCase
import app.web.drjackycv.domain.products.usecase.GetBeersListUseCase
import app.web.drjackycv.presentation.base.viewmodel.BaseViewModel
import app.web.drjackycv.presentation.products.choose.ChoosePathType
import app.web.drjackycv.presentation.products.entity.BeerUI
import app.web.drjackycv.presentation.products.entity.mapIt
import autodispose2.autoDispose
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import kotlinx.coroutines.flow.*
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ProductsListViewModel @Inject constructor(
    private val getBeersUseCase: GetBeersListUseCase,
    private val getBeersListByCoroutineUseCase: GetBeersListByCoroutineUseCase,
    private val savedStateHandle: SavedStateHandle,
) : BaseViewModel() {

    private val _ldProductsList: MutableLiveData<PagingData<BeerUI>> = MutableLiveData()
    val ldProductsList: LiveData<PagingData<BeerUI>> = _ldProductsList

    private val _productsListByCoroutine = MutableStateFlow(ProductsListUIState(isLoading = true))
    val productsListByCoroutine: StateFlow<ProductsListUIState> = getProductsByCoroutinePath()

    init {
        val path =
            ChoosePathType.COROUTINE//savedStateHandle.get<ChoosePathType>(Screens.ProductsScreen.navArgumentKey) //TODO
        Timber.d("Which path: $path")
        //getProductsBaseOnPath(path)
    }

    private fun getProductsByRxPath() {
        getBeersUseCase()
            .cachedIn(viewModelScope)
            .observeOn(AndroidSchedulers.mainThread())
            .autoDispose(this)
            .subscribe { pagingDataBeer ->
                _ldProductsList.value = pagingDataBeer
                    .map(Beer::mapIt)
            }
    }

    private fun getProductsByCoroutinePath() =
        getBeersListByCoroutineUseCase()
            .cachedIn(viewModelScope)
            .map {
                it.map(Beer::mapIt)
            }
            .map {
                ProductsListUIState(it)
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = ProductsListUIState(isLoading = true)
            )

    private fun getProductsBaseOnPath(path: ChoosePathType?) {
        when (path) {
            ChoosePathType.RX -> {
                getProductsByRxPath()
            }
            ChoosePathType.COROUTINE -> {
                getProductsByCoroutinePath()
            }
            else -> getProductsByCoroutinePath()
        }
    }

}

data class ProductsListUIState(
    val items: PagingData<BeerUI> = PagingData.empty(),
    val isLoading: Boolean = false,
    val userMessage: Int? = null
)