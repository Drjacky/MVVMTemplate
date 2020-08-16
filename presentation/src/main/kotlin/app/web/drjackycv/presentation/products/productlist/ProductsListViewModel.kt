package app.web.drjackycv.presentation.products.productlist

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.rxjava2.cachedIn
import app.web.drjackycv.domain.base.RecyclerItem
import app.web.drjackycv.domain.products.usecase.GetBeersListParams
import app.web.drjackycv.domain.products.usecase.GetBeersListUseCase
import app.web.drjackycv.presentation.base.viewmodel.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo

class ProductsListViewModel @ViewModelInject constructor(
    private val getBeersUseCase: GetBeersListUseCase
) : BaseViewModel() {

    private val _ldProductsList: MutableLiveData<PagingData<RecyclerItem>> = MutableLiveData()
    val ldProductsList: LiveData<PagingData<RecyclerItem>> = _ldProductsList

    init {
        loading(true)
        getProducts("")
    }

    private fun getProducts(ids: String) {
        loading(false)
        getBeersUseCase(GetBeersListParams(ids = ids))
            .cachedIn(viewModelScope)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                _ldProductsList.value = it
            }.addTo(compositeDisposable)
    }

}