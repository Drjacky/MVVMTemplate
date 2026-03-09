package app.web.drjackycv.core.domain.products.usecase

import app.web.drjackycv.core.domain.products.entity.Beer
import io.reactivex.rxjava3.core.Flowable

fun interface GetBeerUseCase : (String) -> Flowable<Beer>
