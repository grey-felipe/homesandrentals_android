package com.bows.arrows.homesrentals.property_module.presenter.media_presenter

import android.net.Uri
import com.bows.arrows.homesrentals.property_module.view.AddPropertyViewModel

interface IPropertyMediaPresenter {
    fun validatePropertyMedia(list: List<Uri>, viewModel: AddPropertyViewModel)
}