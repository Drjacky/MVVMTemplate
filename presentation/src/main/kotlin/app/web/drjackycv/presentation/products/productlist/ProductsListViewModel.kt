package app.web.drjackycv.presentation.products.productlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import androidx.paging.rxjava3.cachedIn
import app.web.drjackycv.domain.products.usecase.*
import app.web.drjackycv.presentation.base.viewmodel.BaseViewModel
import app.web.drjackycv.presentation.products.choose.ChoosePathType
import app.web.drjackycv.presentation.products.entity.BeerMapper
import app.web.drjackycv.presentation.products.entity.BeerUI
import autodispose2.autoDispose
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ProductsListViewModel @Inject constructor(
    private val getBeersUseCase: GetBeersListUseCase,
    private val getBeerUseCase: GetBeerUseCase,
    private val getBeersListByCoroutineUseCase: GetBeersListByCoroutineUseCase,
    private val getBeerByCoroutineUseCase: GetBeerByCoroutineUseCase,
    private val savedStateHandle: SavedStateHandle,
) : BaseViewModel() {

    private val _ldProductsList: MutableLiveData<PagingData<BeerUI>> = MutableLiveData()
    val ldProductsList: LiveData<PagingData<BeerUI>> = _ldProductsList

    private val _ldProduct: MutableLiveData<BeerUI> = MutableLiveData()
    val ldProduct: LiveData<BeerUI> = _ldProduct

    private val _productsListByCoroutine =
        MutableStateFlow<PagingData<BeerUI>>(PagingData.empty())
    val productsListByCoroutine: Flow<PagingData<BeerUI>> = _productsListByCoroutine


    init {
        val path =
            ChoosePathType.COROUTINE//savedStateHandle.get<ChoosePathType>(Screens.ProductsScreen.navArgumentKey) //TODO
        Timber.d("Which path: $path")
        getProductsBaseOnPath(path)
    }

    private fun getProductsByRxPath() {
        getBeersUseCase(Unit)
            .cachedIn(viewModelScope)
            .observeOn(AndroidSchedulers.mainThread())
            .autoDispose(this)
            .subscribe { pagingDataBeer ->
                _ldProductsList.value = pagingDataBeer
                    .map { beer ->
                        BeerMapper().mapLeftToRight(beer)
                    }
            }
    }

    fun getProduct(ids: String) {
        getBeerUseCase(GetBeerParams(ids = ids))
            .observeOn(AndroidSchedulers.mainThread())
            .autoDispose(this)
            .subscribe {
                _ldProduct.value = BeerMapper().mapLeftToRight(it)
            }
    }

    private fun getProductsByCoroutinePath() =
        getBeersListByCoroutineUseCase(Unit)
            .cachedIn(viewModelScope)

    fun getProductByCoroutine(ids: String) =
        getBeerByCoroutineUseCase(GetBeerByCoroutineParams(ids = ids))

    private fun getProductsBaseOnPath(path: ChoosePathType?) {
        when (path) {
            ChoosePathType.RX -> {
                getProductsByRxPath()
            }
            ChoosePathType.COROUTINE -> {
                viewModelScope.launch {
                    _productsListByCoroutine.value = getProductsByCoroutinePath().first()
                        .map { beer ->
                            BeerMapper().mapLeftToRight(beer)
                        }
                }
            }
            else -> getProductsByRxPath()
        }
    }

}