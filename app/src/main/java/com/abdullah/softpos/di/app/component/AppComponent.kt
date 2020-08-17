package com.abdullah.softpos.di.app.component

import android.content.Context
import com.abdullah.softpos.AppClass
import com.abdullah.softpos.base.BaseActivity
import com.abdullah.softpos.data.localsource.LocalSource
import com.abdullah.softpos.di.app.module.*
import com.abdullah.softpos.di.app.scope.ApplicationScope
import com.abdullah.softpos.di.factory.IComponent
import com.abdullah.softpos.ui.home.views.Jobs
import dagger.Component

@ApplicationScope
@Component(modules = [AppModule::class, SystemServicesModule::class,
    GsonModule::class,  RoomModule::class, RetrofitModule::class,NetworkModule::class])
interface AppComponent : IComponent {

    fun inject(appClass: AppClass)
    fun inject(localsource: LocalSource)
    fun inject(baseActivity: BaseActivity)
    fun inject(jobs: Jobs)
    fun context(): Context
}