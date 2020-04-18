package app.web.drjackycv.mvvmtemplate.di.module

import androidx.lifecycle.ViewModelProvider
import app.web.drjackycv.mvvmtemplate.di.viewmodel.ViewModelFactory
import app.web.drjackycv.mvvmtemplate.di.viewmodel.ViewModelKey
import app.web.drjackycv.presentation.base.viewmodel.BaseViewModel
import app.web.drjackycv.presentation.products.productdetail.ProductDetailViewModel
import app.web.drjackycv.presentation.products.productlist.ProductsListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun factory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(ProductsListViewModel::class)
    abstract fun productsList(vm: ProductsListViewModel): BaseViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ProductDetailViewModel::class)
    abstract fun productDetail(vm: ProductDetailViewModel): BaseViewModel

}