package app.web.drjackycv.domain.products.usecase

import androidx.paging.PagingData
import app.web.drjackycv.domain.base.RecyclerItem
import app.web.drjackycv.domain.base.usecase.GeneralUseCase
import app.web.drjackycv.domain.products.repository.ProductsListRepository
import io.reactivex.Flowable
import javax.inject.Inject

class GetBeersListUseCase @Inject constructor(
    private val productsListRepository: ProductsListRepository
) : GeneralUseCase<Flowable<PagingData<RecyclerItem>>, GetBeersListParams> {

    override fun invoke(params: GetBeersListParams): Flowable<PagingData<RecyclerItem>> =
        productsListRepository.getBeersList(params.ids)

}

inline class GetBeersListParams(val ids: String)