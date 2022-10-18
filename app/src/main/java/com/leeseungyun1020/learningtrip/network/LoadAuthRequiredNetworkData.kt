package com.leeseungyun1020.learningtrip.network

import androidx.lifecycle.MutableLiveData
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
                if (fallback != null)
                    target.postValue(fallback)
            }

            override fun onResponse(
                call: Call<T>,
                response: Response<T>
            ) {
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