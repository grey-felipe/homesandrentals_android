package com.bows.arrows.homesrentals.api.response_models

import com.bows.arrows.homesrentals.authentication.model.User
import com.google.gson.annotations.SerializedName

data class AuthResponse(
    @SerializedName("user")
    val user: User
)