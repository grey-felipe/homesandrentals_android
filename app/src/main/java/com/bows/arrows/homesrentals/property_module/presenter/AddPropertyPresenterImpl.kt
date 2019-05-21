package com.bows.arrows.homesrentals.property_module.presenter

import com.bows.arrows.homesrentals.property_module.model.Property
import com.bows.arrows.homesrentals.property_module.view.IAddPropertyView

class AddPropertyPresenterImpl(private val iAddPropertyView: IAddPropertyView) : IAddPropertyPresenter {

    override fun validatePropertyData(
        title: String,
        price: Int,
        currency: String,
        type: String,
        available: Boolean,
        description: String,
        location: String,
        coordinates: String,
        media: List<String>
    ) {
        when {
            title.isEmpty() || title.isBlank() -> {
                iAddPropertyView.onValidationError("Title")
            }
            price <= 0 -> {
                iAddPropertyView.onValidationError("Price")
            }
            currency.isEmpty() || currency.isBlank() -> {
                iAddPropertyView.onValidationError("Currency")
            }
            type.isEmpty() || type.isBlank() -> {
                iAddPropertyView.onValidationError("Type")
            }
            description.isEmpty() || description.isBlank() -> {
                iAddPropertyView.onValidationError("Description")
            }
            location.isEmpty() || location.isBlank() -> {
                iAddPropertyView.onValidationError("Location")
            }
            coordinates.isEmpty() || coordinates.isBlank() -> {
                iAddPropertyView.onValidationError("Coordinates")
            }
            media.isEmpty() -> {
                iAddPropertyView.onValidationError("Photos/Videos")
            }
            else -> {
                val property = Property(
                    title,
                    price, currency, type,
                    available, description, location,
                    coordinates, media
                )
                property.getPresenterImpl(this)
                property.pushPropertyDataToBackend(property)

            }
        }
    }

    override fun onPushError() {
        iAddPropertyView.onSubmitError()
    }

    override fun onPushSuccess() {
        iAddPropertyView.onSubmitSuccess()
    }
}