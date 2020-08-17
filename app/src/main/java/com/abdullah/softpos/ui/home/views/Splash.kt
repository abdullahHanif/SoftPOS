package com.abdullah.softpos.ui.home.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.abdullah.softpos.R
import com.abdullah.softpos.base.BaseFragment
import com.abdullah.softpos.databinding.FrSplashBinding
import com.abdullah.softpos.extension.replaceFragment
import kotlinx.android.synthetic.main.fr_splash.*
import java.util.*

class Splash : BaseFragment(){

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding : FrSplashBinding = DataBindingUtil.inflate(inflater, R.layout.fr_splash, container, false)
        return binding.root
    }


    override fun initializeComponents() {
        animateSplashLogo()
    }

    private fun animateSplashLogo() {
        splash_logo.animate().scaleX(0f).scaleY(0f).setDuration(700).setStartDelay(2000).withLayer().start()

        //after animation changing screen
        Timer().schedule(object : TimerTask(){
            override fun run() {
                screenTransactions().replaceFragment(Jobs.newInstance(),R.id.container,false)
            }
        },3000)
    }

    override fun setObservers() {
    }

    override fun showLoader() {
    }

    override fun hideLoader() {
    }

}