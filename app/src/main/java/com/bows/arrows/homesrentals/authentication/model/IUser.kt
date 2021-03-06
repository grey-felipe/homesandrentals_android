package com.bows.arrows.homesrentals.authentication.model

interface IUser {
    fun pushUserDataToBackend(user: User)
    fun onPushError(message:String)
    fun onPushSuccess()
    fun loginUser(user:User)
    fun onLoginError()
    fun onLoginSuccess(token:String)
}