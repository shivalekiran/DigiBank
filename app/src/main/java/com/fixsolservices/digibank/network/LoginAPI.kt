package com.fixsolservices.digibank.network

import com.fixsolservices.digibank.database.mainlogin.User
import retrofit2.http.GET

interface LoginAPI {

    @GET("/users/1")
    suspend fun getUser(
//        @Path("id") email: String/*, password: String*/
    ): User
}