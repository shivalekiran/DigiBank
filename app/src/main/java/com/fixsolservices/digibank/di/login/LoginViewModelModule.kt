package com.fixsolservices.digibank.di.login

import androidx.lifecycle.ViewModel
import com.fixsolservices.digibank.di.ViewModelKey
import com.fixsolservices.digibank.ui.login.LoginViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class LoginViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindAuthViewModel(viewModel: LoginViewModel): ViewModel
}