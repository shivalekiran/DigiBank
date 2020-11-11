package com.fixsolservices.digibank.ui.login

import androidx.lifecycle.*
import com.fixsolservices.digibank.database.mainlogin.User
import com.fixsolservices.digibank.repository.LoginRepository
import com.fixsolservices.digibank.util.DataState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
class LoginViewModel @Inject constructor(
    private val loginRepository: LoginRepository
) : ViewModel() {
    private val _userData: MutableLiveData<DataState<User>> = MutableLiveData()

    val userData: LiveData<DataState<User>>
        get() = _userData


    fun authenticateUser(loginStateEvent: LoginStateEvent, email: String, password: String) {

        viewModelScope.launch {
            when (loginStateEvent) {
                LoginStateEvent.GetUser -> {

                    loginRepository.getUser().onEach {
                        _userData.value = it
                    }.launchIn(viewModelScope)
                }
                LoginStateEvent.None -> {

                }
            }
        }
    }

    init {
        println("initialize login view model")
    }
}


sealed class LoginStateEvent {
    object GetUser : LoginStateEvent()
    object None : LoginStateEvent()
}