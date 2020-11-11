package com.fixsolservices.digibank.ui.netbanking.fund

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FundTransferViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Fund Transfer"
    }
    val text: LiveData<String> = _text
}