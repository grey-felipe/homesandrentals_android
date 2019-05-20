package com.bows.arrows.homesrentals.authentication.presenter

interface ILoginPresenter {
    fun validateData(email: String, password: String)
    fun onLoginError()
    fun onLoginSuccess(token: String)
}