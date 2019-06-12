package com.bows.arrows.homesrentals.property_module.model

import android.net.Uri

interface IProperty {
    fun pushPropertyDataToBackend(property: Property)
    fun pushMediaToFireBase(list: List<Uri>, index: Int): List<String>
    fun onPushError(message: String)
    fun onPushSuccess(message: String)
}