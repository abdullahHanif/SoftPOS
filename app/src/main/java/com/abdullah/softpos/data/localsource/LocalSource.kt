package com.abdullah.softpos.data.localsource

import com.abdullah.softpos.db.TasksDatabase
import com.abdullah.softpos.di.app.component.AppComponent
import com.abdullah.softpos.di.factory.ComponentFactory
import javax.inject.Inject

abstract class LocalSource {

    @Inject
    lateinit var db: TasksDatabase

    init {
        ComponentFactory.createComponent(AppComponent::class).inject(this)
    }

}