package com.bows.arrows.homesrentals.property_module.presenter

import android.net.Uri
import com.bows.arrows.homesrentals.property_module.view.AddPropertyViewModel

interface IAddPropertyPresenter {
    fun validatePropertyMedia(list: List<Uri>, viewModel: AddPropertyViewModel)
    fun onPushError(message: String)
    fun onPushSuccess(message: String)
}