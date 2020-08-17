package com.abdullah.softpos.data.repository

import com.abdullah.softpos.data.localsource.JobsLocalSource
import com.abdullah.softpos.data.remotesource.JobsRemoteSource
import com.abdullah.softpos.ui.home.model.Job
import com.abdullah.softpos.util.InternetConnection
import javax.inject.Inject

class JobsRepository @Inject constructor(
    val remoteSource: JobsRemoteSource,
    val localSource: JobsLocalSource,
    val internetConnection: InternetConnection
) {
    suspend fun getJobs() =
        if (internetConnection.isNetworkConnected()) remoteSource.getJobs() else localSource.getJobs()

    suspend fun storeJobs(jobs: List<Job>) =
        localSource.clearJobs().also { localSource.storeJobs(jobs) }
}