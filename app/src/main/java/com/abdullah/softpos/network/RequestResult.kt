package com.abdullah.softpos.network

import okhttp3.ResponseBody
import retrofit2.Response

//this class provide states for request results
sealed class RequestResult<out T : Any> {
    class Success<out T : Any>(val data: T?) : RequestResult<T>()
    class Local<out T : Any>(val data: T?) : RequestResult<T>()
    class Error(val errorBody: Response<ResponseBody>, val errorString : String) : RequestResult<Nothing>()
    class Exception(val exception: java.lang.Exception) : RequestResult<Nothing>()
}