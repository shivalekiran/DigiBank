package com.fixsolservices.digibank.ui.netbanking.fund

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.fixsolservices.digibank.R
import kotlinx.android.synthetic.main.fragment_e_receipt_another_acc_same_bank.*

class EReceiptAnotherAccSameBankFragment : Fragment(R.layout.fragment_e_receipt_another_acc_same_bank) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        done_fund_transfer.setOnClickListener {
            it.findNavController()
                .navigate(R.id.action_EReceiptAnotherAccSameBankFragment_to_fund_transfer_item)
        }
    }
}