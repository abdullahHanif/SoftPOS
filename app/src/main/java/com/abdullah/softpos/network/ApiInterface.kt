package com.abdullah.softpos.network

import kotlinx.coroutines.Deferred
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiInterface {

    //Creating Network request
    @GET
    operator fun get(@Url endpoint: String): Deferred<Response<ResponseBody>>
}