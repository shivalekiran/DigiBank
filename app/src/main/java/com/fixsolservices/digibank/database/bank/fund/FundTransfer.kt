package com.fixsolservices.digibank.database.bank.fund

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlin.random.Random

@Entity(tableName = "fund_transfer")
data class FundTransfer(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val date: String,
    val transactionType: String,
    val payee: String,
    val beneficiaryDetails: String,
    val amount: Long = Random.nextLong(0, 500000)
)