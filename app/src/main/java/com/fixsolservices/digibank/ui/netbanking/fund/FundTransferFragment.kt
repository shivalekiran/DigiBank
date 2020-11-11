package com.fixsolservices.digibank.ui.netbanking.fund

import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.fixsolservices.digibank.R
import kotlinx.android.synthetic.main.fragment_account_overview.*
import kotlinx.android.synthetic.main.fragment_fund_transfer.*

class FundTransferFragment : Fragment() {

    private lateinit var fundTransferViewModel: FundTransferViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fundTransferViewModel =
            ViewModelProvider(this).get(FundTransferViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_fund_transfer, container, false)
        val textView: TextView = root.findViewById(R.id.text_gallery)
        fundTransferViewModel.text.observe(viewLifecycleOwner, Observer {
            val spannable = SpannableString(it)
            spannable.setSpan(
                StyleSpan(Typeface.BOLD),
                0,
                "Fund".length,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            textView.text = spannable
        })
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        transfer_one_btn.setOnClickListener {
            it.findNavController().navigate(R.id.action_fund_transfer_another_acc_same_bank)
        }
        transfer_two_btn.setOnClickListener {
            it.findNavController().navigate(R.id.action_fund_transfer_item_to_toOtherBankFragment)
        }

    }
}