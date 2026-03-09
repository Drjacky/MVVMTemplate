package app.web.drjackycv.core.domain.products.usecase

import app.web.drjackycv.core.domain.products.entity.Product
import io.reactivex.rxjava3.core.Flowable

fun interface GetProductUseCase : (String) -> Flowable<Product>
