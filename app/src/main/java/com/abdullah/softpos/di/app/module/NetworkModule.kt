package com.abdullah.softpos.di.app.module

import com.abdullah.softpos.di.app.scope.ApplicationScope
import com.abdullah.softpos.network.ApiInterface
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

@Module
object NetworkModule {

    @JvmStatic
    @Provides
    @ApplicationScope
    fun provideOkHttp() = OkHttpClient.Builder()
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .connectTimeout(30, TimeUnit.SECONDS)
        .build()

    @JvmStatic
    @Provides
    @ApplicationScope
    fun provideApiInterface(retrofit: Retrofit) = retrofit.create<ApiInterface>(ApiInterface::class.java)

}