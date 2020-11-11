package com.fixsolservices.digibank.ui.netbanking.estate

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.fixsolservices.digibank.R

class EStatementFragment : Fragment(R.layout.fragment_e_statement) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }


    companion object {
        @JvmStatic
        fun newInstance() =
            EStatementFragment().apply {
                arguments = Bundle().apply {}
            }
    }
}