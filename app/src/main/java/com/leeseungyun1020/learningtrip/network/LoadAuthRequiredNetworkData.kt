package com.leeseungyun1020.learningtrip.network

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.leeseungyun1020.learningtrip.data.TAG
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


fun <T> Call<T>.loadAuthRequiredNetworkData(
    target: MutableLiveData<T>,
    fallback: T? = null,
    onReloadRequired: (() -> Unit)? = null
) {
    this.enqueue(
        object : Callback<T> {
            override fun onFailure(call: Call<T>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.message}")
                if (fallback != null)
                    target.postValue(fallback)
            }

            override fun onResponse(
                call: Call<T>,
                response: Response<T>
            ) {
                Log.d(TAG, "onResponse: ${response.code()}: ${response.body()}")
                when (response.code()) {
                    200 -> {
                        if (response.body() != null)
                            target.postValue(response.body())
                    }
                    401 -> {
                        if (onReloadRequired != null) onReloadRequired()
                    }
                    else -> {
                        if (fallback != null) {
                            target.postValue(fallback)
                        }
                    }
                }
            }
        }
    )
}