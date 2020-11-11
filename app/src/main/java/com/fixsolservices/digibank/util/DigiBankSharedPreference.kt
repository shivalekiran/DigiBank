package com.fixsolservices.digibank.util

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject

class DigiBankSharedPreference @Inject constructor(context: Context) {

    private var preferences: SharedPreferences

    // list of app specific preferences
    private val IS_FIRST_RUN_PREF = Pair("is_first_run", false)

    private val APPLICATION_LANGUAGE = Pair("app_language", "en")

    private val NET_BANKING_USER_ID = Pair("net_banking_user_ID", null)
    private val NET_BANKING_USER_PASSWORD = Pair("net_banking_user_password", null)


    init {
        preferences = context.getSharedPreferences(Companion.NAME, Companion.MODE)
    }

    /**
     * SharedPreferences extension function, so we won't need to call edit() and apply()
     * ourselves on every SharedPreferences operation.
     */
    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = edit()
        operation(editor)
        editor.apply()
    }

    var firstRun: Boolean
        // custom getter to get a preference of a desired type, with a predefined default value
        get() = preferences.getBoolean(IS_FIRST_RUN_PREF.first, IS_FIRST_RUN_PREF.second)
        // custom setter to save a preference back to preferences file
        set(value) = preferences.edit {
            it.putBoolean(IS_FIRST_RUN_PREF.first, value)
        }

    var appLanguage: String?
        get() = preferences.getString(APPLICATION_LANGUAGE.first, APPLICATION_LANGUAGE.second)
        set(value) = preferences.edit {
            it.putString(APPLICATION_LANGUAGE.first, value)
        }

    var netBankingUserID: String?
        get() = preferences.getString(NET_BANKING_USER_ID.first, NET_BANKING_USER_ID.second)
        set(value) = preferences.edit {
            it.putString(NET_BANKING_USER_ID.first, value)
        }


    var netBankingUserPass: String?
        get() = preferences.getString(NET_BANKING_USER_PASSWORD.first, NET_BANKING_USER_PASSWORD.second)
        set(value) = preferences.edit {
            it.putString(NET_BANKING_USER_PASSWORD.first, value)
        }

    companion

    object {
        private const val NAME = "DigiBankSharedPreference"
        private const val MODE = Context.MODE_PRIVATE
    }
}