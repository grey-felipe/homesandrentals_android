package com.bows.arrows.homesrentals.property_module.model

import android.util.Log
import com.bows.arrows.homesrentals.api.RetrofitClient
import com.bows.arrows.homesrentals.api.response_models.PropertyResponse
import com.bows.arrows.homesrentals.property_module.presenter.AddPropertyPresenterImpl
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

data class Property(
    val title: String,
    val price: Int,
    val currency: String,
    val type: String,
    val available: Boolean,
    val description: String,
    val location: String,
    val coordinates: String,
    val media: List<String>
) : IProperty {
    private lateinit var presenterImpl: AddPropertyPresenterImpl

    fun getPresenterImpl(obj: AddPropertyPresenterImpl) {
        presenterImpl = obj
    }

    override fun pushPropertyDataToBackend(property: Property) {
        val map = HashMap<String, Any>()
        map["title"] = property.title
        map["price"] = property.price
        map["currency"] = property.currency
        map["type"] = property.type
        map["available"] = property.available
        map["description"] = property.description
        map["location"] = property.location
        map["coordinates"] = property.coordinates
        map["media"] = property.media

        val propertyMap = HashMap<String, HashMap<String, Any>>()
        propertyMap["property"] = map

        RetrofitClient.instance
            .addProperty(propertyMap)
            .enqueue(object : Callback<PropertyResponse> {
                override fun onFailure(call: Call<PropertyResponse>, t: Throwable) {
                    Log.e("property_error: ", t.cause.toString())
                }

                override fun onResponse(call: Call<PropertyResponse>, response: Response<PropertyResponse>) {
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
        presenterImpl.onPushError()
    }

    override fun onPushSuccess() {
        presenterImpl.onPushSuccess()
    }
}