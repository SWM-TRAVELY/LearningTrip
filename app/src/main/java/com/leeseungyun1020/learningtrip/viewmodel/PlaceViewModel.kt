package com.leeseungyun1020.learningtrip.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.leeseungyun1020.learningtrip.data.AppDatabase
import com.leeseungyun1020.learningtrip.data.PlaceRepository
import com.leeseungyun1020.learningtrip.model.Place
import kotlinx.coroutines.launch

class PlaceViewModel(context: Context) : ViewModel() {
    private val placeDao = AppDatabase.getDatabase(context).placeDao()
    private val repository: PlaceRepository = PlaceRepository(placeDao)

    val allPlaces = repository.allPlaces.asLiveData()
    val filteredPlaces = MutableLiveData<List<Place>>()
    val placeById = MutableLiveData<Place>()

    init {
        viewModelScope.launch {
            filteredPlaces.postValue(repository.placeByKeyword(""))
        }
    }

    fun placeById(id: Int) = viewModelScope.launch {
        placeById.postValue(repository.placeById(id))
    }

    fun placeByKeyword(keyword: String) = viewModelScope.launch {
        Log.e("LSY", "placeByKeyword: ${repository.placeByKeyword(keyword)}")
        filteredPlaces.postValue(repository.placeByKeyword(keyword))
    }

    fun insert(place: Place) = viewModelScope.launch {
        repository.insert(place)
    }
}