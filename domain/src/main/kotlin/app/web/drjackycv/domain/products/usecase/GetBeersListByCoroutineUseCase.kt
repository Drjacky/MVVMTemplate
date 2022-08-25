package app.web.drjackycv.domain.products.usecase

import androidx.paging.PagingData
import app.web.drjackycv.domain.products.entity.Beer
import kotlinx.coroutines.flow.Flow

fun interface GetBeersListByCoroutineUseCase : (String) -> Flow<PagingData<Beer>>