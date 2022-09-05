package com.leeseungyun1020.learningtrip.data

import com.leeseungyun1020.learningtrip.model.Course

class CourseRepository(private val courseDao: CourseDao) {
    val all = courseDao.getAll()

    suspend fun getById(id: Int) = courseDao.getById(id)

    suspend fun getPlaceById(id: Int) = courseDao.getPlaceById(id)

    suspend fun insert(course: Course) {
        courseDao.insert(course)
    }

    suspend fun update(course: Course) {
        courseDao.update(course)
    }

    suspend fun delete(course: Course) {
        courseDao.delete(course)
    }

}