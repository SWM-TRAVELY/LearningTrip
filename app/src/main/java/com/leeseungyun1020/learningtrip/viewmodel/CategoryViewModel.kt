package com.leeseungyun1020.learningtrip.viewmodel

import androidx.lifecycle.ViewModel
import com.leeseungyun1020.learningtrip.data.CategoryRepository

class CategoryViewModel(private val repository: CategoryRepository = CategoryRepository()) :
    ViewModel() {
    val category = repository.category

    fun loadCategory() {
        repository.loadCategory()
    }
}