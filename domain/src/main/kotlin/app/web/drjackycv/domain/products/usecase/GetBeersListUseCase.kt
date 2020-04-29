package app.web.drjackycv.domain.products.usecase

import app.web.drjackycv.domain.base.Listing
import app.web.drjackycv.domain.base.RecyclerItem
import app.web.drjackycv.domain.base.usecase.GeneralUseCase
import app.web.drjackycv.domain.products.repository.ProductsRepository
import javax.inject.Inject

class GetBeersListUseCase @Inject constructor(
    private val productsRepository: ProductsRepository
) : GeneralUseCase<Listing<RecyclerItem>, GetBeersListParams> {

    override fun invoke(params: GetBeersListParams): Listing<RecyclerItem> =
        productsRepository.getBeersById(params.ids)

}

inline class GetBeersListParams(val ids: String)