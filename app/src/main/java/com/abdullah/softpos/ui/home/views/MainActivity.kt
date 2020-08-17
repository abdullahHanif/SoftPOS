package com.abdullah.softpos.ui.home.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.abdullah.softpos.R
import com.abdullah.softpos.extension.replaceFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        replaceFragment(Splash(),R.id.container,false)
    }
}
