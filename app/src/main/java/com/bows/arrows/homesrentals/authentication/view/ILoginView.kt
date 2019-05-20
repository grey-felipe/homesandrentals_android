package com.bows.arrows.homesrentals.authentication.view

interface ILoginView {
    fun loadSignUpFragment()
    fun submitUserData()
    fun onValidationError(value: String)
    fun onSubmitError()
    fun onSubmitSuccess(token: String)
}