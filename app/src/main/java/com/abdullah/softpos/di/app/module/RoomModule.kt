package com.abdullah.softpos.di.app.module

import android.content.Context
import androidx.room.Room
import com.abdullah.softpos.R
import com.abdullah.softpos.db.TasksDatabase
import dagger.Module
import dagger.Provides

@Module
object RoomModule {

    @Synchronized
    @JvmStatic
    @Provides
    fun providesDatabase(context: Context) =
        Room.databaseBuilder(
            context,
            TasksDatabase::class.java,
            context.getString(R.string.db_name)
        ).build()

}