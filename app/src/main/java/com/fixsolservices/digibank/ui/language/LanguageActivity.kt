package com.fixsolservices.digibank.ui.language

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.res.Configuration
import android.content.res.Resources
import android.graphics.Typeface
import android.os.Build
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.StyleSpan
import androidx.recyclerview.widget.LinearLayoutManager
import com.fixsolservices.digibank.R
import com.fixsolservices.digibank.ui.login.LoginActivity
import com.fixsolservices.digibank.util.BaseActivity
import com.fixsolservices.digibank.util.Constants
import com.fixsolservices.digibank.util.DigiBankSharedPreference
import com.fixsolservices.digibank.util.OnItemClick
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_language.*
import java.util.*
import javax.inject.Inject


class LanguageActivity : BaseActivity() {

    @Inject
    lateinit var appPreference: DigiBankSharedPreference
    var countries = arrayOf(
        "English", "Hindi", "Marathi"
    )

    var selectedLanguage = ""

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_language)
        val chooseLang = "Choose the language"
        val spannable = SpannableString(chooseLang)
        spannable.setSpan(
            StyleSpan(Typeface.BOLD),
            0,
            chooseLang.indexOf("l", 0, false),
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        choose_lang_text.text = spannable

        val languageAdapter = LanguageRecycleAdapter(countries, object : OnItemClick {
            override fun onItemClick(position: Int) {
                when (countries[position]) {
                    "English" -> {
                        selectedLanguage = "en"
                    }
                    "Hindi" -> {
                        selectedLanguage = "hi"
                    }
                    "Marathi" -> {
                        selectedLanguage = "mr"
                    }
                }
            }
        })
        languages_recyclerview.layoutManager = LinearLayoutManager(this)
        languages_recyclerview.adapter = languageAdapter
        select_lang_btn.setOnClickListener {
            appPreference.appLanguage = selectedLanguage
            setResult(Activity.RESULT_OK)
            finish()
        }
    }

    private fun setAppLocale(localeCode: String) {
        val resources: Resources = resources
        val config: Configuration = resources.configuration
        config.setLocale(Locale(localeCode.toLowerCase(Locale.ROOT)))
        config.setLocale(Locale(localeCode.toLowerCase()))
        createConfigurationContext(config)
        this.recreate()
//        resources.updateConfiguration(config, dm)
    }
}



