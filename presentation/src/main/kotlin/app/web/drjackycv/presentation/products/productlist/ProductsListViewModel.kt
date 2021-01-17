package app.web.drjackycv.presentation.products.productlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.rxjava2.cachedIn
import app.web.drjackycv.domain.base.RecyclerItem
import app.web.drjackycv.domain.products.usecase.GetBeersListParams
import app.web.drjackycv.domain.products.usecase.GetBeersListUseCase
import app.web.drjackycv.presentation.base.viewmodel.BaseViewModel
import app.web.drjackycv.presentation.products.choose.ChoosePathType
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ProductsListViewModel @Inject constructor(
    private val getBeersUseCase: GetBeersListUseCase,
    private val savedStateHandle: SavedStateHandle
) : BaseViewModel() {

    private val _ldProductsList: MutableLiveData<PagingData<RecyclerItem>> = MutableLiveData()
    val ldProductsList: LiveData<PagingData<RecyclerItem>> = _ldProductsList

    init {
        val path = savedStateHandle.get<ChoosePathType>("choosePathType")
        Timber.d("Which path: $path")
        getProductsBaseOnPath("", path)
    }

    private fun getProductsByRxPath(ids: String) {
        getBeersUseCase(GetBeersListParams(ids = ids))
            .cachedIn(viewModelScope)
            .observeOn(AndroidSchedulers.mainThread())
            .autoDisposable()
            .subscribe {
                _ldProductsList.value = it
            }
    }

    private fun getProductsByCoroutinePath(ids: String) { //TODO
        getBeersUseCase(GetBeersListParams(ids = ids))
            .cachedIn(viewModelScope)
            .observeOn(AndroidSchedulers.mainThread())
            .autoDisposable()
            .subscribe {
                _ldProductsList.value = it
            }
    }

    fun getProductsBaseOnPath(ids: String, path: ChoosePathType?) {
        when (path) {
            ChoosePathType.RX -> getProductsByRxPath(ids)
            ChoosePathType.COROUTINE -> getProductsByCoroutinePath(ids)
            else -> getProductsByRxPath(ids)
        }
    }

}