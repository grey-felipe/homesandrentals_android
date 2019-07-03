package com.bows.arrows.homesrentals.property_module.presenter.info_presenter

interface IPropertyInfoPresenter {
    fun validatePropertyInfo(
        title: String, price: Int, currency: String,
        type: String, available: Boolean, description: String
    )
}