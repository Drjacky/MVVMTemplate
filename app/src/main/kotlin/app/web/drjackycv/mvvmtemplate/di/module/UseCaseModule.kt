package app.web.drjackycv.mvvmtemplate.di.module

import app.web.drjackycv.domain.products.repository.ProductsListRepository
import app.web.drjackycv.domain.products.usecase.GetBeersListByCoroutineUseCase
import app.web.drjackycv.domain.products.usecase.GetBeersListUseCase
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
    fun getBeersListByCoroutineUseCase(
        productsListRepository: ProductsListRepository,
    ): GetBeersListByCoroutineUseCase =
        GetBeersListByCoroutineUseCase(productsListRepository::getBeersListByCoroutine)

    @Provides
    @ViewModelScoped
    fun getBeersListUseCase(
        productsListRepository: ProductsListRepository,
    ): GetBeersListUseCase =
        GetBeersListUseCase(productsListRepository::getBeersList)

}