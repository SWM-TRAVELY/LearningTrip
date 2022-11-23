package com.leeseungyun1020.learningtrip.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.leeseungyun1020.learningtrip.model.course.GroupItem
import com.leeseungyun1020.learningtrip.network.RetrofitClient
import com.leeseungyun1020.learningtrip.network.loadNetworkData

class CategoryRepository {
    private val _category = MutableLiveData<List<GroupItem>>()
    val category: LiveData<List<GroupItem>>
        get() = _category

    fun loadCategory() {
        RetrofitClient.categoryService.getCategory().loadNetworkData(
            target = _category
        )
    }
}