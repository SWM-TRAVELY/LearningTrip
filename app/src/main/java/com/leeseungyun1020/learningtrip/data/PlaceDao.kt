package com.leeseungyun1020.learningtrip.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.leeseungyun1020.learningtrip.model.Place
import com.leeseungyun1020.learningtrip.model.SimplePlace
import kotlinx.coroutines.flow.Flow

@Dao
interface PlaceDao {
    @Insert
    suspend fun insert(place: Place)

    @Query("SELECT * FROM place")
    fun getAll(): Flow<List<Place>>

    @Query("SELECT id, name, typeId, address, imageURL, overview FROM place ORDER BY RANDOM() LIMIT 4")
    suspend fun recommendedPlace(): List<SimplePlace>

    @Query("SELECT * FROM place WHERE id = :id")
    suspend fun getById(id: Int): Place

    @Query("SELECT id, name, typeId, address, imageURL, overview FROM place WHERE name LIKE '%' || :keyword || '%' OR address LIKE '%' || :keyword || '%' LIMIT 16")
    suspend fun findByKeyword(keyword: String): List<SimplePlace>

    @Query("SELECT name FROM place WHERE name LIKE '%' || :keyword || '%' LIMIT 16")
    suspend fun searchNameByKeyword(keyword: String): List<String>

    @Query("DELETE FROM place")
    suspend fun deleteAll()
}