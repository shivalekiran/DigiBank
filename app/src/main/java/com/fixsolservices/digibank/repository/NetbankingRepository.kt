package com.fixsolservices.digibank.repository


import android.service.autofill.Dataset
import com.fixsolservices.digibank.database.bank.fund.FundTransfer
import com.fixsolservices.digibank.database.bank.fund.FundTransferDao
import com.fixsolservices.digibank.database.bank.transactions.MyTransaction
import com.fixsolservices.digibank.database.bank.transactions.MyTransactionsDao
import com.fixsolservices.digibank.database.mainlogin.User
import com.fixsolservices.digibank.database.mainlogin.UserDao
import com.fixsolservices.digibank.util.DataState
import com.fixsolservices.digibank.util.Utils
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NetbankingRepository @Inject constructor(
    private val loginUserDao: UserDao,
    private val myTransactionsDao: MyTransactionsDao,
    private val fundTransferDao: FundTransferDao
) {
    suspend fun getLogedInUser(): Flow<User> = flow {
        emit(loginUserDao.getUser())
    }

    suspend fun getFundTransferData() = flow<DataState<List<FundTransfer>>> {
        emit(DataState.Loading)
        createDemoFundTransfer()
        emit(DataState.Success(fundTransferDao.getAllFundTransfer()))
    }

    suspend fun getMyTransactions(): Flow<DataState<List<MyTransaction>>> = flow {
        emit(DataState.Loading)
        createDemoTransactions()
        emit(DataState.Success(myTransactionsDao.getAllTransactions()))
    }


    private suspend fun createDemoFundTransfer() {
        val allFundTransfer = fundTransferDao.getAllFundTransfer()
        if (allFundTransfer.size < 5) {
            var fundTransfer: FundTransfer
            for (i in 0..4) {
                fundTransfer = FundTransfer(
                    date = Utils.getTransactionType(i),
                    transactionType = Utils.getRandomCapText(),
                    payee = Utils.getRandomCapText(),
                    beneficiaryDetails = Utils.getRandomCapText()
                )
                fundTransferDao.insertFundTransfer(fundTransfer)
            }
        }
    }
    private suspend fun createDemoTransactions() {
        val list = myTransactionsDao.getAllTransactions()
        if (list.size < 5) {
            var myTransaction: MyTransaction
            for (i in 0..4) {
                myTransaction = MyTransaction(
                    transactionDate = Utils.getTransactionType(i),
                    transactionType = Utils.getRandomCapText(),
                    payee = Utils.getRandomCapText()
                )
                val insertTransaction = myTransactionsDao.insertTransaction(myTransaction)
                println("inserted data - $insertTransaction")
            }
        }
    }
}