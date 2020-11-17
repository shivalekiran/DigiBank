package com.fixsolservices.digibank.di.accoverview

import com.fixsolservices.digibank.ui.netbanking.accountoverview.AccountOverviewFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector
import kotlinx.coroutines.ExperimentalCoroutinesApi


@Module
abstract class AccountOverviewBuilderModule {

    @ContributesAndroidInjector
    abstract fun contributeAccountOverviewFragment() : AccountOverviewFragment
}