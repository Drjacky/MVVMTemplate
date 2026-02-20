package app.web.drjackycv.core.domain.products.repository

import androidx.paging.PagingData
import app.web.drjackycv.core.domain.products.entity.Beer
import io.reactivex.rxjava3.core.Flowable
import kotlinx.coroutines.flow.Flow

interface ProductsListRepository {
    fun getBeersList(): Flowable<PagingData<Beer>>
    fun getBeer(id: String): Flowable<Beer>

    fun getBeersListByCoroutine(): Flow<PagingData<Beer>>
    fun getBeerByCoroutine(id: String): Flow<Beer>
}
