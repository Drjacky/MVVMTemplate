package app.web.drjackycv.presentation.products.productlist

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations.map
import androidx.lifecycle.Transformations.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.PagedList
import androidx.paging.PagingData
import androidx.paging.rxjava2.cachedIn
import app.web.drjackycv.domain.base.Listing
import app.web.drjackycv.domain.base.RecyclerItem
import app.web.drjackycv.domain.products.usecase.GetBeersListParams
import app.web.drjackycv.domain.products.usecase.GetBeersListUseCase
import app.web.drjackycv.domain.products.usecase.GetBeersParams
import app.web.drjackycv.domain.products.usecase.GetBeersUseCase
import app.web.drjackycv.presentation.base.viewmodel.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo

class ProductsListViewModel @ViewModelInject constructor(
    private val getBeersListUseCase: GetBeersListUseCase,
    private val getBeersUseCase: GetBeersUseCase
) : BaseViewModel() {

    private val _ldProductsList: MutableLiveData<PagingData<RecyclerItem>> = MutableLiveData()
    val ldProductsList: LiveData<PagingData<RecyclerItem>> = _ldProductsList

    private val _idsQuery = MutableLiveData<String>()

    init {
        //getProductsList()
        loading(true)
        getProducts("")
    }

    private val result: LiveData<Listing<RecyclerItem>> = map(_idsQuery) {
        getBeersListUseCase(GetBeersListParams(ids = it))
    }
    val ldBeersList: LiveData<PagedList<RecyclerItem>> = switchMap(result) {
        loading(false)
        it.data
    }
    private val errorResult: LiveData<Throwable> = switchMap(result) {
        loading(false)
        it.error
    }
    val ldFailure = map(errorResult) {
        handleFailure(it) {
            getProductsList()
        }
    }

    fun getProductsList() {
        loading(true)
        _idsQuery.value = ""
    }

    private fun getProducts(ids: String) {
        loading(false)
        getBeersUseCase(GetBeersParams(ids = ids))
            .cachedIn(viewModelScope)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                _ldProductsList.value = it
            }.addTo(compositeDisposable)
    }

}