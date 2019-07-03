package com.bows.arrows.homesrentals.property_module.view.info_fragment

import com.bows.arrows.homesrentals.property_module.model.Property
import com.bows.arrows.homesrentals.property_module.view.IAddPropertyBaseView

interface IPropertyInfoView : IAddPropertyBaseView {
    fun startLocationFragment()
    fun passPropertyToViewModel(property: Property)
}