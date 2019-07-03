package com.bows.arrows.homesrentals.property_module.presenter

import android.content.Context
import com.bows.arrows.homesrentals.database.PropertyDao
import com.bows.arrows.homesrentals.database.PropertyDatabase
import com.bows.arrows.homesrentals.database.PropertyEntity
import com.bows.arrows.homesrentals.database.PropertyRepository
import com.bows.arrows.homesrentals.property_module.model.Property
import com.bows.arrows.homesrentals.property_module.view.review_fragment.IPropertyReviewFragment
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AddPropertyPresenterImpl(private val view: IPropertyReviewFragment, private val context: Context) :
    IAddPropertyPresenter {

    private lateinit var property: Property
    private var propertyDao: PropertyDao? = null
    private var repository: PropertyRepository? = null

    init {
        getDatabase()
        initializeRepo()
    }

    private fun getDatabase() {
        propertyDao = PropertyDatabase.getDatabase(context).propertyDao()
    }

    private fun initializeRepo() {
        repository = PropertyRepository(propertyDao!!)
    }

    override fun setPropertyObj(obj: Property) {
        property = obj
    }

    override fun pushPropertyToRoomDB() {
        GlobalScope.launch(IO) {
            repository!!.insert(mapPropertyToEntity())
        }
    }

    private fun mapPropertyToEntity(): PropertyEntity {
        return PropertyEntity(
            title = property.title,
            price = property.price,
            currency = property.currency,
            type = property.type,
            available = property.available,
            description = property.description,
            location = property.location,
            coordinates = property.coordinates,
            media = property.media
        )
    }

    override fun pushMediaToFireBase() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun pushPropertyToBackend() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}