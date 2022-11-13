package com.leeseungyun1020.learningtrip.viewmodel

import android.content.Context
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.*
import com.leeseungyun1020.learningtrip.data.PlaceRepository
import com.leeseungyun1020.learningtrip.model.Place
import com.leeseungyun1020.learningtrip.model.SimpleHeritage
import com.leeseungyun1020.learningtrip.model.SimplePlace
import com.opencsv.CSVReader
import kotlinx.coroutines.launch

class PlaceViewModel(private val repository: PlaceRepository) : ViewModel() {
    private val _isUpdated = MutableLiveData(false)
    val isUpdated: LiveData<Boolean>
        get() = _isUpdated

    val recommendedPlaces: LiveData<List<SimplePlace>> = repository.recommendedPlaces
    val filteredPlaces: LiveData<List<SimplePlace>> = repository.filteredPlaces
    val filteredPlaceNames: LiveData<List<String>> = repository.filteredPlaceNames
    val relatedHeritages: LiveData<List<SimpleHeritage>> = repository.relatedHeritages
    val relatedPlaces: LiveData<List<SimplePlace>> = repository.relatedPlaces
    val nearbyPlaces: LiveData<List<SimplePlace>> = repository.nearbyPlaces
    val placeById: LiveData<Place> = repository.searchedPlace

    init {
        viewModelScope.launch {
            repository.searchByKeyword("")
            repository.searchNameByKeyword("")
        }
    }

    fun loadPlaceSpecific(id: Int) {
        placeById(id)
        loadRelatedHeritages(id)
        loadRelatedPlaces(id)
        loadNearbyPlaces(id)
    }

    private fun placeById(id: Int) = viewModelScope.launch {
        repository.searchById(id)
    }

    private fun loadRelatedHeritages(placeId: Int) = viewModelScope.launch {
        repository.loadRelatedHeritages(placeId)
    }

    private fun loadRelatedPlaces(placeId: Int) = viewModelScope.launch {
        repository.loadRelatedPlaces(placeId)
    }

    private fun loadNearbyPlaces(placeId: Int) = viewModelScope.launch {
        repository.loadNearbyPlaces(placeId)
    }

    fun recommend() = viewModelScope.launch {
        repository.recommend()
    }

    fun placeByKeyword(keyword: String) = viewModelScope.launch {
        repository.searchByKeyword(keyword)
    }

    fun placeNameByKeyword(keyword: String) = viewModelScope.launch {
        repository.searchNameByKeyword(keyword)
    }

    private fun insert(place: Place) = viewModelScope.launch {
        repository.insert(place)
    }

    private fun deleteAll() = viewModelScope.launch {
        repository.deleteAll()
    }

    fun updatePlaceData(context: Context) {
        val sharedPref = context.getSharedPreferences("place_data", Context.MODE_PRIVATE)
        when (sharedPref.getString("version", "1.0.0")) {
            "1.1.0" -> {
                _isUpdated.postValue(true)
            }
            else -> {
                deleteAll()
                viewModelScope.launch {
                    // TODO: CSV 파일 교체
                    context.assets.open("TourAPI12_a.csv").bufferedReader().apply {
                        CSVReader(this).use {
                            for (data in it) {
                                if (!data[0].isDigitsOnly())
                                    continue
                                val id: Long = data[0].toLong()
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

                    sharedPref.edit().putString("version", "1.1.0").apply()
                    _isUpdated.postValue(true)
                }
            }
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