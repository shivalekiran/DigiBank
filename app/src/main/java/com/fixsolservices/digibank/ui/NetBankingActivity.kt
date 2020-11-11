package com.fixsolservices.digibank.ui

import android.content.Intent
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import androidx.appcompat.app.AppCompatActivity
import com.fixsolservices.digibank.R
import com.fixsolservices.digibank.ui.netbankinglogin.NetBankingLoginActivity
import com.fixsolservices.digibank.util.BaseActivity
import com.fixsolservices.digibank.util.Constants
import com.fixsolservices.digibank.util.Constants.DONE_LOG_OUT
import kotlinx.android.synthetic.main.activity_net_banking.*


class NetBankingActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_net_banking)
        back_button.setOnClickListener { onBackPressed() }
//        textView9.movementMethod = ScrollingMovementMethod()
        net_banking_start.setOnClickListener {
            val netLoginIntent = Intent(this, NetBankingLoginActivity::class.java)
            startActivityForResult(netLoginIntent,Constants.START_NETBANKING_ACTIVITY_CODE)
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == Constants.START_NETBANKING_ACTIVITY_CODE) {
            data?.hasExtra(DONE_LOG_OUT).let {
                it?.let {
                    finish()
                }
            }
        }
    }
}