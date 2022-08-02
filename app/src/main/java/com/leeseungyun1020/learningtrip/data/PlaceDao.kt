package com.leeseungyun1020.learningtrip.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.leeseungyun1020.learningtrip.model.Place
import kotlinx.coroutines.flow.Flow

@Dao
interface PlaceDao {
    @Insert
    suspend fun insert(place: Place)

    @Query("SELECT * FROM place")
    fun getAll(): Flow<List<Place>>

    @Query("SELECT * FROM place WHERE id = :id")
    suspend fun getById(id: Int): Place

    @Query("SELECT * FROM place WHERE name LIKE '%' || :keyword || '%' OR address LIKE '%' || :keyword || '%'")
    suspend fun findByKeyword(keyword: String): List<Place>

    @Query("SELECT name FROM place WHERE name LIKE '%' || :keyword || '%' LIMIT 16")
    suspend fun searchNameByKeyword(keyword: String): List<String>
}