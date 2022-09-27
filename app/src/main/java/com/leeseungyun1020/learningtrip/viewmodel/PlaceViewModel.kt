package com.leeseungyun1020.learningtrip.viewmodel

import android.content.Context
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.leeseungyun1020.learningtrip.data.PlaceRepository
import com.leeseungyun1020.learningtrip.model.Place
import com.leeseungyun1020.learningtrip.model.SimpleHeritage
import com.leeseungyun1020.learningtrip.model.SimplePlace
import com.leeseungyun1020.learningtrip.network.RetrofitClient
import com.opencsv.CSVReader
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PlaceViewModel(private val repository: PlaceRepository) : ViewModel() {
    val isUpdated = MutableLiveData(false)
    val recommendedPlaces = repository.recommededPlaces
    val filteredPlaces = MutableLiveData<List<SimplePlace>>()
    val filteredPlaceNames = MutableLiveData<List<String>>()
    val relatedHeritages = MutableLiveData<List<SimpleHeritage>>()
    val relatedPlaces = MutableLiveData<List<SimplePlace>>()
    val nearbyPlaces = MutableLiveData<List<SimplePlace>>()
    val placeById = repository.searchedPlace

    init {
        viewModelScope.launch {
            filteredPlaces.postValue(repository.placeByKeyword(""))
            filteredPlaceNames.postValue(repository.searchNameByKeyword(""))
        }
    }

    fun loadPlaceSpecific(id: Int) {
        placeById(id)
        loadRelatedHeritages(id)
        loadRelatedPlaces(id)
        loadNearbyPlaces(id)
    }

    fun recommend() = viewModelScope.launch {
        repository.recommend()
    }

    fun placeById(id: Int) = viewModelScope.launch {
        repository.placeById(id)
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
            isUpdated.postValue(true)
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
            isUpdated.postValue(true)
        }
    }

    fun loadRelatedHeritages(placeId: Int) = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            RetrofitClient.heritageService.getRelatedHeritage(placeId)
                .enqueue(object : Callback<List<SimpleHeritage>> {
                    override fun onFailure(call: Call<List<SimpleHeritage>>, t: Throwable) {

                    }

                    override fun onResponse(
                        call: Call<List<SimpleHeritage>>,
                        response: Response<List<SimpleHeritage>>
                    ) {
                        if (response.isSuccessful && response.code() == 200) {
                            val body = response.body()
                            if (body != null) {
                                relatedHeritages.postValue(body)
                            }
                        }
                    }
                })
        }
    }

    fun loadRelatedPlaces(placeId: Int) = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            RetrofitClient.placeService.getRelatedPlace(placeId)
                .enqueue(object : Callback<List<SimplePlace>> {
                    override fun onFailure(call: Call<List<SimplePlace>>, t: Throwable) {

                    }

                    override fun onResponse(
                        call: Call<List<SimplePlace>>,
                        response: Response<List<SimplePlace>>
                    ) {
                        if (response.isSuccessful && response.code() == 200) {
                            val body = response.body()
                            if (body != null) {
                                relatedPlaces.postValue(body)
                            }
                        }
                    }
                })
        }
    }

    fun loadNearbyPlaces(placeId: Int) = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            RetrofitClient.placeService.getNearbyPlace(placeId)
                .enqueue(object : Callback<List<SimplePlace>> {
                    override fun onFailure(call: Call<List<SimplePlace>>, t: Throwable) {

                    }

                    override fun onResponse(
                        call: Call<List<SimplePlace>>,
                        response: Response<List<SimplePlace>>
                    ) {
                        if (response.isSuccessful && response.code() == 200) {
                            val body = response.body()
                            if (body != null) {
                                nearbyPlaces.postValue(body)
                            }
                        }
                    }
                })
        }
    }
}

class PlaceViewModelFactory(private val repository: PlaceRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PlaceViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PlaceViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}