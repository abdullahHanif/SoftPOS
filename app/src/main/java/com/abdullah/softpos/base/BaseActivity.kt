package com.abdullah.softpos.base

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.abdullah.softpos.broadcast_recievers.InternetStateReceiver
import com.abdullah.softpos.di.app.component.AppComponent
import com.abdullah.softpos.di.factory.ComponentFactory
import javax.inject.Inject

abstract class BaseActivity : AppCompatActivity() {
    var isInternetConnected = false

    @Inject
    lateinit var internetStateReceiver: InternetStateReceiver

    init {
        ComponentFactory.createComponent(AppComponent::class).inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

        attachViewModel()
        setObservers()
        startObservingNavEvents()
    }

    override fun onStart() {
        super.onStart()
        //adding client to get network connectivity events
        internetStateReceiver.addReciver(this)
    }

    override fun onStop() {
        super.onStop()
        //removing internet connection client
        internetStateReceiver.removeReceiver(this)
    }

    protected abstract fun attachViewModel()
    protected abstract fun setObservers()
    protected abstract fun startObservingNavEvents()

    open fun onInternetConnected() {
        isInternetConnected = true
    }

    open fun onInternetDisconnected() {
        isInternetConnected = false
    }

}