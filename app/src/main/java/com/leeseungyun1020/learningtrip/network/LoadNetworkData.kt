package com.leeseungyun1020.learningtrip.network

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.leeseungyun1020.learningtrip.data.TAG
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


fun <T> Call<T>.loadNetworkData(
    target: MutableLiveData<T>,
    fallback: T? = null,
    onSuccess: ((T?) -> Unit)? = null,
    printLog: Boolean = false
) {
    this.enqueue(
        object : Callback<T> {
            override fun onFailure(call: Call<T>, t: Throwable) {
                if (fallback != null)
                    target.postValue(fallback)
                if (printLog)
                    Log.d(TAG, "onFailure: $t")
            }

            override fun onResponse(
                call: Call<T>,
                response: Response<T>
            ) {
                Log.d(TAG, "onResponse: $response ${response.body()}")
                if (response.isSuccessful && response.code() == 200 && response.body() != null) {
                    target.postValue(response.body())
                    onSuccess?.run { this(response.body()) }
                } else if (fallback != null) {
                    target.postValue(fallback)
                }
            }
        }
    )
}