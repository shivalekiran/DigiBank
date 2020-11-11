package com.fixsolservices.digibank.di.main

import android.app.Application
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.fixsolservices.digibank.R
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton


@Module
object MainModule{
    @Provides
    fun provideAppLogoDrawable(application: Application): Drawable {
        return ContextCompat.getDrawable(application, R.drawable.office_netbanking)!!
    }
}
