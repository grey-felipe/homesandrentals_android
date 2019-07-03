package com.bows.arrows.homesrentals.property_module.model

import android.net.Uri
import android.util.Log
import com.bows.arrows.homesrentals.api.RetrofitClient
import com.bows.arrows.homesrentals.property_module.presenter.AddPropertyPresenterImpl
import com.bows.arrows.homesrentals.utilities.Constants
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.UUID
import kotlin.collections.HashMap

data class Property(
    var title: String = "",
    var price: Int = 0,
    var currency: String = "",
    var type: String = "",
    var available: Boolean = true,
    var description: String = "",
    var location: String = "",
    var coordinates: String = "",
    var media: List<Any>
) : IProperty {

//    private lateinit var presenterImpl: AddPropertyPresenterImpl
    private lateinit var storageReference: StorageReference

//    fun getPresenterImpl(obj: AddPropertyPresenterImpl) {
//        presenterImpl = obj
//    }

    private fun createImageName(media: Uri): String {
        val uuid = UUID.randomUUID()
        val newPath = media.path?.toString()!!.replace("/", "_", true)
        return "$uuid$newPath"
    }

    override fun pushMediaToFireBase(list: List<Uri>, index: Int): List<String> {
        val mediaList = mutableListOf<String>()
        GlobalScope.launch(IO) {
            if (index <= list.size - 1) {
                storageReference = FirebaseStorage.getInstance().reference.child(Constants.FIREBASE_STORAGE_FILENAME)
                    .child(createImageName(list[index]))
                val uploadTask = storageReference.putFile(list[index]).addOnCompleteListener { listener ->
                    if (listener.isSuccessful) {
                        storageReference.downloadUrl.addOnSuccessListener { uri ->
                            mediaList.add(uri.toString())
                            Log.i("result", uri.toString())
                        }
                    } else {
                        Log.e("upload_error", listener.exception.toString())
                    }
                }
                Thread.sleep(7000)
                pushMediaToFireBase(list, index + 1)
            }
        }
        return mediaList
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

        val disposable = RetrofitClient.instance
            .addProperty(propertyMap)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                when (it.code()) {
                    400 -> {
                        onPushError("Upload failed property was not saved")
                    }
                    201 -> {
                        onPushSuccess("Upload was successful property added")
                    }
                }
            }, { onPushError("Upload failed property was not saved") })
    }

    override fun onPushError(message: String) {
//        presenterImpl.onPushError(message)
    }

    override fun onPushSuccess(message: String) {
//        presenterImpl.onPushSuccess(message)
    }
}