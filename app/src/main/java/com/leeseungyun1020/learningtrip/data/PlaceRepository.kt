package com.leeseungyun1020.learningtrip.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.leeseungyun1020.learningtrip.model.Place
import com.leeseungyun1020.learningtrip.model.SimplePlace
import com.leeseungyun1020.learningtrip.network.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PlaceRepository(private val placeDao: PlaceDao) {
    val allPlaces: Flow<List<Place>> = placeDao.getAll()
    val recommededPlaces: MutableLiveData<List<SimplePlace>> = MutableLiveData()
    val searchedPlace: MutableLiveData<Place> = MutableLiveData()

    suspend fun recommend() {
        withContext(Dispatchers.IO) {
            val dbData = placeDao.recommendedPlace()
            RetrofitClient.placeService.getRecommendPlaceList()
                .enqueue(object : Callback<List<SimplePlace>> {
                    override fun onFailure(call: Call<List<SimplePlace>>, t: Throwable) {
                        Log.d("LSYD", "onFailure: recommend $call $t")
                        recommededPlaces.postValue(dbData)
                    }

                    override fun onResponse(
                        call: Call<List<SimplePlace>>,
                        response: Response<List<SimplePlace>>
                    ) {
                        if (response.isSuccessful && response.code() == 200) {
                            val body = response.body()
                            Log.d("LSYD", "onResponse: recommend $body")
                            if (body != null) {
                                recommededPlaces.postValue(body ?: dbData)
                            }
                        }
                    }
                })
        }
    }

    suspend fun placeById(id: Int) {
        withContext(Dispatchers.IO) {
            val dbData = placeDao.getById(id)
            RetrofitClient.placeService.getPlace(id)
                .enqueue(object : Callback<Place> {
                    override fun onFailure(call: Call<Place>, t: Throwable) {
                        Log.d("LSYD", "onFailure: placeById $call $t")
                        searchedPlace.postValue(dbData)
                    }

                    override fun onResponse(
                        call: Call<Place>,
                        response: Response<Place>
                    ) {
                        if (response.isSuccessful && response.code() == 200) {
                            val body = response.body()
                            Log.d("LSYD", "onResponse: placeById $body")
                            if (body != null) {
                                searchedPlace.postValue(body ?: dbData)
                            }
                        }
                    }
                })
        }
    }

    suspend fun placeByKeyword(keyword: String): List<SimplePlace> {
        return placeDao.findByKeyword(keyword)
    }

    suspend fun searchNameByKeyword(keyword: String): List<String> {
        return placeDao.searchNameByKeyword(keyword)
    }

    suspend fun insert(place: Place) {
        placeDao.insert(place)
    }
}