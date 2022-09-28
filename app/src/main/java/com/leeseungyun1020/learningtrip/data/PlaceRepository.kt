package com.leeseungyun1020.learningtrip.data

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
    val recommendedPlaces: MutableLiveData<List<SimplePlace>> = MutableLiveData()
    val searchedPlace: MutableLiveData<Place> = MutableLiveData()
    val filteredPlaces: MutableLiveData<List<SimplePlace>> = MutableLiveData()
    val filteredPlaceNames: MutableLiveData<List<String>> = MutableLiveData()

    suspend fun recommend() {
        withContext(Dispatchers.IO) {
            val dbData = placeDao.recommendedPlace()
            RetrofitClient.homeService.getRecommendPlace()
                .enqueue(object : Callback<List<SimplePlace>> {
                    override fun onFailure(call: Call<List<SimplePlace>>, t: Throwable) {
                        recommendedPlaces.postValue(dbData)
                    }

                    override fun onResponse(
                        call: Call<List<SimplePlace>>,
                        response: Response<List<SimplePlace>>
                    ) {
                        if (response.isSuccessful && response.code() == 200) {
                            val body = response.body()
                            if (body != null) {
                                recommendedPlaces.postValue(body)
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
                        searchedPlace.postValue(dbData)
                    }

                    override fun onResponse(
                        call: Call<Place>,
                        response: Response<Place>
                    ) {
                        if (response.isSuccessful && response.code() == 200) {
                            val body = response.body()
                            if (body != null) {
                                searchedPlace.postValue(body)
                            }
                        }
                    }
                })
        }
    }

    suspend fun placeByKeyword(keyword: String) {
        withContext(Dispatchers.IO) {
            val dbData = placeDao.findByKeyword(keyword)
            RetrofitClient.searchService.getSearchedPlaceList(keyword)
                .enqueue(object : Callback<List<SimplePlace>> {
                    override fun onFailure(call: Call<List<SimplePlace>>, t: Throwable) {
                        filteredPlaces.postValue(dbData)
                    }

                    override fun onResponse(
                        call: Call<List<SimplePlace>>,
                        response: Response<List<SimplePlace>>
                    ) {
                        if (response.isSuccessful && response.code() == 200) {
                            val body = response.body()
                            if (body != null) {
                                filteredPlaces.postValue(body)
                            }
                        }
                    }
                })
        }
    }

    suspend fun searchNameByKeyword(keyword: String) {
        withContext(Dispatchers.IO) {
            val dbData = placeDao.searchNameByKeyword(keyword)
            RetrofitClient.searchService.getSearchedKeyword(keyword)
                .enqueue(object : Callback<List<String>> {
                    override fun onFailure(call: Call<List<String>>, t: Throwable) {
                        filteredPlaceNames.postValue(dbData)
                    }

                    override fun onResponse(
                        call: Call<List<String>>,
                        response: Response<List<String>>
                    ) {
                        if (response.isSuccessful && response.code() == 200) {
                            val body = response.body()
                            if (body != null) {
                                filteredPlaceNames.postValue(body)
                            }
                        }
                    }
                })
        }
    }

    suspend fun insert(place: Place) {
        placeDao.insert(place)
    }
}