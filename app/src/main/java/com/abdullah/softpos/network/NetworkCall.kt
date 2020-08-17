package com.abdullah.softpos.network

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.abdullah.softpos.di.app.scope.ApplicationScope
import kotlinx.coroutines.Deferred
import okhttp3.ResponseBody
import retrofit2.Response
import javax.inject.Inject

@ApplicationScope
class NetworkCall @Inject constructor(
    protected val apiInterface: ApiInterface,
    protected val context: Context
) {

    suspend inline fun get(endpoint: String): RequestResult<out Any> {
        return generalRequest { apiInterface.get(endpoint) }
    }

    suspend inline fun generalRequest(request: () -> Deferred<Response<ResponseBody>>) =
        try {
            var response = request().await()

            if (response.isSuccessful) {
                var responseString = response.body()?.string()

                Log.d("response", "${responseString}")
                RequestResult.Success(responseString)
            } else {
//            to handle response codes other than 200
                var errorJson = response.errorBody()?.string()
                Log.d("error", "$errorJson")
                handlerError(errorJson, response.code())
                RequestResult.Error(response, errorJson ?: "")
            }
        } catch (exception: Exception) {
//        this catch is like onFailure when we used callbacks
            Log.d("Exception ", exception.message!!)
//       this is custom exception to show network error sendig genral error for now
            RequestResult.Exception(Exception("Internet not connected"))
        }

    fun handlerError(errorResponse: String?, errorCode: Int) {
        when (errorCode) {
//      list of error code to be handled  ie 305 , 400, 405... 500
            500 -> {
                Toast.makeText(context, errorResponse,Toast.LENGTH_LONG).show()
            }
        }
    }

}