package com.fixsolservices.digibank

import com.fixsolservices.digibank.di.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

/**
 * App's application class,
 * we use dagger for dependency injection so we extend with @DaggerApplication
 * and inject Application using @AppComponent interface
 */

class DigiBankApplication : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerApplicationComponent.builder().application(this).contextBind(this).build()
    }
}