package com.fixsolservices.digibank.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.fixsolservices.digibank.database.mainlogin.User
import com.fixsolservices.digibank.database.mainlogin.UserDao

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class LoginDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        const val LOGIN_DATABASE = "LOGIN_DATABASE"
    }
}