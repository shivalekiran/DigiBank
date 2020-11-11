package com.fixsolservices.digibank.di

import androidx.lifecycle.HasDefaultViewModelProviderFactory
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fixsolservices.digibank.viewmodels.ViewModelProviderFactory
import dagger.Binds
import dagger.Module

/**
 * Provide Factory for view models of activity
 */
@Module
abstract class ViewModelFactoryModule {
    @Binds
    abstract fun bindViewModelFactory(viewModelProviderFactory: ViewModelProviderFactory): ViewModelProvider.Factory
}