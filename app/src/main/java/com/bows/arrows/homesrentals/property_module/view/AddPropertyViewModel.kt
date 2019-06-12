package com.bows.arrows.homesrentals.property_module.view

import android.net.Uri
import androidx.lifecycle.ViewModel
import com.bows.arrows.homesrentals.property_module.model.Property
import com.google.android.gms.maps.model.LatLng

class AddPropertyViewModel : ViewModel() {
    private lateinit var property: Property

    fun setPropertyObject(obj: Property) {
        property = obj
    }

    fun addLocation(location: String, latLng: LatLng) {
        val sb = StringBuilder()
        val coordinates = sb.append(latLng.latitude).append(",").append(latLng.longitude).toString()
        //add location and coordinates to property object
        property.coordinates = coordinates
        property.location = location
    }

    fun addMedia(list: List<Uri>) {
        property.media = list
    }

    fun getPropertyObject(): Property {
        return property
    }
}