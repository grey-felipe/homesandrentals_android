package com.bows.arrows.homesrentals.property_module.presenter

interface IPropertyInfoPresenter {
    fun validatePropertyData(
        title: String, price: Int, currency: String,
        type: String, available: Boolean, description: String)
}