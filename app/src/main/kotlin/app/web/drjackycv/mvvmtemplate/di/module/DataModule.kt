package app.web.drjackycv.mvvmtemplate.di.module

import dagger.Module

@Module(
    includes = [
        NetModule::class,
        RepositoryModule::class,
        ApiModule::class
    ]
)
class DataModule