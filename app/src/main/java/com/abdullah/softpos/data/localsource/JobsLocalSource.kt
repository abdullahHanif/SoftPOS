package com.abdullah.softpos.data.localsource

import com.abdullah.softpos.di.app.scope.ApplicationScope
import com.abdullah.softpos.network.RequestResult
import com.abdullah.softpos.ui.home.model.Job
import javax.inject.Inject

@ApplicationScope
class JobsLocalSource @Inject constructor() : LocalSource() {

        //suspend fun storeJobs(jobs: List<Job>) = db.jobsDao().insert(jobs)

    suspend fun storeJobs(jobs: List<Job>) {
        jobs.forEach {
            db.jobsDao().insert(it)
        }
    }

    suspend fun getJobs() = RequestResult.Local<List<Job>>(db.jobsDao().getAll())
    suspend fun clearJobs() = db.jobsDao().deleteAll()

}