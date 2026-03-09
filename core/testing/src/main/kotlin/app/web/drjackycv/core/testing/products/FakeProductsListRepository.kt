package app.web.drjackycv.core.testing.products

import androidx.paging.PagingData
import app.web.drjackycv.core.domain.products.entity.Product
import app.web.drjackycv.core.domain.products.repository.ProductsListRepository
import io.reactivex.rxjava3.core.Flowable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flow

class FakeProductsListRepository : ProductsListRepository {

    private val productsMap = mutableMapOf<String, Product>()

    var shouldFail = false
    var errorToThrow: Throwable = RuntimeException("Test error")

    private val productsListFlow = MutableSharedFlow<PagingData<Product>>(replay = 1)
    private var rxProductsList: PagingData<Product>? = null

    fun addProduct(product: Product) {
        productsMap[product.id.toString()] = product
    }

    fun emitProductsList(pagingData: PagingData<Product>) {
        rxProductsList = pagingData
        productsListFlow.tryEmit(pagingData)
    }

    fun reset() {
        productsMap.clear()
        shouldFail = false
        errorToThrow = RuntimeException("Test error")
        rxProductsList = null
    }

    override fun getProductsList(): Flowable<PagingData<Product>> {
        if (shouldFail) return Flowable.error(errorToThrow)
        val data = rxProductsList ?: PagingData.empty()
        return Flowable.just(data)
    }

    @Suppress("ReturnCount")
    override fun getProduct(id: String): Flowable<Product> {
        if (shouldFail) return Flowable.error(errorToThrow)
        val product =
            productsMap[id] ?: return Flowable.error(NoSuchElementException("Product $id not found"))
        return Flowable.just(product)
    }

    override fun getProductsListByCoroutine(): Flow<PagingData<Product>> {
        if (shouldFail) return flow { throw errorToThrow }
        return productsListFlow
    }

    override fun getProductByCoroutine(id: String): Flow<Product> = flow {
        if (shouldFail) throw errorToThrow
        val product = productsMap[id] ?: throw NoSuchElementException("Product $id not found")
        emit(product)
    }
}
