package com.bows.arrows.homesrentals.api

import com.bows.arrows.homesrentals.api.response_models.PropertyResponse
import com.bows.arrows.homesrentals.api.response_models.AuthResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface IApi {
    @POST("auth/signup/")
    fun registerUser(@Body body: HashMap<String, HashMap<String, Any>>): Call<AuthResponse>

    @POST("auth/login/")
    fun loginUser(@Body body: HashMap<String, HashMap<String, Any>>): Call<AuthResponse>

    @POST("property/add/")
    fun addProperty(@Body body: HashMap<String, HashMap<String, Any>>): Call<PropertyResponse>
}