package com.leeseungyun1020.learningtrip.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leeseungyun1020.learningtrip.data.HeritageRepository
import kotlinx.coroutines.launch

class HeritageViewModel(private val repository: HeritageRepository = HeritageRepository()) :
    ViewModel() {
    val heritage = repository.searchedHeritage

    fun searchById(id: Int) = viewModelScope.launch {
        repository.searchById(id)
    }
}