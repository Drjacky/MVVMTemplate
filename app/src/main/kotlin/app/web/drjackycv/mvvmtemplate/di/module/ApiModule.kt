package app.web.drjackycv.mvvmtemplate.di.module

import app.web.drjackycv.data.products.remote.ProductsApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class ApiModule {

    @Provides
    fun products(retrofit: Retrofit): ProductsApi =
        retrofit.create(ProductsApi::class.java)

}