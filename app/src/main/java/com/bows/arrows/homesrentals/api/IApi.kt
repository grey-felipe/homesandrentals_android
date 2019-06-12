package com.bows.arrows.homesrentals.api

import com.bows.arrows.homesrentals.api.response_models.AuthResponse
import com.bows.arrows.homesrentals.api.response_models.PropertyResponse
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface IApi {
    @POST("auth/signup/")
    fun registerUser(@Body body: HashMap<String, HashMap<String, Any>>): Observable<Response<AuthResponse>>

    @POST("auth/login/")
    fun loginUser(@Body body: HashMap<String, HashMap<String, Any>>): Observable<Response<AuthResponse>>

    @POST("property/add/")
    fun addProperty(@Body body: HashMap<String, HashMap<String, Any>>): Observable<Response<PropertyResponse>>
}