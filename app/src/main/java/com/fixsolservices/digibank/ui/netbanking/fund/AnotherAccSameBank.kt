package com.fixsolservices.digibank.ui.netbanking.fund

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.PopupWindow
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.fixsolservices.digibank.R
import com.fixsolservices.digibank.util.transparentPopupWindow
import kotlinx.android.synthetic.main.fragment_fund_transfer_to_bank_acc.*
import kotlinx.android.synthetic.main.male_female_layout.*

class AnotherAccSameBank : Fragment(R.layout.fragment_fund_transfer_to_bank_acc) {

    lateinit var mContext: Context

    lateinit var popupWindow: PopupWindow
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
        popupWindow = PopupWindow(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        next_confirm_details.setOnClickListener {
            it.findNavController()
                .navigate(R.id.action_another_acc_same_bank_to_confirmOtherAccSameBankFragment)
        }
        val layout = LayoutInflater.from(context)
            .inflate(R.layout.fund_transfer_acc_list_layout, null, false)
        popupWindow.transparentPopupWindow(mContext, layout)

        select_acc_text.setOnClickListener {
            Toast.makeText(context, "showing asdfsdfkasjd", Toast.LENGTH_SHORT).show()
            if (popupWindow.isShowing) {
                popupWindow.dismiss()
            } else {
                val accSalary = layout.findViewById<TextView>(R.id.acc_salary)
                val accCurrent = layout.findViewById<TextView>(R.id.acc_current)
                accSalary.setOnClickListener {
                    select_acc_text.text = accSalary.text
                    popupWindow.dismiss()
                }
                accCurrent.setOnClickListener {
                    select_acc_text.text = accCurrent.text
                    popupWindow.dismiss()
                }
                popupWindow.showAsDropDown(it)
            }
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

    override fun onDestroyView() {
        popupWindow.dismiss()
        super.onDestroyView()
    }
}