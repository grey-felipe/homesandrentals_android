package com.bows.arrows.homesrentals.property_module.presenter.info_presenter

import com.bows.arrows.homesrentals.property_module.model.Property
import com.bows.arrows.homesrentals.property_module.view.info_fragment.IPropertyInfoView

class PropertyInfoPresenterImpl(private val view: IPropertyInfoView) : IPropertyInfoPresenter {
    override fun validatePropertyInfo(
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
                val list = emptyList<Any>()
                val property = Property(
                    title, price, currency, type,
                    available, description, media = list
                )
                view.passPropertyToViewModel(property)
            }
        }
    }

}