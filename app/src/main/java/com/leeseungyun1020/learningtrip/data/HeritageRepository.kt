package com.leeseungyun1020.learningtrip.data

import androidx.lifecycle.MutableLiveData
import com.leeseungyun1020.learningtrip.model.Heritage
import com.leeseungyun1020.learningtrip.network.RetrofitClient
import com.leeseungyun1020.learningtrip.network.loadNetworkData

class HeritageRepository {
    val searchedHeritage = MutableLiveData<Heritage>()

    fun searchById(id: Int) {
        RetrofitClient.heritageService.getHeritage(id).loadNetworkData(
            target = searchedHeritage
        )
    }
}