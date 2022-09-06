package app.web.drjackycv.domain.products.usecase

import app.web.drjackycv.domain.products.entity.Beer
import kotlinx.coroutines.flow.Flow

fun interface GetBeerByCoroutineUseCase : (String) -> Flow<Beer>