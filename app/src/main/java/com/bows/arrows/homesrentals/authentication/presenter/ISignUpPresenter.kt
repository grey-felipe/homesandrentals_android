package com.bows.arrows.homesrentals.authentication.presenter

interface ISignUpPresenter {
    fun validateData(username: String, email: String, phone: String, password: String)
    fun onPushError(message:String)
    fun onPushSuccess()
}