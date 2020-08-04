package app.web.drjackycv.mvvmtemplate.di.module

import app.web.drjackycv.data.products.remote.ProductsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit

@InstallIn(ApplicationComponent::class)
@Module
class ApiModule {

    @Provides
    fun products(retrofit: Retrofit): ProductsApi =
        retrofit.create(ProductsApi::class.java)

}