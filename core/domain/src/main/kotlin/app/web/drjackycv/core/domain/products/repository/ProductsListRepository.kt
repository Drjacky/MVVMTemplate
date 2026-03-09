package app.web.drjackycv.core.domain.products.repository

import androidx.paging.PagingData
import app.web.drjackycv.core.domain.products.entity.Product
import io.reactivex.rxjava3.core.Flowable
import kotlinx.coroutines.flow.Flow

interface ProductsListRepository {
    fun getProductsList(): Flowable<PagingData<Product>>
    fun getProduct(id: String): Flowable<Product>

    fun getProductsListByCoroutine(): Flow<PagingData<Product>>
    fun getProductByCoroutine(id: String): Flow<Product>
}
