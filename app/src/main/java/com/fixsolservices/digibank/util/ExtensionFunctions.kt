package com.fixsolservices.digibank.util

import android.app.Activity
import android.content.Context
import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.style.StyleSpan
import android.util.Patterns
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat

/**
 * @author Kiran Shivale
 * Hide keyboard in activity
 * @param context get to service
 */
fun View.hideKeyBoard(context: Context) {
    val inputMethodManager: InputMethodManager =
        context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(this.windowToken, 0)
}

fun View.showView() {
    this.visibility = View.VISIBLE
}

fun View.hideView() {
    this.visibility = View.GONE
}

/**
 *
 */
fun SpannableString.boldSpanText(start: Int, end: Int): Spannable {
    setSpan(
        StyleSpan(Typeface.BOLD), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
    )
    return this
}

/**
 * validating email address here
 */
fun String.validateEmail(): Boolean {
    return this.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()
}


fun Int.getColor(context: Context): Int {
    return ContextCompat.getColor(context, this)
}