package com.fixsolservices.digibank.ui.netbanking.fund

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.fixsolservices.digibank.R
import kotlinx.android.synthetic.main.fragment_confirm_other_acc_same_bank.*


class ConfirmOtherAccSameBankFragment : Fragment(R.layout.fragment_confirm_other_acc_same_bank) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        confirm_button_same_bank.setOnClickListener {
            it.findNavController()
                .navigate(R.id.action_confirmOtherAccSameBankFragment_to_otpOtherAccSameBankFragment)
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ConfirmOtherAccSameBankFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ConfirmOtherAccSameBankFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}