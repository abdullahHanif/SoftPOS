package com.abdullah.softpos.di.factory

import com.abdullah.softpos.AppClass
import com.abdullah.softpos.di.app.component.AppComponent
import com.abdullah.softpos.di.app.component.DaggerAppComponent
import com.abdullah.softpos.di.app.module.AppModule
import kotlin.reflect.KClass

object ComponentFactory {
    private lateinit var appComponent: AppComponent

    fun <T : IComponent> createComponent(componentClass: KClass<T>): T {
        when (componentClass) {
            AppComponent::class -> {
                appComponent = DaggerAppComponent
                    .builder()
                    .appModule(AppModule(AppClass.getAppContext()))
                    .build()

                return appComponent as T
            }

            else -> {
                throw IllegalArgumentException("Unknown Component class: ${componentClass.java.simpleName}")
            }
        }
    }
}