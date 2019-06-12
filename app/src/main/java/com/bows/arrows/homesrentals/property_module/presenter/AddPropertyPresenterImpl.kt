package com.bows.arrows.homesrentals.property_module.presenter

import android.net.Uri
import com.bows.arrows.homesrentals.property_module.model.Property
import com.bows.arrows.homesrentals.property_module.view.AddPropertyViewModel
import com.bows.arrows.homesrentals.property_module.view.IPropertyMediaView

class AddPropertyPresenterImpl(private val view: IPropertyMediaView) : IAddPropertyPresenter {

    private lateinit var property: Property

    override fun validatePropertyMedia(list: List<Uri>, viewModel: AddPropertyViewModel) {
        when {
            list.isEmpty() -> {
                view.onValidationError("images")
            }
            else -> {
                property = viewModel.getPropertyObject()
                property.getPresenterImpl(this)
                property.media = property.pushMediaToFireBase(list, 0)
            }
        }
    }

    override fun onPushError(message: String) {
    }

    override fun onPushSuccess(message: String) {
    }
}