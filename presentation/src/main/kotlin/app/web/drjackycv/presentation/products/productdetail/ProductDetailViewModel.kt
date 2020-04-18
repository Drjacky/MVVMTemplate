package app.web.drjackycv.presentation.products.productdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import app.web.drjackycv.domain.products.usecase.GetProductDetailParams
import app.web.drjackycv.domain.products.usecase.GetProductDetailUseCase
import app.web.drjackycv.presentation.base.viewmodel.BaseViewModel
import app.web.drjackycv.presentation.products.entity.ProductUI
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ProductDetailViewModel @Inject constructor(
    private val getProductDetailUseCase: GetProductDetailUseCase
) : BaseViewModel() {

    private val _ldProductDetail = MutableLiveData<ProductUI>()
    val ldProductDetail: LiveData<ProductUI> = _ldProductDetail

    fun getProductDetails(id: Int) {
        getProductDetailUseCase(GetProductDetailParams(productId = id))
            .map { ProductMapper().mapToUI(it) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { loading(true) }
            .doAfterTerminate { loading(false) }
            .subscribe({ productDetail ->
                _ldProductDetail.value = productDetail
            }, { throwable ->
                handleFailure(throwable) { getProductDetails(id) }
            }).addTo(compositeDisposable)
    }

}