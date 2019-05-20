package com.bows.arrows.homesrentals.authentication.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.bows.arrows.homesrentals.R

class AuthActivity : AppCompatActivity() {

    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        toolbar = findViewById(R.id.auth_toolbar)
        setSupportActionBar(toolbar)

        supportFragmentManager
            .beginTransaction()
            .add(R.id.authFragmentsContainer, LoginFragment.newInstance(), "LOGIN_FRAGMENT")
            .commit()
    }
}
