package com.abdullah.softpos.data.remotesource

import com.abdullah.softpos.network.NetworkCall
import com.abdullah.softpos.network.RequestResult
import javax.inject.Inject

class JobsRemoteSource @Inject constructor( val networkCall: NetworkCall){

    suspend fun getJobs(): RequestResult<out Any> {
        return networkCall.get("http://private-14c693-rentapanda.apiary-mock.com/jobs")
    }
}