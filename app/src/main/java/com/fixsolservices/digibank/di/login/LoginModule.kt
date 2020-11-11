package com.fixsolservices.digibank.di.login

import com.fixsolservices.digibank.database.mainlogin.UserDao
import com.fixsolservices.digibank.network.LoginAPI
import com.fixsolservices.digibank.repository.LoginRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class LoginModule {
    @Provides
    fun providesServiceLoginAPI(retrofit: Retrofit): LoginAPI {
        return retrofit.create(LoginAPI::class.java)
    }

    @Provides
    fun provideLoginRepository(loginAPI: LoginAPI, userDao: UserDao): LoginRepository {
        return LoginRepository(loginAPI, userDao)
    }
}