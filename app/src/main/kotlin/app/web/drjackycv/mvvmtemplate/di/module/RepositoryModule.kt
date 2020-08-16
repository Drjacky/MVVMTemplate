package app.web.drjackycv.mvvmtemplate.di.module

import app.web.drjackycv.data.products.datasource.ProductsPagingSource
import app.web.drjackycv.data.products.repository.ProductsListRepositoryImpl
import app.web.drjackycv.domain.products.repository.ProductsListRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@InstallIn(ApplicationComponent::class)
@Module
class RepositoryModule {

    @Provides
    fun productsList(pagingSource: ProductsPagingSource): ProductsListRepository =
        ProductsListRepositoryImpl(pagingSource)

}