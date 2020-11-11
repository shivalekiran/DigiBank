package com.fixsolservices.digibank.ui.netbanking.accountoverview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fixsolservices.digibank.database.mainlogin.User
import com.fixsolservices.digibank.repository.NetbankingRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
class AccOverviewViewModel @Inject constructor(
    private val netBankingRepository: NetbankingRepository
) : ViewModel() {

    private val _logedInUsr = MutableLiveData<User>()

    val logedUser: LiveData<User>
        get() = _logedInUsr

    private val _text = MutableLiveData<String>().apply {
        value = "Account Overview"
    }
    val text: LiveData<String> = _text

    fun fetchDataFromDatabase(accountOverviewStateEvent: AccountOverviewStateEvent) {
        viewModelScope.launch {
            when (accountOverviewStateEvent) {
                AccountOverviewStateEvent.FetchUser -> {
                    netBankingRepository.getLogedInUser().onEach {
                        _logedInUsr.value = it
                    }.launchIn(viewModelScope)
                }
                AccountOverviewStateEvent.None -> {

                }
                AccountOverviewStateEvent.FetchMyTransactionList -> {

                }
            }
        }
    }
}

sealed class AccountOverviewStateEvent {
    object FetchUser : AccountOverviewStateEvent()
    object None : AccountOverviewStateEvent()
    object FetchMyTransactionList : AccountOverviewStateEvent()
}