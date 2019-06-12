package com.bows.arrows.homesrentals.authentication.view

interface ISignUpView {
    fun submitUserData()
    fun onValidationError(value: String)
    fun onSubmitError(message:String)
    fun onSubmitSuccess()
}