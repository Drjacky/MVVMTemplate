package app.web.drjackycv.core.domain.products.usecase

import androidx.paging.PagingData
import app.web.drjackycv.core.domain.products.entity.Product
import io.reactivex.rxjava3.core.Flowable

fun interface GetProductsListUseCase : () -> Flowable<PagingData<Product>>
