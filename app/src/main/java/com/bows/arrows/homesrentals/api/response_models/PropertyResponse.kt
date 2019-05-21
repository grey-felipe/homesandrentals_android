package com.bows.arrows.homesrentals.api.response_models

import com.bows.arrows.homesrentals.property_module.model.Property
import com.google.gson.annotations.SerializedName

data class PropertyResponse(@SerializedName("property") val property: Property)