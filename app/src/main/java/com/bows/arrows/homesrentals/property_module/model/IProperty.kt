package com.bows.arrows.homesrentals.property_module.model

interface IProperty {
    fun pushPropertyDataToBackend(property: Property)
    fun onPushError()
    fun onPushSuccess()
}