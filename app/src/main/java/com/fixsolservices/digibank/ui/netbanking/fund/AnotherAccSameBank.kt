package com.fixsolservices.digibank.ui.netbanking.fund

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.fixsolservices.digibank.R
import kotlinx.android.synthetic.main.activity_sign_up.*
import kotlinx.android.synthetic.main.fragment_fund_transfer_to_bank_acc.*
import kotlinx.android.synthetic.main.male_female_layout.*

class AnotherAccSameBank : Fragment(R.layout.fragment_fund_transfer_to_bank_acc) {

    lateinit var mContext: Context

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        next_confirm_details.setOnClickListener {
            it.findNavController()
                .navigate(R.id.action_another_acc_same_bank_to_confirmOtherAccSameBankFragment)
        }

        select_acc_text.setOnClickListener {
            select_acc_linear_layout.visibility == if (select_acc_linear_layout.visibility == View.VISIBLE) View.GONE else View.VISIBLE
        }
    }

    fun onSelectAccViewClick(view: View) {
        when (view.id) {
            R.id.acc_salary -> {
                select_acc_text.text = "Salary Account(INR) 001xxxxxx"
            }
            R.id.acc_current -> {
                select_acc_text.text = "Current Account(INR) 002xxxxxxx"
            }
        }
        maleFemaleLayoutVisibility()
    }

    private fun maleFemaleLayoutVisibility() {
        if (male_female_line_constraintlayout_layout.visibility == View.VISIBLE) {
            male_female_line_constraintlayout_layout.visibility = View.GONE
            select_acc_text.setCompoundDrawablesRelativeWithIntrinsicBounds(
                null,
                null,
                null,
                ContextCompat.getDrawable(mContext, R.drawable.ic_down)
            )

        } else {
            male_female_line_constraintlayout_layout.visibility = View.VISIBLE
            select_acc_text.setCompoundDrawablesRelativeWithIntrinsicBounds(
                null,
                null,
                null,
                ContextCompat.getDrawable(mContext, R.drawable.ic_up)
            )
        }
    }
}