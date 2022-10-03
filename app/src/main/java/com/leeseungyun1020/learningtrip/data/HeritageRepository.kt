package com.leeseungyun1020.learningtrip.data

import androidx.lifecycle.MutableLiveData
import com.leeseungyun1020.learningtrip.model.Heritage
import com.leeseungyun1020.learningtrip.network.RetrofitClient
import com.leeseungyun1020.learningtrip.network.loadNetworkData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HeritageRepository {
    val searchedHeritage = MutableLiveData<Heritage>()

    suspend fun searchById(id: Int) {
        withContext(Dispatchers.IO) {
            RetrofitClient.heritageService.getHeritage(id).loadNetworkData(
                target = searchedHeritage
            )
        }
    }
}