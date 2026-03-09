package app.web.drjackycv.mvvmtemplate.di.module

import app.web.drjackycv.core.domain.products.repository.ProductsListRepository
import app.web.drjackycv.core.domain.products.usecase.GetProductByCoroutineUseCase
import app.web.drjackycv.core.domain.products.usecase.GetProductUseCase
import app.web.drjackycv.core.domain.products.usecase.GetProductsListByCoroutineUseCase
import app.web.drjackycv.core.domain.products.usecase.GetProductsListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class UseCaseModule {

    @Provides
    @ViewModelScoped
    fun getProductsListByCoroutineUseCase(
        productsListRepository: ProductsListRepository,
    ): GetProductsListByCoroutineUseCase =
        GetProductsListByCoroutineUseCase(productsListRepository::getProductsListByCoroutine)

    @Provides
    @ViewModelScoped
    fun getProductByCoroutineUseCase(
        productsListRepository: ProductsListRepository,
    ): GetProductByCoroutineUseCase =
        GetProductByCoroutineUseCase(productsListRepository::getProductByCoroutine)

    @Provides
    @ViewModelScoped
    fun getProductsListUseCase(
        productsListRepository: ProductsListRepository,
    ): GetProductsListUseCase =
        GetProductsListUseCase(productsListRepository::getProductsList)

    @Provides
    @ViewModelScoped
    fun getProductUseCase(
        productsListRepository: ProductsListRepository,
    ): GetProductUseCase =
        GetProductUseCase(productsListRepository::getProduct)
}
