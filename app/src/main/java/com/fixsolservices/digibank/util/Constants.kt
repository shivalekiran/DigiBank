package com.fixsolservices.digibank.util

object Constants {
    const val LOGIN_DETAILS: String = "LOGIN_DETAILS"
    const val DO_LOGIN_ID: Int = 231
    const val BASE_URL = "https://jsonplaceholder.typicode.com"
    const val HOME_HEADER_IMAGE = "HOME_HEADER_IMAGE"

    const val DIGI_BANK = "Digi BANK"

    const val LANGUAGE_CHANGE = 2323
    const val START_NETBANKING_ACTIVITY_CODE = 326
    const val DONE_LOG_OUT = "DONE_LOG_OUT"
}

interface OnItemClick {
    fun onItemClick(position: Int)
}


