package com.fixsolservices.digibank.ui.main

import android.app.Activity
import android.content.Intent
import android.content.res.Configuration
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.SpannableString
import android.util.DisplayMetrics
import android.view.Menu
import android.view.MenuItem
import android.view.WindowManager
import android.widget.Toast
import com.bumptech.glide.RequestManager
import com.fixsolservices.digibank.R
import com.fixsolservices.digibank.ui.NetBankingActivity
import com.fixsolservices.digibank.ui.SplashActivity
import com.fixsolservices.digibank.ui.contact.ContactActivity
import com.fixsolservices.digibank.ui.language.LanguageActivity
import com.fixsolservices.digibank.ui.login.LoginActivity
import com.fixsolservices.digibank.util.*
import com.fixsolservices.digibank.util.Constants.DIGI_BANK
import com.fixsolservices.digibank.util.Constants.DO_LOGIN_ID
import com.fixsolservices.digibank.util.Constants.LANGUAGE_CHANGE
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import javax.inject.Inject

/**
 * @author Kiran Shivale
 *
 * main dashboard activity showing main page
 */
class MainActivity : BaseActivity() {


    @Inject
    lateinit var logoDrawable: Drawable

    @Inject
    lateinit var requestManager: RequestManager

    @Inject
    lateinit var appPreference: DigiBankSharedPreference

    var menu: Menu? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //setting flag for full screen activity for transparent status bar
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        initView()
    }

    /**
     * initialize ui and call back to view
     */
    private fun initView() {
        setSupportActionBar(main_toolbar)
        floating_contact_btn.setOnClickListener {
            callGetInTouch()
        }
        get_in_touch.setOnClickListener {
            callGetInTouch()
        }
//        setting spannable text to title
        main_toolbar.title = SpannableString(DIGI_BANK).boldSpanText(
            DIGI_BANK.indexOf("B"),
            DIGI_BANK.length
        )

        netbanking_overview_btn.setOnClickListener {
            openNetBankingAct()
        }
    }

    //opening net banking activity
    private fun openNetBankingAct() {
        if (appPreference.firstRun) {
            val contactActivity = Intent(this, NetBankingActivity::class.java)
            startActivity(contactActivity)
        } else {
            logInUser()
        }
    }

    //opening change language activity for result
    //return @Activity.RESULT_OK on success change language and setting language her
    private fun showLanguageChangeDialog() {
        val langIntent = Intent(this, LanguageActivity::class.java)
        startActivityForResult(langIntent, LANGUAGE_CHANGE)
    }

    //calling contact activity for review/contact
    private fun callGetInTouch() {
        val contactActivity = Intent(this, ContactActivity::class.java)
        startActivity(contactActivity)
    }

    //setting language if change from @LanguageActivity
    private fun setLocale(lang: String) {
        val myLocale = Locale(lang)
        val res: Resources = resources
        val dm: DisplayMetrics = res.displayMetrics
        val conf: Configuration = res.configuration
        conf.locale = myLocale
        res.updateConfiguration(conf, dm)
        val refresh = Intent(this, SplashActivity::class.java)
        finish()
        startActivity(refresh)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        this.menu = menu;
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        if (appPreference.firstRun) {
            showLogInMenu()
        } else {
            showLogOuMenu()
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.change_language_item -> {
                showLanguageChangeDialog()
                return true
            }
            R.id.user_profile_item -> {
                logInUser()
                return true
            }
            R.id.user_log_out_item -> {
                logOutUser()
                return true
            }
            else ->
                return super.onOptionsItemSelected(item)
        }
    }

    private fun logOutUser() {
        appPreference.firstRun = false
        showLogOuMenu()
    }

    //opening login activity for login User
    //setting @appPreference.firstRun false to set logout status
    private fun logInUser() {
        appPreference.firstRun = false
        val loginActivity = Intent(this, LoginActivity::class.java)
        startActivityForResult(loginActivity, DO_LOGIN_ID)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            //for result of login activity
            DO_LOGIN_ID -> {
                if (resultCode == RESULT_OK) {
                    showLogInMenu()
                } else {
                    Toast.makeText(this, "LOGIN FAIL", Toast.LENGTH_SHORT).show()
                    showLogOuMenu()
                }
            }
            //for result of @LanguageActivity and setting language
            LANGUAGE_CHANGE -> {
                Toast.makeText(this, "Coming soon..", Toast.LENGTH_SHORT).show()
//                setLocale(appPreference.appLanguage!!)
            }
        }
//        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun showLogInMenu() {
        hideMenu(menu?.findItem(R.id.user_profile_item))
        showMenu(menu?.findItem(R.id.user_log_out_item))
    }

    private fun showLogOuMenu() {
        showMenu(menu?.findItem(R.id.user_profile_item))
        hideMenu(menu?.findItem(R.id.user_log_out_item))
    }

    private fun showMenu(menuItem: MenuItem?) {
        menuItem?.isVisible = true
    }

    private fun hideMenu(menuItem: MenuItem?) {
        menuItem?.isVisible = false
    }

    //change resource before activity created(injection obj not available in this fun)
    /*
    override fun attachBaseContext(newBase: Context?) {
        newBase?.let {
            super.attachBaseContext(
                MyContextWrapper.wrap(
                    newBase,
                    DigiBankSharedPreference(newBase).appLanguage!!
                )
            )
        }
    }*/
}