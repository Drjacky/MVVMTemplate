package app.web.drjackycv.domain.products.repository

import androidx.paging.PagingData
import app.web.drjackycv.domain.base.RecyclerItem
import io.reactivex.Flowable
import kotlinx.coroutines.flow.Flow

interface ProductsListRepository {

    fun getBeersList(ids: String): Flowable<PagingData<RecyclerItem>>

    fun getBeersListByCoroutine(ids: String): Flow<PagingData<RecyclerItem>>

}