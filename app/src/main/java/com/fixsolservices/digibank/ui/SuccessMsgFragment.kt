package com.fixsolservices.digibank.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fixsolservices.digibank.R
import kotlinx.android.synthetic.main.fragment_success_msg.*

class SuccessMsgFragment : Fragment() {
    private val MSG_TEXT = "MSG_TEXT"
    private var msgText: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            msgText = it.getString(MSG_TEXT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_success_msg, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        success_msg_text.text = msgText
    }

    companion object {
        @JvmStatic
        fun newInstance(msg: String) =
            SuccessMsgFragment().apply {
                arguments = Bundle().apply {
                    putString(MSG_TEXT, msg)
                }
            }
    }
}