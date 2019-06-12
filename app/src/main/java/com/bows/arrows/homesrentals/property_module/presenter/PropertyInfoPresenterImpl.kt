package com.bows.arrows.homesrentals.property_module.presenter

import com.bows.arrows.homesrentals.property_module.model.Property
import com.bows.arrows.homesrentals.property_module.view.IPropertyInfoView

class PropertyInfoPresenterImpl(private val view: IPropertyInfoView) : IPropertyInfoPresenter {
    override fun validatePropertyData(
        title: String,
        price: Int,
        currency: String,
        type: String,
        available: Boolean,
        description: String
    ) {

        when {
            title.isEmpty() || title.isBlank() -> {
                view.onValidationError("Title")
            }
            price <= 0 -> {
                view.onValidationError("Price")
            }
            currency.isEmpty() || currency.isBlank() -> {
                view.onValidationError("Currency")
            }
            type.isEmpty() || type.isBlank() -> {
                view.onValidationError("Type")
            }
            description.isEmpty() || description.isBlank() -> {
                view.onValidationError("Description")
            }
            else -> {
                val list = mutableListOf("")
                val property = Property(
                    title, price, currency, type,
                    available, description, media = list
                )
                view.startLocationFragment(property)
            }
        }
    }
}