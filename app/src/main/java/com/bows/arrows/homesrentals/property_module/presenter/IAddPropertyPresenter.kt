package com.bows.arrows.homesrentals.property_module.presenter

import com.bows.arrows.homesrentals.property_module.model.Property

interface IAddPropertyPresenter {
    fun pushPropertyToRoomDB()
    fun pushMediaToFireBase()
    fun pushPropertyToBackend()
    fun setPropertyObj(obj: Property)
}