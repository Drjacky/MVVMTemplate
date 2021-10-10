package app.web.drjackycv.domain.products.usecase

import app.web.drjackycv.domain.base.usecase.GeneralUseCase
import app.web.drjackycv.domain.products.entity.Beer
import app.web.drjackycv.domain.products.repository.ProductsListRepository
import io.reactivex.rxjava3.core.Flowable
import javax.inject.Inject

class GetBeerUseCase @Inject constructor(
    private val productsListRepository: ProductsListRepository,
) : GeneralUseCase<Flowable<Beer>, GetBeerParams> {

    override fun invoke(params: GetBeerParams): Flowable<Beer> =
        productsListRepository.getBeer(params.ids)

}

@JvmInline
value class GetBeerParams(val ids: String)