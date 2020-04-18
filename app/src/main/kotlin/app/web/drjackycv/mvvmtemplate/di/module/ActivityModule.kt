package app.web.drjackycv.mvvmtemplate.di.module

import app.web.drjackycv.mvvmtemplate.di.scope.PerView
import app.web.drjackycv.presentation.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @PerView
    @ContributesAndroidInjector
    abstract fun main(): MainActivity

}