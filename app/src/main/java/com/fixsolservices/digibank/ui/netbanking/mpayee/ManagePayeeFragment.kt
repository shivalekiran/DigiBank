package com.fixsolservices.digibank.ui.netbanking.mpayee

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.fixsolservices.digibank.R
import kotlinx.android.synthetic.main.fragment_manage_payee.*

class ManagePayeeFragment : Fragment() {

    private lateinit var managePayeeViewModel: ManagePayeeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        return inflater.inflate(R.layout.fragment_manage_payee, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        managePayeeViewModel = ViewModelProvider(this).get(ManagePayeeViewModel::class.java)
        managePayeeViewModel.text.observe(viewLifecycleOwner, Observer {
            manage_payee_title.text = it
        })
        next_btn_add_payee.setOnClickListener {
            it.findNavController()
                .navigate(R.id.action_manage_payee_item_to_managePayeeConfirmDetailsFragment)
        }
    }
}