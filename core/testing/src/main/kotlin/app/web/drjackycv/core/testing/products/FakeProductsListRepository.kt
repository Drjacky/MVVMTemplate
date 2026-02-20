package app.web.drjackycv.core.testing.products

import androidx.paging.PagingData
import app.web.drjackycv.core.domain.products.entity.Beer
import app.web.drjackycv.core.domain.products.repository.ProductsListRepository
import io.reactivex.rxjava3.core.Flowable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flow

class FakeProductsListRepository : ProductsListRepository {

    private val beersMap = mutableMapOf<String, Beer>()

    var shouldFail = false
    var errorToThrow: Throwable = RuntimeException("Test error")

    private val beersListFlow = MutableSharedFlow<PagingData<Beer>>(replay = 1)
    private var rxBeersList: PagingData<Beer>? = null

    fun addBeer(beer: Beer) {
        beersMap[beer.id.toString()] = beer
    }

    fun emitBeersList(pagingData: PagingData<Beer>) {
        rxBeersList = pagingData
        beersListFlow.tryEmit(pagingData)
    }

    fun reset() {
        beersMap.clear()
        shouldFail = false
        errorToThrow = RuntimeException("Test error")
        rxBeersList = null
    }

    override fun getBeersList(): Flowable<PagingData<Beer>> {
        if (shouldFail) return Flowable.error(errorToThrow)
        val data = rxBeersList ?: PagingData.empty()
        return Flowable.just(data)
    }

    override fun getBeer(id: String): Flowable<Beer> {
        if (shouldFail) return Flowable.error(errorToThrow)
        val beer =
            beersMap[id] ?: return Flowable.error(NoSuchElementException("Beer $id not found"))
        return Flowable.just(beer)
    }

    override fun getBeersListByCoroutine(): Flow<PagingData<Beer>> {
        if (shouldFail) return flow { throw errorToThrow }
        return beersListFlow
    }

    override fun getBeerByCoroutine(id: String): Flow<Beer> = flow {
        if (shouldFail) throw errorToThrow
        val beer = beersMap[id] ?: throw NoSuchElementException("Beer $id not found")
        emit(beer)
    }
}
