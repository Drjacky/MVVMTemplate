package app.web.drjackycv.presentation.products.productlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations.map
import androidx.lifecycle.Transformations.switchMap
import androidx.paging.PagedList
import app.web.drjackycv.domain.base.Listing
import app.web.drjackycv.domain.base.RecyclerItem
import app.web.drjackycv.domain.products.usecase.GetBeersListParams
import app.web.drjackycv.domain.products.usecase.GetBeersListUseCase
import app.web.drjackycv.presentation.base.viewmodel.BaseViewModel
import javax.inject.Inject

class ProductsListViewModel @Inject constructor(
    private val getBeersListUseCase: GetBeersListUseCase
) : BaseViewModel() {

    private val _idsQuery = MutableLiveData<String>()
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

}