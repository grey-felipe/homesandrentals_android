package com.bows.arrows.homesrentals.database

import android.net.Uri
import androidx.room.TypeConverter
import com.google.gson.Gson

class ListTypeConverter {
    companion object {
        @TypeConverter
        @JvmStatic
        fun listToJson(list: List<Any>): String {
            return Gson().toJson(list)
        }

        @TypeConverter
        @JvmStatic
        fun jsonToList(string: String): List<Any> {
            val objects = Gson().fromJson(string, Array<Any>::class.java)
            return objects.toList()
        }
    }

}