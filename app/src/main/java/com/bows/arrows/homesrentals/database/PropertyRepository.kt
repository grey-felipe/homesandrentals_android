package com.bows.arrows.homesrentals.database

import androidx.annotation.WorkerThread

class PropertyRepository(private val propertyDao: PropertyDao) {

    @WorkerThread
    suspend fun insert(property: PropertyEntity): Long {
        return propertyDao.addProperty(property)
    }
}