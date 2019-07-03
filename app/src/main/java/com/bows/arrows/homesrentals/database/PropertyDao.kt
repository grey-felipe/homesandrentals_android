package com.bows.arrows.homesrentals.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PropertyDao {
    @Insert
    suspend fun addProperty(property: PropertyEntity): Long

    @Query("SELECT * FROM property_table WHERE title = :title")
    fun findPropertyByTitle(title: String): LiveData<PropertyEntity>

    @Query("SELECT * FROM property_table")
    fun getAllProperty(): LiveData<List<PropertyEntity>>

    @Query("DELETE FROM property_table WHERE id = :id")
    fun deleteProperty(id: Int)
}