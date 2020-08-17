package com.abdullah.softpos

import android.app.Application
import android.content.Context
import android.content.IntentFilter
import com.abdullah.softpos.broadcast_recievers.InternetStateReceiver
import com.abdullah.softpos.di.app.component.AppComponent
import com.abdullah.softpos.di.factory.ComponentFactory
import javax.inject.Inject

class AppClass : Application() {

    @Inject
    lateinit var internetStateReceiver: InternetStateReceiver

    override fun onCreate() {
        super.onCreate()
        context = this

        ComponentFactory.createComponent(AppComponent::class).inject(this)

//        registering network state receiver
        registerReceiver(
            internetStateReceiver,
            IntentFilter(internetStateReceiver.INTERNET_RECEIVER)
        )
    }


    companion object {

        private  lateinit var context: Context

        fun getAppContext(): Context {
            return context
        }

    }
}