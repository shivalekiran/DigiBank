package com.fixsolservices.digibank.ui.login

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Patterns
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.fixsolservices.digibank.R
import com.fixsolservices.digibank.database.mainlogin.User
import com.fixsolservices.digibank.ui.forgotpass.ForgotPasswordActivity
import com.fixsolservices.digibank.ui.signup.SignUpActivity
import com.fixsolservices.digibank.util.BaseActivity
import com.fixsolservices.digibank.util.DataState
import com.fixsolservices.digibank.util.DigiBankSharedPreference
import com.fixsolservices.digibank.util.hideKeyBoard
import com.fixsolservices.digibank.viewmodels.ViewModelProviderFactory
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

class LoginActivity : BaseActivity() {

    @ExperimentalCoroutinesApi
    private lateinit var loginViewModel: LoginViewModel

    @Inject
    lateinit var appPreference: DigiBankSharedPreference

    @Inject
    lateinit var factory: ViewModelProviderFactory

    @ExperimentalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginViewModel =
            ViewModelProvider(this, factory).get(LoginViewModel::class.java)

        login.setOnClickListener {
            it.hideKeyBoard(this)
            loginUser()
        }
        subscribeObserver()
        sing_up.setOnClickListener {
            openSignUpActivity()
        }
        forgot_password_text.setOnClickListener {
            openForgotPasswordAct()
        }

        email_edittext.addTextChangedListener(EditTextWatcher(email_edittext))
        password_edittext.addTextChangedListener(EditTextWatcher(password_edittext))
    }

    fun openForgotPasswordAct() {
        val forgotIntent = Intent(this, ForgotPasswordActivity::class.java)
        startActivity(forgotIntent)
    }

    private fun openSignUpActivity() {
        val intentSignUp = Intent(this, SignUpActivity::class.java)
        startActivity(intentSignUp)
    }

    @ExperimentalCoroutinesApi
    private fun subscribeObserver() {
        loginViewModel.userData.observe(this, Observer { dataState ->

            when (dataState) {
                is DataState.Success -> {
                    displayProgressBar(false)
                    logInSuccess(dataState.data)
                }
                is DataState.Error -> {
                    displayProgressBar(false)
                    Toast.makeText(this, "Please enter valid email/password", Toast.LENGTH_SHORT)
                        .show()
                }
                DataState.Loading -> {
                    displayProgressBar(true)
                }
            }
        })
    }

    private fun logInSuccess(data: User) {
        email_edittext.setText(data.email)
        appPreference.firstRun = true
        setResult(Activity.RESULT_OK)
        finish()
    }

    private fun displayProgressBar(isDisplay: Boolean) {
        login_progressbar.visibility = if (isDisplay) View.VISIBLE else View.GONE
    }

    @ExperimentalCoroutinesApi
    private fun loginUser() {

        if (validateInputs(email_edittext) && validateInputs(password_edittext)) {
            login_progressbar.visibility = View.VISIBLE
            loginViewModel.authenticateUser(
                LoginStateEvent.GetUser,
                email_edittext.text.toString().trim(),
                password_edittext.text.toString().trim()
            )
        }
    }

    private fun validateInputs(editText: EditText): Boolean {
        when (editText.id) {
            R.id.email_edittext -> {
                return if (!TextUtils.isEmpty(editText.text.toString().trim())
                    && Patterns.EMAIL_ADDRESS.matcher(editText.text.toString().trim()).matches()
                ) {
                    true
                } else {
                    setErrorMsg(editText)
                    false
                }
            }
            R.id.password_edittext -> {
                return if (TextUtils.isEmpty(editText.text.toString().trim())) {
                    setErrorMsg(
                        editText
                    )
                    false
                } else {
                    true
                }
            }
            else -> return false
        }
    }

    private fun setErrorMsg(editText: TextView) {
        when (editText.id) {
            R.id.email_edittext -> {
                email_error_text.visibility =
                    if (editText.text.toString().trim().isNotEmpty()) View.GONE else View.VISIBLE
            }
            R.id.password_edittext -> {
                password_error_text.visibility =
                    if (editText.text.toString().trim().isNotEmpty()) View.GONE else View.VISIBLE

            }
        }
        editText.requestFocus()
    }

    open inner
    class EditTextWatcher(private val editText: EditText) : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            setErrorMsg(editText)
        }

        override fun afterTextChanged(s: Editable?) {
        }
    }

}