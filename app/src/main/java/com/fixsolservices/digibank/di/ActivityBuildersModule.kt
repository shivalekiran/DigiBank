package com.fixsolservices.digibank.di

import com.fixsolservices.digibank.di.accoverview.AccOverviewViewModelsModule
import com.fixsolservices.digibank.di.accoverview.AccountOverviewBuilderModule
import com.fixsolservices.digibank.di.login.LoginModule
import com.fixsolservices.digibank.di.login.LoginViewModelModule
import com.fixsolservices.digibank.ui.NetBankingActivity
import com.fixsolservices.digibank.ui.SplashActivity
import com.fixsolservices.digibank.ui.forgotpass.ForgotPasswordActivity
import com.fixsolservices.digibank.ui.language.LanguageActivity
import com.fixsolservices.digibank.ui.login.LoginActivity
import com.fixsolservices.digibank.ui.main.MainActivity
import com.fixsolservices.digibank.ui.netbanking.NetbakingOverviewActivity
import com.fixsolservices.digibank.ui.netbankinglogin.NetBankingLoginActivity
import com.fixsolservices.digibank.ui.signup.SignUpActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(
        modules = [
            LoginViewModelModule::class,
            LoginModule::class
        ]
    )
    abstract fun injectAuthActivity(): LoginActivity

    @ContributesAndroidInjector(    )
    abstract fun injectMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun injectSplashActivity(): SplashActivity

    @ContributesAndroidInjector
    abstract fun injectLanguageActivity(): LanguageActivity

    @ContributesAndroidInjector
    abstract fun injectForgotPasswordActivity(): ForgotPasswordActivity

    @ContributesAndroidInjector
    abstract fun injectSignUpActivity(): SignUpActivity

    @ContributesAndroidInjector
    abstract fun injectNetBankingActivity(): NetBankingActivity

    @ContributesAndroidInjector(
        modules = [
            AccountOverviewBuilderModule::class
            , AccOverviewViewModelsModule::class
        ]
    )
    abstract fun injectNetbakingOverviewActivity(): NetbakingOverviewActivity

    @ContributesAndroidInjector
    abstract fun injectNetBankingLoginActivity(): NetBankingLoginActivity

}