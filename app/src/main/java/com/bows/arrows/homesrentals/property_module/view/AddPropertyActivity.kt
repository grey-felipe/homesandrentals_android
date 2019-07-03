package com.bows.arrows.homesrentals.property_module.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.bows.arrows.homesrentals.R
import com.bows.arrows.homesrentals.property_module.view.info_fragment.PropertyInfoFragment

class AddPropertyActivity : AppCompatActivity() {
    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_property)
        toolbar = findViewById(R.id.main_frags_toolbar)
        setSupportActionBar(toolbar)

        supportFragmentManager
            .beginTransaction()
            .add(
                R.id.add_property_activity_fragment_container,
                PropertyInfoFragment.newInstance(),
                "PROPERTY_INFO_FRAGMENT"
            )
            .commit()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }
}
