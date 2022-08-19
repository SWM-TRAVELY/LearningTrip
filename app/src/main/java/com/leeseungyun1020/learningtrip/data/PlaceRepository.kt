package com.leeseungyun1020.learningtrip.data

import com.leeseungyun1020.learningtrip.model.Place
import com.leeseungyun1020.learningtrip.model.SimplePlace
import kotlinx.coroutines.flow.Flow

class PlaceRepository(private val placeDao: PlaceDao) {
    val allPlaces: Flow<List<Place>> = placeDao.getAll()

    suspend fun recommend() = placeDao.recommendedPlace()

    suspend fun placeById(id: Int) = placeDao.getById(id)

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