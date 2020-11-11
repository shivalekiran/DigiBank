package com.fixsolservices.digibank

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.fragment_manage_payee_confirm_details.*

class ManagePayeeConfirmDetailsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_manage_payee_confirm_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        payee_confirm_button.setOnClickListener {
            it.findNavController()
                .navigate(R.id.action_managePayeeConfirmDetailsFragment_to_urnVerificationFragment)
        }
    }
}