package com.bows.arrows.homesrentals.property_module.view

import com.bows.arrows.homesrentals.property_module.model.Property

interface IPropertyInfoView {
    fun onValidationError(message: String)
    fun startLocationFragment(property: Property)
}