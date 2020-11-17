package com.fixsolservices.digibank.ui.netbanking.accountoverview

import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fixsolservices.digibank.R
import com.fixsolservices.digibank.database.bank.fund.FundTransfer
import com.fixsolservices.digibank.database.bank.transactions.MyTransaction
import com.fixsolservices.digibank.util.DataState
import com.fixsolservices.digibank.viewmodels.ViewModelProviderFactory
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_account_overview.*
import kotlinx.android.synthetic.main.fragment_manage_payee.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
class AccountOverviewFragment : DaggerFragment() {

    private lateinit var accOverviewViewModel: AccOverviewViewModel
    lateinit var transactionAdapter: MyTransactionsAdapter
    lateinit var fundTransferAdapter: FundTransferAdapter
    var transactionList = mutableListOf<MyTransaction>()

    val fundTransferList = mutableListOf<FundTransfer>()

    @Inject
    lateinit var factory: ViewModelProviderFactory
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_account_overview, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        accOverviewViewModel =
            ViewModelProvider(this, factory).get(AccOverviewViewModel::class.java)
        initializeView()
        addUserObserver()
        addMyTransactionObserver()
        addFundTransferObserver()
    }

    private fun addFundTransferObserver() {
        accOverviewViewModel.fetchFundTransferFromDB(AccountOverviewStateEvent.FetchFundTransferList)
        accOverviewViewModel.myFunds.observe(viewLifecycleOwner, Observer { dataState ->
            when (dataState) {
                is DataState.Success -> {
                    fundTransferList.addAll(dataState.data)
                    fundTransferAdapter.notifyDataSetChanged()
                }
                is DataState.Error -> {
                    println("Error${dataState.exception}")
                }
                DataState.Loading -> {
                    println("Loading fund transfer data")
                }
            }

        })
    }

    private fun initializeView() {
        myTransactionRecycleView.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        transactionAdapter = MyTransactionsAdapter(transactionList)
        myTransactionRecycleView.adapter = transactionAdapter

        fundTransferAdapter = FundTransferAdapter(fundTransferList)
        myFundRecycleView.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        myFundRecycleView.adapter = fundTransferAdapter
    }

    private fun addMyTransactionObserver() {
        accOverviewViewModel.fetchDataFromDatabase(AccountOverviewStateEvent.FetchMyTransactionsList)
        accOverviewViewModel.myTransaction.observe(viewLifecycleOwner, Observer { dataState ->
            when (dataState) {
                is DataState.Success -> {
                    transactionList.addAll(dataState.data)
                    transactionAdapter.notifyDataSetChanged()
                }
                is DataState.Error -> {
                    println("Eroro ${dataState.exception}")
                }
                DataState.Loading -> {
                    println("Loading demo data")
                }
            }
        })
    }

    private fun addUserObserver() {
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