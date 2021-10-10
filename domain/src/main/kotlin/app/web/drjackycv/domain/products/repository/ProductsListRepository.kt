package app.web.drjackycv.domain.products.repository

import androidx.paging.PagingData
import app.web.drjackycv.domain.products.entity.Beer
import io.reactivex.rxjava3.core.Flowable
import kotlinx.coroutines.flow.Flow

interface ProductsListRepository {

    fun getBeersList(): Flowable<PagingData<Beer>>
    fun getBeer(ids: String): Flowable<Beer>

    fun getBeersListByCoroutine(): Flow<PagingData<Beer>>
    fun getBeerByCoroutine(ids: String): Flow<Beer>

}