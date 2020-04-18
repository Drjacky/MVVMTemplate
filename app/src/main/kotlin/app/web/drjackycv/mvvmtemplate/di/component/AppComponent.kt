package app.web.drjackycv.mvvmtemplate.di.component

import app.web.drjackycv.mvvmtemplate.application.MVVMTemplateApplication
import app.web.drjackycv.mvvmtemplate.di.module.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        DataModule::class,
        ViewModelModule::class,
        ActivityModule::class,
        FragmentModule::class
    ]
)
interface AppComponent : AndroidInjector<MVVMTemplateApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(app: MVVMTemplateApplication): Builder

        fun build(): AppComponent

    }

}