package app.web.drjackycv.domain.products.usecase

import androidx.paging.PagingData
import app.web.drjackycv.domain.products.entity.Beer
import io.reactivex.rxjava3.core.Flowable

fun interface GetBeersListUseCase : (String) -> Flowable<PagingData<Beer>>