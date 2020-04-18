package app.web.drjackycv.mvvmtemplate.di.module

import android.app.Application
import android.content.Context
import android.content.res.Resources
import app.web.drjackycv.mvvmtemplate.application.MVVMTemplateApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideApplication(application: MVVMTemplateApplication): Application = application

    @Provides
    @Singleton
    fun provideContext(application: Application): Context = application

    @Provides
    @Singleton
    fun resources(application: Application): Resources = application.resources

}