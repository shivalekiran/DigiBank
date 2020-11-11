package com.fixsolservices.digibank.di

import android.app.Application
import android.content.Context
import com.fixsolservices.digibank.DigiBankApplication
import com.fixsolservices.digibank.util.DigiBankSharedPreference
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivityBuildersModule::class,
        AppModule::class,
        ViewModelFactoryModule::class
    ]
)
interface ApplicationComponent : AndroidInjector<DigiBankApplication> {
    fun applicationSharedPreference(): DigiBankSharedPreference

    @Component.Builder
    interface Builder {
        fun build(): ApplicationComponent

        @BindsInstance
        fun contextBind(context: Context): Builder

        @BindsInstance
        fun application(application: Application): Builder
    }
}