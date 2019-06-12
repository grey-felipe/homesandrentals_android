package com.bows.arrows.homesrentals.utilities

import android.content.Context
import com.bows.arrows.homesrentals.R

class CloudinaryConfig {
    companion object {
        fun getConfig(context: Context): HashMap<String, String> {
            lateinit var configMap: HashMap<String, String>
            configMap["cloud_name"] = context.getString(R.string.CLOUDINARY_CLOUD_NAME)
            return configMap
        }
    }
}