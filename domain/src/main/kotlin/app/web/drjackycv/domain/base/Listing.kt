package app.web.drjackycv.domain.base

import androidx.lifecycle.LiveData
import androidx.paging.PagedList

data class Listing<T>(
    val data: LiveData<PagedList<T>>,
    val error: LiveData<Throwable>
)