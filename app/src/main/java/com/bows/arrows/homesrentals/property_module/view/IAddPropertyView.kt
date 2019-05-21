package com.bows.arrows.homesrentals.property_module.view

interface IAddPropertyView {
    fun submitPropertyData()
    fun onValidationError(message:String)
    fun onSubmitError()
    fun onSubmitSuccess()
}