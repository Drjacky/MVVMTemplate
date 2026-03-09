package app.web.drjackycv.core.domain.products.usecase

import androidx.paging.PagingData
import app.web.drjackycv.core.domain.products.entity.Product
import kotlinx.coroutines.flow.Flow

fun interface GetProductsListByCoroutineUseCase : () -> Flow<PagingData<Product>>
