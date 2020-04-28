package app.web.drjackycv.mvvmtemplate.di.module

import app.web.drjackycv.data.products.datasource.ProductsRemoteDataSource
import app.web.drjackycv.data.products.repository.ProductsRepositoryImpl
import app.web.drjackycv.domain.products.repository.ProductsRepository
import dagger.Module
import dagger.Provides
import java.util.concurrent.Executors

@Module
class RepositoryModule {

    @Suppress("PrivatePropertyName")
    private val NETWORK_IO = Executors.newFixedThreadPool(5)

    @Provides
    fun products(productsRemoteDataSource: ProductsRemoteDataSource): ProductsRepository =
        ProductsRepositoryImpl(productsRemoteDataSource, NETWORK_IO)

}