package com.leeseungyun1020.learningtrip.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.leeseungyun1020.learningtrip.data.HeritageRepository
import com.leeseungyun1020.learningtrip.model.Heritage

class HeritageViewModel(private val repository: HeritageRepository = HeritageRepository()) :
    ViewModel() {
    val heritage: LiveData<Heritage>
        get() = repository.searchedHeritage

    fun searchById(id: Int) = repository.searchById(id)

}