package com.fixsolservices.digibank.ui.forgotpass

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.fixsolservices.digibank.R
import com.fixsolservices.digibank.ui.SuccessMsgFragment
import com.fixsolservices.digibank.util.BaseActivity
import com.fixsolservices.digibank.util.hideKeyBoard
import kotlinx.android.synthetic.main.activity_forgot_password.*

class ForgotPasswordActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        back_button.setOnClickListener {
            onBackPressed()
        }
        send_recovery_email_btn.setOnClickListener {
            if (email_edittext.text.trim().isNotEmpty()) {
                it.hideKeyBoard(this)
                supportFragmentManager.beginTransaction().replace(
                    R.id.container,
                    SuccessMsgFragment.newInstance("Password reset link has been successfully sent to your email address")
                ).commit()
                Handler().postDelayed(Runnable { finish() }, 500)
            } else {
                email_edittext.requestFocus()
                email_error_text.visibility = View.VISIBLE
            }
        }
        email_edittext.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (email_edittext.text.trim().isNotEmpty()) {
                    email_error_text.visibility = View.GONE
                } else {
                    email_error_text.visibility = View.VISIBLE
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
    }

}