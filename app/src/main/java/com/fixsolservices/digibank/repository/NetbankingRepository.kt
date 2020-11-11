package com.fixsolservices.digibank.repository

import com.fixsolservices.digibank.database.mainlogin.User
import com.fixsolservices.digibank.database.mainlogin.UserDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class NetbankingRepository constructor(private val loginUserDao: UserDao) {

    suspend fun getLogedInUser(): Flow<User> = flow {
        emit(loginUserDao.getUser())
    }
}