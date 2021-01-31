package app.web.drjackycv.domain.products.usecase

import androidx.paging.PagingData
import app.web.drjackycv.domain.base.RecyclerItem
import app.web.drjackycv.domain.base.usecase.GeneralUseCase
import app.web.drjackycv.domain.products.repository.ProductsListRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetBeersListByCoroutineUseCase @Inject constructor(
    private val productsListRepository: ProductsListRepository
) : GeneralUseCase<Flow<PagingData<RecyclerItem>>, GetBeersListByCoroutineParams> {

    override fun invoke(params: GetBeersListByCoroutineParams): Flow<PagingData<RecyclerItem>> =
        productsListRepository.getBeersListByCoroutine(params.ids)

}

inline class GetBeersListByCoroutineParams(val ids: String)