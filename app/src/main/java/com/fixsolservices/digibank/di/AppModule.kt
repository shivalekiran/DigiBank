package com.fixsolservices.digibank.di

import android.app.Application
import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import androidx.room.Room
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.fixsolservices.digibank.R
import com.fixsolservices.digibank.database.DigiBankDatabase
import com.fixsolservices.digibank.database.bank.fund.FundTransferDao
import com.fixsolservices.digibank.database.bank.transactions.MyTransactionsDao
import com.fixsolservices.digibank.database.mainlogin.UserDao
import com.fixsolservices.digibank.util.Constants
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named

@Module
class AppModule {

    @Provides
    fun provideLoginDatabase(context: Context): DigiBankDatabase {
        return Room.databaseBuilder(
            context, DigiBankDatabase::class.java,
            DigiBankDatabase.DIGI_BANK_DATABASE
        ).build()
    }

    @Provides
    fun provideLoginDao(digiBankDatabase: DigiBankDatabase): UserDao {
        return digiBankDatabase.userDao()
    }

    @Provides
    fun provideMyTransactionDao(digiBankDatabase: DigiBankDatabase): MyTransactionsDao {
        return digiBankDatabase.myTransactionsDao()
    }

    @Provides
    fun provideFundTransferDao(digiBankDatabase: DigiBankDatabase): FundTransferDao {
        return digiBankDatabase.fundTransferDao()
    }

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient().newBuilder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    fun provideRetrofitInstance(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(okHttpClient)
//            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @ApplicationNameString
    fun getActivityName(application: Application): String {
        return application.getString(R.string.app_name)
    }

    @Provides
    @Named("ACT")
    fun getActName(): String {
        return "Activity"
    }

    @Provides
    fun provideRequestOptions(): RequestOptions {
        return RequestOptions
            .placeholderOf(R.drawable.ic_launcher_foreground)
            .error(R.drawable.ic_launcher_foreground)
    }

    @Provides
    fun provideGlideInstance(
        application: Application,
        requestOptions: RequestOptions
    ): RequestManager {
        return Glide.with(application)
            .setDefaultRequestOptions(requestOptions)
    }

    @Provides
    fun provideDrawableLogo(application: Application): Drawable {
        return ContextCompat.getDrawable(application, R.drawable.office_netbanking)!!
    }

}