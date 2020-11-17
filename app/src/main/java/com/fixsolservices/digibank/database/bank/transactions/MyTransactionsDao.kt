package com.fixsolservices.digibank.database.bank.transactions

import android.util.Log
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MyTransactionsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTransaction(myTransaction: MyTransaction): Long

    @Query("SELECT * FROM my_transactions")
    suspend fun getAllTransactions(): List<MyTransaction>
}