package com.bows.arrows.homesrentals.database

import androidx.room.Dao
import androidx.room.Query
import com.bows.arrows.homesrentals.property_module.model.Property

@Dao
interface PropertyDao {
    @Query("")
    fun addProperty(property: Property)

    @Query("")
    fun findPropertyByTitle(title: Property)

    @Query("")
    fun getAllProperty()

    @Query("")
    fun deleteProperty()
}