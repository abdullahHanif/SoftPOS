package com.abdullah.softpos.di.app.module

import com.abdullah.softpos.di.app.scope.ApplicationScope
import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@Module
object RetrofitModule {

    @JvmStatic
    @Provides
    @ApplicationScope
    fun provideCoroutineCallAdapter() = CoroutineCallAdapterFactory()

    @JvmStatic
    @Provides
    @ApplicationScope
    fun providesRetrofit(
        converterFactory: GsonConverterFactory,
        okHttpClient: OkHttpClient,
        callAdapterFactory: CoroutineCallAdapterFactory
    ) = Retrofit.Builder()
        .baseUrl("https://google.com/")
        .addCallAdapterFactory(callAdapterFactory)
        .addConverterFactory(converterFactory)
        .client(okHttpClient)
        .build()


    @JvmStatic
    @Provides
    @ApplicationScope
    fun provideGsonConverter(gson: Gson) = GsonConverterFactory.create(gson)
}