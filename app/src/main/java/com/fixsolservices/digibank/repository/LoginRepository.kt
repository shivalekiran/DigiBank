package com.fixsolservices.digibank.repository

import android.util.Log
import androidx.room.Dao
import com.fixsolservices.digibank.database.mainlogin.User
import com.fixsolservices.digibank.database.mainlogin.UserDao
import com.fixsolservices.digibank.network.LoginAPI
import com.fixsolservices.digibank.util.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception

class LoginRepository constructor(
    private val loginAPI: LoginAPI,
    private val loginUserDao: UserDao
) {
    suspend fun getUser(): Flow<DataState<User>> = flow {
        emit(DataState.Loading)
        try {
            val user = loginAPI.getUser();
            println("User from API: :$user")
            loginUserDao.insertUser(user)
            val dataBaseUser = loginUserDao.getUser()
            emit(DataState.Success(dataBaseUser))
            println("Database user: $dataBaseUser")
        } catch (e: Exception) {
            Log.e("Debug: ", "User: ", e)
            emit(DataState.Error(e))
        }
    }
}