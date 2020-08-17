package com.abdullah.softpos.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.abdullah.softpos.db.dao.JobsDao
import com.abdullah.softpos.di.app.scope.ApplicationScope
import com.abdullah.softpos.ui.home.model.Job
import com.abdullah.softpos.ui.home.model.JobTypeConverter


@ApplicationScope
@Database(
    entities = [Job::class],
    version = 2,
    exportSchema = false
)
//@TypeConverters(JobTypeConverter::class)
abstract class TasksDatabase : RoomDatabase() {

    abstract fun jobsDao(): JobsDao
}