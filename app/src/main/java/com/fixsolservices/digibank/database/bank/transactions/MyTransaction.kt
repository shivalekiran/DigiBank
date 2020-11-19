package com.fixsolservices.digibank.database.bank.transactions

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.fixsolservices.digibank.util.indianCurrencyFormat
import java.math.BigInteger
import java.text.DecimalFormat

@Entity(tableName = "my_transactions")
data class MyTransaction(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val transactionDate: String,
    val transactionType: String,
    val payee: String,
    val totalAmount: Long = 30000,
    val paymentId: Long = 165165156,
    val status: String = "Success"
) {
    val totalAmountString: String
        get() = totalAmount.indianCurrencyFormat()

}

