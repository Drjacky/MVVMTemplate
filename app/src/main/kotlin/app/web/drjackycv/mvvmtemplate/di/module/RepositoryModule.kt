package app.web.drjackycv.mvvmtemplate.di.module

import app.web.drjackycv.data.products.repository.ProductsRepositoryImpl
import app.web.drjackycv.domain.products.repository.ProductsRepository
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun products(productsRepositoryImpl: ProductsRepositoryImpl): ProductsRepository

}