package com.bows.arrows.homesrentals.authentication.base_contracts

import android.content.Context

interface IBaseAuthView {
    fun getFragContext(): Context
    fun onValidationError(error: String)
    fun onError(error: String)
    fun onSuccess(message: String)
}