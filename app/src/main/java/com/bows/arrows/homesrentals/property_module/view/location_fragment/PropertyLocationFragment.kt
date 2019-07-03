package com.bows.arrows.homesrentals.property_module.view.location_fragment


import android.Manifest
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.bows.arrows.homesrentals.R
import com.bows.arrows.homesrentals.property_module.view.AddPropertyViewModel
import com.bows.arrows.homesrentals.property_module.view.media_fragment.PropertyMediaFragment
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass.
 *
 */
class PropertyLocationFragment : Fragment(), OnMapReadyCallback, View.OnClickListener, GoogleMap.OnCameraIdleListener {

    private lateinit var mMap: GoogleMap
    private lateinit var fragView: View
    private lateinit var viewModel: AddPropertyViewModel
    private lateinit var locationFab: FloatingActionButton

    private val FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION
    private val COARSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION
    private var mLocationPermissionGranted: Boolean = false
    private val PERMISSION_REQUEST_CODE = 1234
    private var locationClient: FusedLocationProviderClient? = null
    private var DEFAULT_ZOOM = 18f
    private var propLatitude: Double = 0.0
    private var propLongitude: Double = 0.0
    private var locationName = ""


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        (activity as AppCompatActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        fragView = inflater.inflate(R.layout.fragment_property_location, container, false)
        getLocationPermissions()
        initializeViews(fragView)
        return fragView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(activity!!).get(AddPropertyViewModel::class.java)
    }

    private fun getLocationPermissions() {
        val permissions = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)
        if (context?.let {
                ContextCompat.checkSelfPermission(
                    it,
                    FINE_LOCATION
                )
            } == PackageManager.PERMISSION_GRANTED) {
            if (context?.let {
                    ContextCompat.checkSelfPermission(
                        it,
                        COARSE_LOCATION
                    )
                } == PackageManager.PERMISSION_GRANTED) {
                mLocationPermissionGranted = true
                initializeMapFragment()
            } else {
                ActivityCompat.requestPermissions(activity!!, permissions, PERMISSION_REQUEST_CODE)
            }
        } else {
            ActivityCompat.requestPermissions(activity!!, permissions, PERMISSION_REQUEST_CODE)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            PERMISSION_REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mLocationPermissionGranted = true
                    //initialize map
                    initializeMapFragment()
                }
            }
        }
    }

    private fun getDeviceLocation() {
        locationClient = LocationServices.getFusedLocationProviderClient(context!!)
        if (mLocationPermissionGranted) {
            try {
                locationClient!!.lastLocation.addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val currentLocation = task.result
                        moveCamera(LatLng(currentLocation!!.latitude, currentLocation.longitude), DEFAULT_ZOOM)
                    } else {
                        Toast.makeText(context, "Location not found", Toast.LENGTH_SHORT).show()
                    }
                }
            } catch (e: SecurityException) {
                Log.e("Location Error:", e.message)
            }
        }
    }

    private fun moveCamera(latLng: LatLng, zoom: Float) {
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom))
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.property_location_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.property_location_next -> {
                if (locationName.isBlank() || locationName.isEmpty()) {
                    Toast.makeText(context, "Please provide a location", Toast.LENGTH_SHORT).show()
                } else {
                    viewModel.addLocation(locationName, LatLng(propLatitude, propLongitude))
                    fragmentManager!!.beginTransaction()
                        .replace(
                            R.id.add_property_activity_fragment_container,
                            PropertyMediaFragment.newInstance(),
                            "PROPERTY_MEDIA_FRAGMENT"
                        )
                        .addToBackStack("PROPERTY_LOCATION_FRAGMENT")
                        .commit()
                }
            }
            android.R.id.home -> {
                activity!!.onBackPressed()
            }
        }
        return false
    }

    private fun initializeMapFragment() {
        val mapFragment =
            childFragmentManager
                .findFragmentById(R.id.add_property_map_fragment) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    private fun initializeViews(fragView: View) {
        locationFab = fragView.findViewById(R.id.get_location_fab)
        locationFab.setOnClickListener(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.setOnCameraIdleListener(this)
        if (mLocationPermissionGranted) {
            getDeviceLocation()
            try {
                mMap.isMyLocationEnabled = true
            } catch (e: SecurityException) {
                Log.e("Location Error:", e.message)
            }
        }
    }

    override fun onCameraIdle() {
        propLatitude = mMap.cameraPosition.target.latitude
        propLongitude = mMap.cameraPosition.target.longitude

        GlobalScope.launch(Dispatchers.IO) {
            geoCoder(propLatitude, propLongitude)
        }
    }

    private fun geoCoder(latitude: Double, longitude: Double) {
        val geoCoder = Geocoder(context)
        val list = geoCoder.getFromLocation(latitude, longitude, 1)
        if (list.isNotEmpty()) {
            val address: Address = list[0]
            locationName = address.getAddressLine(0)
        }
    }

    override fun onClick(v: View?) {
        mMap.clear()
        mMap.addMarker(
            MarkerOptions()
                .position(LatLng(propLatitude, propLongitude))
                .title(locationName)
        )
    }

    companion object {
        fun newInstance(): PropertyLocationFragment {
            return PropertyLocationFragment()
        }
    }
}
