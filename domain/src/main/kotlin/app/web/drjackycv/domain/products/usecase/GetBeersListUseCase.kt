package app.web.drjackycv.domain.products.usecase

import androidx.paging.PagingData
import app.web.drjackycv.domain.base.usecase.GeneralUseCase
import app.web.drjackycv.domain.products.entity.Beer
import app.web.drjackycv.domain.products.repository.ProductsListRepository
import io.reactivex.rxjava3.core.Flowable
import javax.inject.Inject

class GetBeersListUseCase @Inject constructor(
    private val productsListRepository: ProductsListRepository,
) : GeneralUseCase<Flowable<PagingData<Beer>>, Unit> {

    override fun invoke(params: Unit): Flowable<PagingData<Beer>> =
        productsListRepository.getBeersList()

}