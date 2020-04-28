package app.web.drjackycv.presentation.products.productlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations.map
import androidx.lifecycle.Transformations.switchMap
import androidx.paging.PagedList
import app.web.drjackycv.domain.base.Listing
import app.web.drjackycv.domain.products.entity.Beer
import app.web.drjackycv.domain.products.usecase.GetBeersListParams
import app.web.drjackycv.domain.products.usecase.GetBeersListUseCase
import app.web.drjackycv.presentation.base.viewmodel.BaseViewModel
import javax.inject.Inject

class ProductsListViewModel @Inject constructor(
    private val getBeersListUseCase: GetBeersListUseCase
) : BaseViewModel() {

    private val _idsQuery = MutableLiveData<String>()
    private val _ldBeersList: LiveData<Listing<Beer>> = map(_idsQuery) {
        getBeersListUseCase(GetBeersListParams(ids = it))
    }
    val ldBeersList: LiveData<PagedList<Beer>> = switchMap(_ldBeersList) {
        loading(false)
        it.pagedList
    }

    fun getProductsList() {
        loading(true)
        _idsQuery.value = ""
    }

}