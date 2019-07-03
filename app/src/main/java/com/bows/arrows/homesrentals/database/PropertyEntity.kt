package com.bows.arrows.homesrentals.database

import android.net.Uri
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "property_table")
data class PropertyEntity(
    @ColumnInfo(name = "title")
    var title: String,
    @ColumnInfo(name = "price")
    var price: Int,
    @ColumnInfo(name = "currency")
    var currency: String,
    @ColumnInfo(name = "type")
    var type: String,
    @ColumnInfo(name = "available")
    var available: Boolean,
    @ColumnInfo(name = "description")
    var description: String,
    @ColumnInfo(name = "location")
    var location: String,
    @ColumnInfo(name = "coordinates")
    var coordinates: String,
    @ColumnInfo(name = "media")
    var media: List<Any>
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0
}