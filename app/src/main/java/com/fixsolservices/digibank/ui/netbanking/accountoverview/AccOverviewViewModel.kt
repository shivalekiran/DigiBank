package com.fixsolservices.digibank.ui.netbanking.accountoverview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fixsolservices.digibank.database.bank.fund.FundTransfer
import com.fixsolservices.digibank.database.bank.transactions.MyTransaction
import com.fixsolservices.digibank.database.mainlogin.User
import com.fixsolservices.digibank.repository.NetbankingRepository
import com.fixsolservices.digibank.util.DataState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

class AccOverviewViewModel public @Inject constructor(
    val netBankingRepository: NetbankingRepository
) : ViewModel() {

    private val _logedInUsr = MutableLiveData<User>()

    private val _myTransactionList = MutableLiveData<DataState<List<MyTransaction>>>()
    val myTransaction: LiveData<DataState<List<MyTransaction>>>
        get() = _myTransactionList

    private val _fundList = MutableLiveData<DataState<List<FundTransfer>>>()
    val myFunds: LiveData<DataState<List<FundTransfer>>>
        get() = _fundList

    val logedUser: LiveData<User>
        get() = _logedInUsr

    private val _text = MutableLiveData<String>().apply {
        value = "Account Overview"
    }
    val text: LiveData<String> = _text

    fun fetchDataFromDatabase(stateEvent: AccountOverviewStateEvent) {
        viewModelScope.launch {
            when (stateEvent) {
                AccountOverviewStateEvent.FetchMyTransactionsList -> {
                    netBankingRepository.getMyTransactions().onEach { dataState ->
                        _myTransactionList.value = dataState
                    }.launchIn(viewModelScope)
                }
            }
        }
    }

    fun fetchFundTransferFromDB(stateEvent: AccountOverviewStateEvent) {
        viewModelScope.launch {
            when (stateEvent) {
                AccountOverviewStateEvent.FetchFundTransferList -> {
                    netBankingRepository.getFundTransferData().onEach { dataState ->
                        _fundList.value = dataState
                    }.launchIn(viewModelScope)
                }
            }
        }
    }
}

sealed class AccountOverviewStateEvent {
    object FetchUser : AccountOverviewStateEvent()
    object FetchMyTransactionsList : AccountOverviewStateEvent()
    object FetchFundTransferList : AccountOverviewStateEvent()
}