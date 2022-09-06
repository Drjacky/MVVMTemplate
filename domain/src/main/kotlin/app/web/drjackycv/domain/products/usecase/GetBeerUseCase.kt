package app.web.drjackycv.domain.products.usecase

import app.web.drjackycv.domain.products.entity.Beer
import io.reactivex.rxjava3.core.Flowable

fun interface GetBeerUseCase : (String) -> Flowable<Beer>