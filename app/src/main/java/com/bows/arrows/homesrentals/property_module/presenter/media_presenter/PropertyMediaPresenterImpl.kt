package com.bows.arrows.homesrentals.property_module.presenter.media_presenter

import android.net.Uri
import com.bows.arrows.homesrentals.property_module.model.Property
import com.bows.arrows.homesrentals.property_module.view.AddPropertyViewModel
import com.bows.arrows.homesrentals.property_module.view.media_fragment.IPropertyMediaView

class PropertyMediaPresenterImpl(private val view: IPropertyMediaView) : IPropertyMediaPresenter {

    private lateinit var property: Property

    override fun validatePropertyMedia(list: List<Uri>, viewModel: AddPropertyViewModel) {
        when {
            list.isEmpty() -> {
                view.onValidationError("images")
            }
            else -> {
                property = viewModel.getPropertyObject()
                property.media = list
                view.startReviewFragment()
            }
        }
    }
}