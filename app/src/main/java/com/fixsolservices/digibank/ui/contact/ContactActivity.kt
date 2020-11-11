package com.fixsolservices.digibank.ui.contact

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import com.fixsolservices.digibank.R
import com.fixsolservices.digibank.ui.SuccessMsgFragment
import com.fixsolservices.digibank.util.hideKeyBoard
import com.fixsolservices.digibank.util.hideView
import com.fixsolservices.digibank.util.showView
import com.fixsolservices.digibank.util.validateEmail
import kotlinx.android.synthetic.main.activity_contact.*

/**@author Kiran Shivale
 * For Sending enquiry request
 */
class ContactActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact)
        initView()
    }

    /**
     * Initializing UI and setting call back for buttons
     */
    private fun initView() {
        send_enquiry.setOnClickListener {
            if (isFormValidate()) {
                it.hideKeyBoard(this)
                //showing success fragment on api response successful
                supportFragmentManager.beginTransaction()
                    .replace(
                        R.id.container,
                        SuccessMsgFragment.newInstance("Password reset link has been successfully sent to your email address")
                    )
                    .commit()
                Handler().postDelayed(Runnable { finish() }, 500)
            }
        }
        back_button.setOnClickListener {
            onBackPressed()
        }

        full_name_editext.addTextChangedListener(MyTextWatcher(full_name_editext))
        email_edittext.addTextChangedListener(MyTextWatcher(email_edittext))
        comment_edittext.addTextChangedListener(MyTextWatcher(comment_edittext))
    }

    /**
     * validating form
     */
    private fun isFormValidate(): Boolean {
        if (full_name_editext.text.trim().isNotEmpty()) {
            if (email_edittext.text.trim().toString().validateEmail()) {
                if (comment_edittext.text.trim().isNotEmpty()) {
                    return true
                } else {
                    comment_edittext.requestFocus()
                    comment_error_text.showView()
                }
            } else {
                email_edittext.requestFocus()
                email_error_text.showView()
            }
        } else {
            full_name_editext.requestFocus()
            full_name_error_text.showView()
        }
        return false
    }

    /**
     * watching for text change in all edit text and showing or hiding error text
     */
    inner class MyTextWatcher(private val editText: EditText) : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            when (editText.id) {
                R.id.full_name_editext -> {
                    if (editText.text.trim().isNotEmpty()) {
                        full_name_error_text.hideView()
                    } else {
                        full_name_error_text.showView()
                    }
                }
                R.id.email_edittext -> {
                    if (editText.text.trim().isNotEmpty()) {
                        email_error_text.hideView()
                    } else {
                        email_error_text.showView()
                    }
                }
                R.id.comment_edittext -> {

                    if (editText.text.trim().isNotEmpty()) {
                        comment_error_text.hideView()
                    } else {
                        comment_error_text.showView()
                    }
                }
            }
        }

        override fun afterTextChanged(s: Editable?) {

        }

    }
}