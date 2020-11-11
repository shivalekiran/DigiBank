package com.fixsolservices.digibank.ui

import android.content.Intent
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.text.Spannable
import android.text.SpannableString
import android.text.style.StyleSpan
import com.fixsolservices.digibank.R
import com.fixsolservices.digibank.ui.language.LanguageActivity
import com.fixsolservices.digibank.ui.login.LoginActivity
import com.fixsolservices.digibank.ui.main.MainActivity
import com.fixsolservices.digibank.util.Constants
import com.fixsolservices.digibank.util.DigiBankSharedPreference
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_splash.*
import javax.inject.Inject

class SplashActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var appPreference: DigiBankSharedPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val spannable = SpannableString(Constants.DIGI_BANK)
        spannable.setSpan(
            StyleSpan(Typeface.BOLD),
            Constants.DIGI_BANK.indexOf("B"),
            Constants.DIGI_BANK.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        digi_bank_title.text = spannable
        val directionIntent = Intent(this, MainActivity::class.java)
        Handler().postDelayed(Runnable {
            startActivity(directionIntent)
            finish()
        }, 500)
    }
}