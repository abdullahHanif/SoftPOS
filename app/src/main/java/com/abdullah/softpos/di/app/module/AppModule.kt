package com.abdullah.softpos.di.app.module

import android.content.Context
import com.abdullah.softpos.di.app.scope.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
class AppModule(var context: Context) {

    @Provides
    @ApplicationScope
    fun provideContext() = context

    @Provides
    @ApplicationScope
    fun provideResource() = context.resources

}