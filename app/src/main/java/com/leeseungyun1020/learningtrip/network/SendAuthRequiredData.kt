package com.leeseungyun1020.learningtrip.network

import android.util.Log
import com.leeseungyun1020.learningtrip.data.TAG
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun <T> Call<T>.sendAuthRequiredData(
    onReloadRequired: (() -> Unit)? = null
) {
    this.enqueue(
        object : Callback<T> {
            override fun onFailure(call: Call<T>, t: Throwable) {
                Log.d(TAG, "onResponse: ${t.message}")
            }

            override fun onResponse(
                call: Call<T>,
                response: Response<T>
            ) {
                Log.d(TAG, "onResponse: ${response}")
                when (response.code()) {
                    200 -> {

                    }
                    401 -> {

                    }
                    else -> {

                    }
                }
            }
        }
    )
}