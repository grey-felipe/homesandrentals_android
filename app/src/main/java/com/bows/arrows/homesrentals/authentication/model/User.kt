package com.bows.arrows.homesrentals.authentication.model

import android.util.Log
import com.bows.arrows.homesrentals.api.RetrofitClient
import com.bows.arrows.homesrentals.api.response_models.RequestResponse
import com.bows.arrows.homesrentals.authentication.presenter.LoginPresenterImpl
import com.bows.arrows.homesrentals.authentication.presenter.SignUpPresenterImpl
import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


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

    private var signUpPresenterImplObj: SignUpPresenterImpl? = null
    private var loginPresenterImplObj: LoginPresenterImpl? = null

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

        RetrofitClient.instance
            .registerUser(userMap)
            .enqueue(object : Callback<RequestResponse> {
                override fun onFailure(call: Call<RequestResponse>, t: Throwable) {
                    Log.e("reg_error: ", t.cause.toString())
                }

                override fun onResponse(call: Call<RequestResponse>, response: Response<RequestResponse>) {
                    when (response.code()) {
                        400 -> {
                            onPushError()
                        }
                        201 -> {
                            onPushSuccess()
                        }
                    }
                }
            })
    }

    override fun onPushError() {
        signUpPresenterImplObj!!.onPushError()
    }

    override fun onPushSuccess() {
        signUpPresenterImplObj!!.onPushSuccess()
    }

    //Login user
    override fun loginUser(user: User) {
        val map = HashMap<String, Any>()
        map["email"] = user.email
        map["password"] = user.password

        val userMap = HashMap<String, HashMap<String, Any>>()
        userMap["user"] = map

        RetrofitClient.instance
            .loginUser(userMap)
            .enqueue(object : Callback<RequestResponse> {
                override fun onFailure(call: Call<RequestResponse>, t: Throwable) {
                    Log.e("login_error: ", t.cause.toString())
                }

                override fun onResponse(call: Call<RequestResponse>, response: Response<RequestResponse>) {
                    when (response.code()) {
                        400 -> {
                            onLoginError()
                        }
                        200 -> {
                            val token: String = response.body()!!.user.token
                            onLoginSuccess(token)
                        }
                    }
                }
            })
    }

    override fun onLoginError() {
        loginPresenterImplObj!!.onLoginError()
    }

    override fun onLoginSuccess(token: String) {
        loginPresenterImplObj!!.onLoginSuccess(token)
    }
}