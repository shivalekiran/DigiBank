package com.fixsolservices.digibank.di.main

import androidx.lifecycle.ViewModel
import com.fixsolservices.digibank.di.ViewModelKey
import com.fixsolservices.digibank.ui.main.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindAuthViewModel(viewModel: MainViewModel): ViewModel
}