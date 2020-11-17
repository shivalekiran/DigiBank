package com.fixsolservices.digibank.di.accoverview

import androidx.lifecycle.ViewModel
import com.fixsolservices.digibank.di.ViewModelKey
import com.fixsolservices.digibank.ui.netbanking.accountoverview.AccOverviewViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class AccOverviewViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(AccOverviewViewModel::class)
    abstract fun bindAccOverviewViewModel(accOverviewViewModel: AccOverviewViewModel): ViewModel
}