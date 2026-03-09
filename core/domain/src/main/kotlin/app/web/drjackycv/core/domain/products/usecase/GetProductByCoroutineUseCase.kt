package app.web.drjackycv.core.domain.products.usecase

import app.web.drjackycv.core.domain.products.entity.Product
import kotlinx.coroutines.flow.Flow

fun interface GetProductByCoroutineUseCase : (String) -> Flow<Product>
