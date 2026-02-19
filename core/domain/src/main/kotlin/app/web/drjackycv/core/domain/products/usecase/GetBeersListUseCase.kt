package app.web.drjackycv.core.domain.products.usecase

import androidx.paging.PagingData
import app.web.drjackycv.core.domain.products.entity.Beer
import io.reactivex.rxjava3.core.Flowable

fun interface GetBeersListUseCase : () -> Flowable<PagingData<Beer>>