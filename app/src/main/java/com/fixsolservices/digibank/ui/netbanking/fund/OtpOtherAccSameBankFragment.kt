package com.fixsolservices.digibank.ui.netbanking.fund

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.fixsolservices.digibank.R
import kotlinx.android.synthetic.main.fragment_otp_other_acc_same_bank.*

class OtpOtherAccSameBankFragment :
    Fragment(R.layout.fragment_otp_other_acc_same_bank) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        next_confirm_otp.setOnClickListener {
            it.findNavController()
                .navigate(R.id.action_otpOtherAccSameBankFragment_to_EReceiptAnotherAccSameBankFragment)
        }
    }
}