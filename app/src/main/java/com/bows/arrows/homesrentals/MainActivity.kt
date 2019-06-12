package com.bows.arrows.homesrentals


import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.bows.arrows.homesrentals.home_module.view.HomeFragment
import com.bows.arrows.homesrentals.property_module.view.PropertyFragment
import com.bows.arrows.homesrentals.utilities.Constants
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var toolbar: Toolbar
    private var mAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar = findViewById(R.id.main_frags_toolbar)
        setSupportActionBar(toolbar)

        val sharedPreferences = this.getSharedPreferences(Constants.SHARED_PREFERENCES_FILE, Context.MODE_PRIVATE)
        Constants.AUTH_TOKEN = sharedPreferences.getString(Constants.SHARED_PREFERENCES_TOKEN_KEY, "")

        supportFragmentManager
            .beginTransaction()
            .add(R.id.main_fragments_container, HomeFragment.newInstance(), "HOME_FRAGMENT")
            .commit()

        val mNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item: MenuItem ->

            when (item.itemId) {
                R.id.bottom_nav_home -> {
                    return@OnNavigationItemSelectedListener true
                }
                R.id.bottom_nav_search -> {
                    return@OnNavigationItemSelectedListener true
                }
                R.id.bottom_nav_property -> {
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.main_fragments_container, PropertyFragment.newInstance(), "PROPERTY_FRAGMENT")
                        .commit()
                    return@OnNavigationItemSelectedListener true
                }
                R.id.bottom_nav_favorites -> {
                    return@OnNavigationItemSelectedListener true
                }
                R.id.bottom_nav_profile -> {
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }
        main_bottom_navigation_bar.setOnNavigationItemSelectedListener(mNavigationItemSelectedListener)
    }

    override fun onStart() {
        super.onStart()
        val currentUser = mAuth?.currentUser
        if (currentUser == null) {
            anonymousFireBaseSignIn()
        } else {
            Log.i("current_user", mAuth?.currentUser!!.uid)
        }
    }

    private fun anonymousFireBaseSignIn() {
        mAuth = FirebaseAuth.getInstance()
        mAuth?.signInAnonymously()?.addOnCompleteListener { Log.i("anonymous_user", mAuth?.currentUser!!.uid) }
    }
}
