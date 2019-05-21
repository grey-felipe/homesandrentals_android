package com.bows.arrows.homesrentals.property_module.presenter

interface IAddPropertyPresenter {
    fun validatePropertyData(
        title: String, price: Int, currency: String,
        type: String, available: Boolean, description: String,
        location: String, coordinates: String, media: List<String>
    )
    fun onPushError()
    fun onPushSuccess()
}