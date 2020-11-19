package com.fixsolservices.digibank.util

import android.annotation.SuppressLint
import android.text.TextUtils
import androidx.core.text.TextUtilsCompat
import androidx.room.util.StringUtil
import java.lang.StringBuilder
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
object Utils {
    const val ddMMyyyy = "dd-MM-yyyy"

    var ALPHABET_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"

    fun getDateBefore(day: Int): String {
        val calendar = Calendar.getInstance()
        val simpleDateFormatter = SimpleDateFormat(ddMMyyyy)
        if (day != 0)
            calendar.add(Calendar.DAY_OF_YEAR, -day * 2)
        return simpleDateFormatter.format(calendar.time)
    }

    fun getRandomCapText(): String {
        val stringBuilder = StringBuilder()
        for (i in 0..4) {
            stringBuilder.append(
                ALPHABET_STRING[
                        Random().nextInt(
                            ALPHABET_STRING.length
                        )
                ]
            )
        }
        return stringBuilder.toString()
    }

    fun getTransactionType(id: Int): String {
        return when (id) {
            0 -> "Transfer Funds to Other Bank Accounts / Credit Cards"
            1 -> "Transfer Funds to any other ICICI bank account"
            2 -> "Transfer Funds to Other Bank Accounts / Credit Cards"
            3 -> "Transfer Funds to an account in other bank through IMPS with IFSC code"
            4 -> "Transfer Funds to an account in other bank through IMPS with IFSC code"
            else -> "Transfer Funds to Other Bank Accounts / Credit Cards"
        }
    }
}