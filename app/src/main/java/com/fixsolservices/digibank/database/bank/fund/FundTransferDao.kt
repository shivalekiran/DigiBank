package com.fixsolservices.digibank.database.bank.fund

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.fixsolservices.digibank.database.bank.transactions.MyTransaction

@Dao
interface FundTransferDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFundTransfer(fundTransfer: FundTransfer): Long

    @Query("SELECT * FROM fund_transfer")
    suspend fun getAllFundTransfer(): List<FundTransfer>
}