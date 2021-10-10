package app.web.drjackycv.domain.products.usecase

import app.web.drjackycv.domain.base.usecase.GeneralUseCase
import app.web.drjackycv.domain.products.entity.Beer
import app.web.drjackycv.domain.products.repository.ProductsListRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetBeerByCoroutineUseCase @Inject constructor(
    private val productsListRepository: ProductsListRepository,
) : GeneralUseCase<Flow<Beer>, GetBeerByCoroutineParams> {

    override fun invoke(params: GetBeerByCoroutineParams): Flow<Beer> =
        productsListRepository.getBeerByCoroutine(params.ids)

}

@JvmInline
value class GetBeerByCoroutineParams(val ids: String)