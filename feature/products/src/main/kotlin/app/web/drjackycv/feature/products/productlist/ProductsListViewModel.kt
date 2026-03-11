package app.web.drjackycv.feature.products.productlist

import android.content.res.Resources
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import androidx.paging.rxjava3.cachedIn
import app.web.drjackycv.core.domain.base.Failure
import app.web.drjackycv.core.domain.products.entity.Product
import app.web.drjackycv.core.domain.products.usecase.GetProductsListByCoroutineUseCase
import app.web.drjackycv.core.domain.products.usecase.GetProductsListUseCase
import app.web.drjackycv.feature.products.base.adapter.RecyclerItem
import app.web.drjackycv.feature.products.choose.ChoosePathType
import app.web.drjackycv.feature.products.entity.mapIt
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

const val CHOOSE_PATH_TYPE = "choosePathType"

@HiltViewModel
class ProductsListViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsListUseCase,
    private val getProductsListByCoroutineUseCase: GetProductsListByCoroutineUseCase,
    private val resources: Resources,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val disposables = CompositeDisposable()

    private val _productsListByRx =
        MutableStateFlow<PagingData<RecyclerItem>>(PagingData.empty())
    val productsListByRx: StateFlow<PagingData<RecyclerItem>> = _productsListByRx

    private val _productsListByCoroutine =
        MutableStateFlow<PagingData<RecyclerItem>>(PagingData.empty())
    val productsListByCoroutine: Flow<PagingData<RecyclerItem>> = _productsListByCoroutine

    init {
        val path =
            savedStateHandle.get<ChoosePathType>(CHOOSE_PATH_TYPE) ?: ChoosePathType.COROUTINE
        Timber.d("Which path: $path")
        getProductsBaseOnPath(path)
    }

    private fun getProductsByRxPath() {
        getProductsUseCase()
            .cachedIn(viewModelScope)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { pagingDataProduct ->
                _productsListByRx.value = pagingDataProduct.map(Product::mapIt)
            }
            .addTo(disposables)
    }

    private fun getProductsByCoroutinePath() =
        getProductsListByCoroutineUseCase()
            .cachedIn(viewModelScope)

    private fun getProductsBaseOnPath(path: ChoosePathType) {
        when (path) {
            ChoosePathType.RX -> {
                getProductsByRxPath()
            }

            ChoosePathType.COROUTINE -> {
                viewModelScope.launch {
                    _productsListByCoroutine.value = getProductsByCoroutinePath().first()
                        .map(Product::mapIt)
                }
            }
        }
    }

    fun mapToFailure(throwable: Throwable, retryAction: () -> Unit): Failure {
        val failure = when (throwable) {
            is Failure.NoInternet -> {
                Failure.NoInternet(resources.getString(app.web.drjackycv.core.common.R.string.error_no_internet))
            }

            is Failure.Api -> {
                Failure.Api(throwable.msg)
            }

            is Failure.Timeout -> {
                Failure.Timeout(resources.getString(app.web.drjackycv.core.common.R.string.error_timeout))
            }

            is Failure.Unknown -> {
                Failure.Unknown(resources.getString(app.web.drjackycv.core.common.R.string.error_unknown))
            }

            else -> {
                Failure.Unknown(resources.getString(app.web.drjackycv.core.common.R.string.error_unknown))
            }
        }
        failure.retryAction = retryAction
        return failure
    }

    override fun onCleared() {
        disposables.clear()
        super.onCleared()
    }
}
