package com.leeseungyun1020.learningtrip.data

import androidx.room.*
import com.leeseungyun1020.learningtrip.model.Course
import com.leeseungyun1020.learningtrip.model.SimplePlace
import kotlinx.coroutines.flow.Flow

@Dao
interface CourseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(course: Course)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(course: Course)

    @Delete
    suspend fun delete(course: Course)

    @Query("SELECT * FROM course")
    fun getAll(): Flow<List<Course>>

    @Query("SELECT * FROM course WHERE id = :id")
    suspend fun getById(id: Int): Course

    @Query("SELECT * FROM place WHERE id = :id")
    suspend fun getPlaceById(id: Int): SimplePlace
}