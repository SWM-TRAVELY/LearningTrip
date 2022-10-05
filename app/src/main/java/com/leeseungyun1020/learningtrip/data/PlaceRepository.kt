package com.leeseungyun1020.learningtrip.data

import androidx.lifecycle.MutableLiveData
import com.leeseungyun1020.learningtrip.model.Place
import com.leeseungyun1020.learningtrip.model.SimpleHeritage
import com.leeseungyun1020.learningtrip.model.SimplePlace
import com.leeseungyun1020.learningtrip.network.RetrofitClient
import com.leeseungyun1020.learningtrip.network.loadNetworkData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class PlaceRepository(private val placeDao: PlaceDao) {
    val allPlaces: Flow<List<Place>> = placeDao.getAll()
    val recommendedPlaces = MutableLiveData<List<SimplePlace>>()
    val searchedPlace = MutableLiveData<Place>()
    val filteredPlaces = MutableLiveData<List<SimplePlace>>()
    val filteredPlaceNames = MutableLiveData<List<String>>()
    val relatedHeritages = MutableLiveData<List<SimpleHeritage>>()
    val relatedPlaces = MutableLiveData<List<SimplePlace>>()
    val nearbyPlaces = MutableLiveData<List<SimplePlace>>()

    suspend fun recommend() {
        withContext(Dispatchers.IO) {
            RetrofitClient.homeService.getRecommendPlace().loadNetworkData(
                target = recommendedPlaces,
                fallback = placeDao.recommendedPlace()
            )
        }
    }

    suspend fun searchById(id: Int) {
        withContext(Dispatchers.IO) {
            RetrofitClient.placeService.getPlace(id).loadNetworkData(
                target = searchedPlace,
                fallback = placeDao.getById(id)
            )
        }
    }

    suspend fun searchByKeyword(keyword: String) {
        withContext(Dispatchers.IO) {
            RetrofitClient.searchService.getSearchedPlaceList(keyword).loadNetworkData(
                target = filteredPlaces,
                fallback = placeDao.findByKeyword(keyword)
            )
        }
    }

    suspend fun searchNameByKeyword(keyword: String) {
        withContext(Dispatchers.IO) {
            RetrofitClient.searchService.getSearchedKeyword(keyword).loadNetworkData(
                target = filteredPlaceNames,
                fallback = placeDao.searchNameByKeyword(keyword)
            )
        }
    }

    suspend fun insert(place: Place) {
        placeDao.insert(place)
    }

    suspend fun loadRelatedHeritages(placeId: Int) {
        withContext(Dispatchers.IO) {
            RetrofitClient.heritageService.getRelatedHeritage(placeId).loadNetworkData(
                target = relatedHeritages
            )
        }
    }

    suspend fun loadRelatedPlaces(placeId: Int) {
        withContext(Dispatchers.IO) {
            RetrofitClient.placeService.getRelatedPlace(placeId).loadNetworkData(
                target = relatedPlaces
            )
        }
    }

    suspend fun loadNearbyPlaces(placeId: Int) {
        withContext(Dispatchers.IO) {
            RetrofitClient.placeService.getNearbyPlace(placeId).loadNetworkData(
                target = nearbyPlaces
            )
        }
    }
}