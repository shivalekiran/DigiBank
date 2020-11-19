package com.fixsolservices.digibank.ui.netbanking.accountoverview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fixsolservices.digibank.R
import com.fixsolservices.digibank.database.bank.fund.FundTransfer


class FundTransferAdapter(private val transactions: List<FundTransfer>) :
    RecyclerView.Adapter<FundTransferAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val myTransactionItem =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.fund_transfer_item_layout, parent, false)
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
        val BeneficialDetails: TextView = item.findViewById(R.id.beneficial_details)
        val AmmountInr: TextView = item.findViewById(R.id.ammount_inr)
        fun bindViewData(fundTransfer: FundTransfer) {
            TransactionsDate.text = fundTransfer.date
            TransactionsType.text = fundTransfer.transactionType
            PayeeName.text = fundTransfer.payee
            BeneficialDetails.text = fundTransfer.beneficiaryDetails
            AmmountInr.text = fundTransfer.amountString
        }

    }
}