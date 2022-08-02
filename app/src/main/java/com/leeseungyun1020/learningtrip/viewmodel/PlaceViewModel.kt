package com.leeseungyun1020.learningtrip.viewmodel

import android.content.Context
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.leeseungyun1020.learningtrip.data.AppDatabase
import com.leeseungyun1020.learningtrip.data.PlaceRepository
import com.leeseungyun1020.learningtrip.model.Place
import com.opencsv.CSVReader
import kotlinx.coroutines.launch

class PlaceViewModel(context: Context) : ViewModel() {
    private val placeDao = AppDatabase.getDatabase(context).placeDao()
    private val repository: PlaceRepository = PlaceRepository(placeDao)

    val allPlaces = repository.allPlaces.asLiveData()
    val filteredPlaces = MutableLiveData<List<Place>>()
    val filteredPlaceNames = MutableLiveData<List<String>>()
    val placeById = MutableLiveData<Place>()

    init {
        viewModelScope.launch {
            filteredPlaces.postValue(repository.placeByKeyword(""))
            filteredPlaceNames.postValue(repository.searchNameByKeyword(""))
        }
    }

    fun placeById(id: Int) = viewModelScope.launch {
        placeById.postValue(repository.placeById(id))
    }

    fun placeByKeyword(keyword: String) = viewModelScope.launch {
        filteredPlaces.postValue(repository.placeByKeyword(keyword))
    }

    fun placeNameByKeyword(keyword: String) = viewModelScope.launch {
        filteredPlaceNames.postValue(repository.searchNameByKeyword(keyword))
    }

    fun insert(place: Place) = viewModelScope.launch {
        repository.insert(place)
    }

    fun updatePlaceData(context: Context) {
        val sharedPref = context.getSharedPreferences("place_data", Context.MODE_PRIVATE)
        if (sharedPref.getBoolean("place_data_updated", false)) {
            return
        }
        viewModelScope.launch {
            context.assets.open("TourAPI12_a.csv").bufferedReader().apply {
                CSVReader(this).use {
                    for (data in it) {
                        if (!data[0].isDigitsOnly())
                            continue
                        val id: Int = data[0].toInt()
                        val name: String = data[2]
                        val typeId: Int = data[1].toInt()
                        val address: String = data[3]
                        val chkInTextbook: Boolean = data[4].toBoolean()
                        val latitude: Double = data[5].toDoubleOrNull() ?: 0.0
                        val longitude: Double = data[6].toDoubleOrNull() ?: 0.0
                        val tel: String = data[7]
                        val overview: String = data[8]
                        val imageURL: String = data[9]
                        val restDate: String = data[10]
                        val useTime: String = data[11]
                        val chkParking: Boolean = data[12].toBoolean()
                        val chkBabyCarriage: Boolean = data[13].toBoolean()
                        val chkPets: Boolean = data[14].toBoolean()
                        val ageAvailable: String = data[15]
                        val expGuide: String = data[16]
                        val chkWorldCultural: Boolean = data[17].toBoolean()
                        val chkWorldNatural: Boolean = data[18].toBoolean()
                        val chkWorldRecord: Boolean = data[19].toBoolean()
                        val place = Place(
                            id,
                            name,
                            typeId,
                            address,
                            chkInTextbook,
                            latitude,
                            longitude,
                            tel,
                            overview,
                            imageURL,
                            restDate,
                            useTime,
                            chkParking,
                            chkBabyCarriage,
                            chkPets,
                            ageAvailable,
                            expGuide,
                            chkWorldCultural,
                            chkWorldNatural,
                            chkWorldRecord
                        )
                        insert(place)
                    }
                }
            }.close()

            with(sharedPref.edit()) {
                putBoolean("place_data_updated", true)
                apply()
            }
        }
    }
}