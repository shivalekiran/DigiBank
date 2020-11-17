package com.fixsolservices.digibank.ui.netbanking.accountoverview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fixsolservices.digibank.R
import com.fixsolservices.digibank.database.bank.fund.FundTransfer
import com.fixsolservices.digibank.database.bank.transactions.MyTransaction


class FundTransferAdapter(private val transactions: List<FundTransfer>) :
    RecyclerView.Adapter<FundTransferAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val myTransactionItem =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.my_transactions_item_layout, parent, false)
        return MyViewHolder(myTransactionItem)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindViewData(transactions[position])

    }

    override fun getItemCount(): Int {
        return transactions.size
    }


    inner class MyViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        val TransactionsDate: TextView = item.findViewById(R.id.TransactionsDate)
        val TransactionsType: TextView = item.findViewById(R.id.TransactionsType)
        val PayeeName: TextView = item.findViewById(R.id.PayeeName)
        val TotalAmount: TextView = item.findViewById(R.id.TotalAmount)
        val PaymentId: TextView = item.findViewById(R.id.PaymentId)
        val Status: TextView = item.findViewById(R.id.Status)
        fun bindViewData(myTransaction: FundTransfer) {
            TransactionsDate.text = myTransaction.date
            TransactionsType.text = myTransaction.transactionType
            PayeeName.text = myTransaction.payee
            TotalAmount.text = "${myTransaction.amount}"
            PaymentId.text = myTransaction.beneficiaryDetails

        }

    }
}