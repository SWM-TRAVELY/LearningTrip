package com.leeseungyun1020.learningtrip.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leeseungyun1020.learningtrip.model.Heritage
import com.leeseungyun1020.learningtrip.network.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HeritageViewModel : ViewModel() {
    val heritage = MutableLiveData<Heritage>()

    fun loadHeritage(id: Int) = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            // 추천 키워드
            RetrofitClient.heritageService.getHeritage(id)
                .enqueue(object : Callback<Heritage> {
                    override fun onFailure(call: Call<Heritage>, t: Throwable) {

                    }

                    override fun onResponse(
                        call: Call<Heritage>,
                        response: Response<Heritage>
                    ) {
                        if (response.isSuccessful && response.code() == 200) {
                            val body = response.body()
                            if (body != null) {
                                heritage.postValue(body)
                            }
                        }
                    }
                })
        }
    }
}