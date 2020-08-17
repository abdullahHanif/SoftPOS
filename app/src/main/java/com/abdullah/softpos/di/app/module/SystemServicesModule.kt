package com.abdullah.softpos.di.app.module

import android.content.Context
import android.net.ConnectivityManager
import android.view.inputmethod.InputMethodManager
import com.abdullah.softpos.di.app.scope.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
object SystemServicesModule {

    @JvmStatic
    @ApplicationScope
    @Provides
    fun provideInputMedthodManager(context: Context) =
        context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

    @JvmStatic
    @ApplicationScope
    @Provides
    fun provideConnectivityManager(context: Context) =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager


}