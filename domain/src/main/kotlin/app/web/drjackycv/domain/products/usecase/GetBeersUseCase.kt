package app.web.drjackycv.domain.products.usecase

import androidx.paging.PagingData
import app.web.drjackycv.domain.base.RecyclerItem
import app.web.drjackycv.domain.base.usecase.GeneralUseCase
import app.web.drjackycv.domain.products.repository.ProductsListRepository
import io.reactivex.Flowable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetBeersUseCase @Inject constructor(
    private val productsListRepository: ProductsListRepository
) : GeneralUseCase<Flowable<PagingData<RecyclerItem>>, GetBeersParams> {

    override fun invoke(params: GetBeersParams): Flowable<PagingData<RecyclerItem>> =
        productsListRepository.getBeers(params.ids)

}

inline class GetBeersParams(val ids: String)