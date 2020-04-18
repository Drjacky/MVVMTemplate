package app.web.drjackycv.presentation.products.productlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import app.web.drjackycv.domain.products.usecase.GetClusterListUseCase
import app.web.drjackycv.presentation.base.adapter.RecyclerItem
import app.web.drjackycv.presentation.base.viewmodel.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ProductsListViewModel @Inject constructor(
    private val getClusterListUseCase: GetClusterListUseCase
) : BaseViewModel() {

    private val _ldItemsList = MutableLiveData<List<RecyclerItem>>()
    val ldItemsList: LiveData<List<RecyclerItem>> = _ldItemsList

    init {
        getProductsList()
    }

    private fun getProductsList() {
        val finalList = mutableListOf<RecyclerItem>()
        getClusterListUseCase(Unit)
            .map { clusters ->
                ClustersMapper().mapToUI(clusters).clusters
            }
            .map {
                it.forEach { clusterUI ->
                    finalList.add(clusterUI)
                    finalList.addAll(clusterUI.items)
                }

                finalList.toList()
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { loading(true) }
            .doAfterTerminate { loading(false) }
            .subscribe({ productsUIList ->
                _ldItemsList.value = productsUIList
            }, { throwable ->
                handleFailure(throwable) { getProductsList() }
            }).addTo(compositeDisposable)
    }

}