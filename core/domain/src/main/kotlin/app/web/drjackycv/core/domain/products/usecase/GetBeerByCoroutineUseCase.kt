package app.web.drjackycv.core.domain.products.usecase

import app.web.drjackycv.core.domain.products.entity.Beer
import kotlinx.coroutines.flow.Flow

fun interface GetBeerByCoroutineUseCase : (String) -> Flow<Beer>