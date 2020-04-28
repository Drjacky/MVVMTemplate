package app.web.drjackycv.domain.products.usecase

import app.web.drjackycv.domain.base.Listing
import app.web.drjackycv.domain.base.usecase.GeneralUseCase
import app.web.drjackycv.domain.products.entity.Beer
import app.web.drjackycv.domain.products.repository.ProductsRepository
import javax.inject.Inject

class GetBeersListUseCase @Inject constructor(
    private val productsRepository: ProductsRepository
) : GeneralUseCase<Listing<Beer>, GetBeersListParams> {

    override fun invoke(params: GetBeersListParams): Listing<Beer> =
        productsRepository.getBeersById(params.ids)

}

inline class GetBeersListParams(val ids: String)