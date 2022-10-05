package com.leeseungyun1020.learningtrip.network

import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


fun <T> Call<T>.loadNetworkData(target: MutableLiveData<T>, fallback: T? = null) {
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
                if (response.isSuccessful && response.code() == 200 && response.body() != null) {
                    target.postValue(response.body())
                } else if (fallback != null) {
                    target.postValue(fallback)
                }
            }
        }
    )
}