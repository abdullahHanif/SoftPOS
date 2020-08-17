package com.abdullah.softpos.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import com.abdullah.softpos.di.app.scope.ApplicationScope
import javax.inject.Inject

@ApplicationScope
class InternetConnection @Inject constructor(
    var connectivityManager: ConnectivityManager,
    var context: Context
) {

    fun isNetworkConnected(): Boolean {

        if (Build.VERSION.SDK_INT < 23) {
            val ni = connectivityManager.activeNetworkInfo
            return ni?.let {
                ni.isConnected && (ni.type == ConnectivityManager.TYPE_WIFI || ni.type == ConnectivityManager.TYPE_MOBILE)
            } ?: false
        } else {
            val n = connectivityManager.activeNetwork

            return n?.let {
                val nc = connectivityManager.getNetworkCapabilities(n)

                nc.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                        nc.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                        nc.hasTransport(NetworkCapabilities.TRANSPORT_VPN)
            }?: false
        }
    }
}
