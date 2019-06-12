package com.bows.arrows.homesrentals.authentication.model

import com.bows.arrows.homesrentals.api.RetrofitClient
import com.bows.arrows.homesrentals.authentication.presenter.LoginPresenterImpl
import com.bows.arrows.homesrentals.authentication.presenter.SignUpPresenterImpl
import com.google.gson.annotations.SerializedName
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


data class User(
    @SerializedName("username")
    val username: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("bio")
    var bio: String = "",
    @SerializedName("password")
    val password: String,
    @SerializedName("isActive")
    var isActive: Boolean = true,
    @SerializedName("image")
    var image: String = "",
    @SerializedName("token")
    var token: String = ""
) : IUser {

    private lateinit var signUpPresenterImplObj: SignUpPresenterImpl
    private lateinit var loginPresenterImplObj: LoginPresenterImpl

    fun getSignUpPresenterObj(obj: SignUpPresenterImpl) {
        signUpPresenterImplObj = obj
    }

    fun getLoginPresenterObj(obj: LoginPresenterImpl) {
        loginPresenterImplObj = obj
    }

    //Register user
    override fun pushUserDataToBackend(user: User) {
        val map = HashMap<String, Any>()
        map["username"] = user.username
        map["email"] = user.email
        map["phone"] = user.phone
        map["bio"] = ""
        map["password"] = user.password
        map["isActive"] = true
        map["image"] = ""

        val userMap = HashMap<String, HashMap<String, Any>>()
        userMap["user"] = map

        val disposable = RetrofitClient.instance
            .registerUser(userMap)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                when (it.code()) {
                    400 -> {
                        onPushError("User already exists")
                    }
                    201 -> {
                        onPushSuccess()
                    }
                }
            }, { error -> onPushError(error.cause.toString()) })
    }

    override fun onPushError(message: String) {
        signUpPresenterImplObj.onPushError(message)
    }

    override fun onPushSuccess() {
        signUpPresenterImplObj.onPushSuccess()
    }

    //Login user
    override fun loginUser(user: User) {
        val map = HashMap<String, Any>()
        map["email"] = user.email
        map["password"] = user.password

        val userMap = HashMap<String, HashMap<String, Any>>()
        userMap["user"] = map

        val disposable = RetrofitClient.instance
            .loginUser(userMap)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                when (it.code()) {
                    400 -> {
                        onLoginError()
                    }
                    200 -> {
                        val token: String = it.body()!!.user.token
                        onLoginSuccess(token)
                    }
                }
            }, { onLoginError() })
    }

    override fun onLoginError() {
        loginPresenterImplObj.onLoginError()
    }

    override fun onLoginSuccess(token: String) {
        loginPresenterImplObj.onLoginSuccess(token)
    }
}