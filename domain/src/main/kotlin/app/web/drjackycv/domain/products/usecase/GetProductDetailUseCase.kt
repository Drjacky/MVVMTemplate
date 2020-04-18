package app.web.drjackycv.domain.products.usecase

import app.web.drjackycv.domain.base.usecase.SingleUseCase
import app.web.drjackycv.domain.products.entity.Product
import app.web.drjackycv.domain.products.repository.ProductsRepository
import io.reactivex.Single
import javax.inject.Inject

class GetProductDetailUseCase @Inject constructor(
    private val productsRepository: ProductsRepository
) : SingleUseCase<Product, GetProductDetailParams> {

    override fun invoke(params: GetProductDetailParams): Single<Product> =
        productsRepository.getProductDetail(params.productId)
}

inline class GetProductDetailParams(val productId: Int)