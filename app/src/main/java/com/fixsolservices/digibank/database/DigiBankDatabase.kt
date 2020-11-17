package com.fixsolservices.digibank.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.fixsolservices.digibank.database.bank.fund.FundTransfer
import com.fixsolservices.digibank.database.bank.fund.FundTransferDao
import com.fixsolservices.digibank.database.bank.transactions.MyTransaction
import com.fixsolservices.digibank.database.bank.transactions.MyTransactionsDao
import com.fixsolservices.digibank.database.mainlogin.User
import com.fixsolservices.digibank.database.mainlogin.UserDao

@Database(
    entities = [User::class, MyTransaction::class, FundTransfer::class],
    version = 1,
    exportSchema = false
)
abstract class DigiBankDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    abstract fun myTransactionsDao(): MyTransactionsDao

    abstract fun fundTransferDao(): FundTransferDao

    companion object {
        const val DIGI_BANK_DATABASE = "DIGI_BANK_DATABASE"
    }
}