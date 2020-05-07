package app.web.drjackycv.data.products.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import app.web.drjackycv.domain.base.RecyclerItem

class ProductsRemoteDataSourceFactory(
    private val ids: String,
    private val productsRemoteDataSource: ProductsRemoteDataSource
) : DataSource.Factory<Int, RecyclerItem>() {

    val source = MutableLiveData<ProductsRemotePagedDataSource>()

    override fun create(): DataSource<Int, RecyclerItem> {
        val source = ProductsRemotePagedDataSource(ids, productsRemoteDataSource)
        this.source.postValue(source)
        return source
    }

}