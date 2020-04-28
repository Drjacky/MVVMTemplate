package app.web.drjackycv.domain.base

import androidx.lifecycle.LiveData
import androidx.paging.PagedList

data class Listing<T>(val pagedList: LiveData<PagedList<T>>)