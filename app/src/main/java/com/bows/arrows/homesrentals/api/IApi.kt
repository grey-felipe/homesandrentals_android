package com.bows.arrows.homesrentals.api

import com.bows.arrows.homesrentals.api.response_models.RequestResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface IApi {
    @POST("auth/signup/")
    fun registerUser(@Body body: HashMap<String, HashMap<String, Any>>): Call<RequestResponse>

    @POST("auth/login/")
    fun loginUser(@Body body: HashMap<String, HashMap<String, Any>>): Call<RequestResponse>
}