package com.fixsolservices.digibank.ui.netbanking.mpayee

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ManagePayeeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Manage Payee"
    }
    val text: LiveData<String> = _text
}