package com.fixsolservices.digibank.ui.netbanking.accountoverview

import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.StyleSpan
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.fixsolservices.digibank.R
import com.fixsolservices.digibank.util.Constants
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_account_overview.*

class AccountOverviewFragment : Fragment(R.layout.fragment_account_overview) {

    private lateinit var accOverviewViewModel: AccOverviewViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        accOverviewViewModel =
            ViewModelProvider(this).get(AccOverviewViewModel::class.java)
        accOverviewViewModel.text.observe(viewLifecycleOwner, Observer {
            val spannable = SpannableString(it)
            spannable.setSpan(
                StyleSpan(Typeface.BOLD),
                0,
                "Account".length,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            text_home.text = spannable
        })
    }
}