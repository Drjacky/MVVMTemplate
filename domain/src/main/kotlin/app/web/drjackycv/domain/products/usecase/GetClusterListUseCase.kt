package app.web.drjackycv.domain.products.usecase

import app.web.drjackycv.domain.base.usecase.SingleUseCase
import app.web.drjackycv.domain.products.entity.Clusters
import app.web.drjackycv.domain.products.repository.ProductsRepository
import io.reactivex.Single
import javax.inject.Inject

class GetClusterListUseCase @Inject constructor(
    private val productsRepository: ProductsRepository
) : SingleUseCase<Clusters, Unit> {

    override fun invoke(params: Unit): Single<Clusters> =
        productsRepository.getClusters()

}