package app.web.drjackycv.mvvmtemplate.di.module

import app.web.drjackycv.data.products.datasource.ProductsPagingSource
import app.web.drjackycv.data.products.datasource.ProductsPagingSourceByCoroutine
import app.web.drjackycv.data.products.repository.ProductsListRepositoryImpl
import app.web.drjackycv.domain.products.repository.ProductsListRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class RepositoryModule {

    @Provides
    @ViewModelScoped
    fun productsList(
        pagingSource: ProductsPagingSource,
        pagingSourceByCoroutine: ProductsPagingSourceByCoroutine
    ): ProductsListRepository =
        ProductsListRepositoryImpl(pagingSource, pagingSourceByCoroutine)

}