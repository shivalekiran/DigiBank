package com.fixsolservices.digibank.ui.signup

import android.app.Activity
import android.os.Bundle
import android.os.Handler
import android.text.TextUtils
import android.util.Patterns
import android.view.View
import android.view.inputmethod.InputMethod
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.fixsolservices.digibank.R
import com.fixsolservices.digibank.ui.SuccessMsgFragment
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_sign_up.*
import kotlinx.android.synthetic.main.male_female_layout.*


class SignUpActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        gender_spinner.setOnClickListener {
            maleFemaleLayoutVisibility()
        }
        back_button.setOnClickListener {
            onBackPressed()
        }
        submit_form.setOnClickListener {

            if (validateForm()) {
                val inputMethodManager =
                    getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(it.windowToken, 0)
                supportFragmentManager.beginTransaction().replace(
                    R.id.container,
                    SuccessMsgFragment.newInstance("Congratulation signup successful \nnow you can login")
                )
                    .commit()
                Handler().postDelayed(Runnable { finish() }, 500)
            }
        }
    }

    private fun validateForm(): Boolean {
        return validateEditText(first_name_edittext) &&
                validateEditText(last_name_edittext) &&
                validateEditText(sign_email_addresss) &&
                validateEditText(sign_mobile_no) &&
                validateEditText(edit_user_name) &&
                validateEditText(edit_password) &&
                validateEditText(edit_confirm_password) && dosePasswordsMatch()

    }

    private fun dosePasswordsMatch(): Boolean {
        if (edit_password.text.trim() == edit_confirm_password.text.trim()) return true
        else {
            requestViewFocus(edit_password)
            showView(password_error_text)
            password_error_text.text = "Enter Password and Confirm password same."
            return false
        }
    }

    private fun validateEditText(editText: EditText): Boolean {
        if (editText.id == R.id.sign_email_addresss) {
            if (editText.text.trim()
                    .isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(editText.text.trim()).matches()
            ) return true
            else {
                editText.requestFocus()
                showView(email_error_text)
                return false
            }

        } else if (editText.text.trim().isNotEmpty()) {
            return true
        } else {
            requestViewFocus(editText)
            when (editText.id) {
                R.id.first_name_edittext -> {
                    showView(first_name_error_text)
                }
                R.id.last_name_edittext -> {
                    showView(last_error_text)
                }
                R.id.sign_email_addresss -> {
                    showView(email_error_text)
                }
                R.id.sign_mobile_no -> {
                    showView(mobile_error_text)
                }
                R.id.edit_user_name -> {
                    showView(username_error_text)
                }
                R.id.edit_password -> {
                    showView(password_error_text)
                }
                R.id.edit_confirm_password -> {
                    showView(confirm_password_error_text)
                }
            }
            return false
        }
    }

    private fun requestViewFocus(editText: EditText) {
        editText.requestFocus()
    }

    private fun showView(textView: TextView) {
        textView.visibility = View.VISIBLE
    }

    //callback for on click male/female text
    fun onGenderViewClick(view: View) {
        when (view.id) {
            R.id.text_male -> {
                gender_spinner.setText("Male")
            }
            R.id.text_female -> {
                gender_spinner.setText("Female")
            }
        }
        maleFemaleLayoutVisibility()
    }

    fun maleFemaleLayoutVisibility() {
        if (male_female_line_constraintlayout_layout.visibility == View.VISIBLE) {
            male_female_line_constraintlayout_layout.visibility = View.GONE
            up_down_image.setImageDrawable(
//            setCompoundDrawablesRelativeWithIntrinsicBounds(
//                null,
//                null,
//                null,
                ContextCompat.getDrawable(this, R.drawable.ic_down)
            )

        } else {
            male_female_line_constraintlayout_layout.visibility = View.VISIBLE
            up_down_image.setImageDrawable(
//                .setCompoundDrawablesRelativeWithIntrinsicBounds(
//                null,
//                null,
//                null,
                ContextCompat.getDrawable(this, R.drawable.ic_up)
            )
        }
    }
}