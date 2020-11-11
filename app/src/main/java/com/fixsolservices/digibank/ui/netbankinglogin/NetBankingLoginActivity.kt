package com.fixsolservices.digibank.ui.netbankinglogin

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import com.fixsolservices.digibank.R
import com.fixsolservices.digibank.ui.netbanking.NetbakingOverviewActivity
import com.fixsolservices.digibank.util.*
import com.fixsolservices.digibank.util.Constants.DONE_LOG_OUT
import com.fixsolservices.digibank.util.Constants.START_NETBANKING_ACTIVITY_CODE
import kotlinx.android.synthetic.main.activity_net_banking_login.*
import javax.inject.Inject

class NetBankingLoginActivity : BaseActivity(), View.OnFocusChangeListener {

    @Inject
    lateinit var appSharedPreference: DigiBankSharedPreference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_net_banking_login)
        back_button.setOnClickListener {
            onBackPressed()
        }
        netbanking_id.onFocusChangeListener = this
        password_edittext.onFocusChangeListener = this

        netbanking_id.addTextChangedListener {
            customer_id_info_text.setTextColor(R.color.appBlueColor.getColor(this))
        }
        password_edittext.addTextChangedListener {
            internet_bank_pass_error_text.setTextColor(R.color.appBlueColor.getColor(this))
        }

        net_banking_login.setOnClickListener {
            if (validateDetails()) {
                appSharedPreference.netBankingUserID = netbanking_id.text.trim().toString()
                appSharedPreference.netBankingUserPass = password_edittext.text.trim().toString()
                val startNetBankingActivity = Intent(this, NetbakingOverviewActivity::class.java)
                startActivityForResult(startNetBankingActivity, START_NETBANKING_ACTIVITY_CODE)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == START_NETBANKING_ACTIVITY_CODE) {
            data?.hasExtra(DONE_LOG_OUT).let {
                it?.let {
                    val result = Intent();
                    result.putExtra(DONE_LOG_OUT, true)
                    setResult(Constants.START_NETBANKING_ACTIVITY_CODE, result)
                    finish()
                }
            }
        }
    }

    private fun validateDetails(): Boolean {
        val id = netbanking_id.text.trim().toString()

        if (id.isNotEmpty() && id.length == 6) {
            if (password_edittext.text.trim().toString()
                    .isNotEmpty() && password_edittext.text.trim().length >= 5
            ) {
                return true
            } else {
                password_edittext.requestFocus()
                internet_bank_pass_error_text.setTextColor(R.color.error_text.getColor(this))
            }
        } else {
            netbanking_id.requestFocus()
            customer_id_info_text.setTextColor(R.color.error_text.getColor(this))
        }

        return false
    }

    override fun onFocusChange(view: View?, hasFocus: Boolean) {
        when (view?.id) {
            R.id.netbanking_id -> {
                customer_id_info_text.setTextColor(R.color.appBlueColor.getColor(this))
                if (hasFocus) {
                    customer_id_info_text.showView()
                } else {
                    customer_id_info_text.hideView()
                }
            }
            R.id.password_edittext -> {
                internet_bank_pass_error_text.setTextColor(R.color.appBlueColor.getColor(this))
                if (hasFocus) {
                    internet_bank_pass_error_text.showView()
                } else {
                    internet_bank_pass_error_text.hideView()

                }
            }
        }
    }

}
