package com.dev.handlusernew.fragments

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.dev.handlusernew.R
import com.dev.handlusernew.databinding.FragmentLocationPickBinding
import com.dev.handlusernew.utils.GPSUtils
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest.PRIORITY_HIGH_ACCURACY
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.tasks.CancellationTokenSource
import com.google.android.gms.tasks.Task
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*


class LocationPickFragment : Fragment(), OnMapReadyCallback {


    private var mFusedLocationClient: FusedLocationProviderClient? = null
    private var mLocation: Location? = null
    private var mGoogleMap: GoogleMap? = null
    private var mGeoCoder: Geocoder? = null
    private lateinit var mBinding: FragmentLocationPickBinding
    private val binding get() = mBinding

    private var locality:String?=null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =
            inflater.inflate(R.layout.fragment_location_pick, container, false)
        val supportActionBar: ActionBar? = (requireActivity() as AppCompatActivity).supportActionBar
        supportActionBar?.hide()
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())
        requestUserLocationForMainScreen()
        val mapFragment =
            childFragmentManager.findFragmentById(R.id.frg) as SupportMapFragment
        mapFragment.getMapAsync(this)
        mBinding = FragmentLocationPickBinding.bind(view)
//        mGeoCoder = Geocoder(context, Locale.ENGLISH) // previously used
        mGeoCoder = Geocoder(context, Locale.getDefault())
        mBinding.shareTV.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_secondFragment_to_loginFragment)
        }
        mBinding.shareTV.isEnabled = false

        return view

    }

    private fun isLocationEnabled(): Boolean {

        val locationManager =
            activity?.getSystemService(Context.LOCATION_SERVICE) as LocationManager

        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        )
    }

    override fun onMapReady(p0: GoogleMap) {
        mGoogleMap = p0
        mGoogleMap?.setMapStyle(
            MapStyleOptions.loadRawResourceStyle(
                requireContext(), R.raw.styled
            )
        )
        if (isLocationEnabled()) {
            getUserLocation()
        } else {
            GPSUtils(requireActivity()).turnOnGPS()
            getUserLocation()
        }

    }

    private fun requestUserLocationForMainScreen() {

        val locationPermissionRequest = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            when {
                permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false) -> {
                    Log.d("location", "FINE granted")
                    getUserLocation()

                }
                permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false) -> {
                    // Only approximate location access granted.

                    getUserLocation()
                    Log.d("location", "COARSE granted")
                }
                else -> {
                    Log.d("location", "NOT granted")
                    // No location access granted.
                }
            }
        }

        locationPermissionRequest.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )


    }


    private fun checkPermissions(): Boolean {
        if (ActivityCompat.checkSelfPermission(
                requireActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireActivity(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return false
        }
        return true

    }

    @SuppressLint("MissingPermission", "SetTextI18n")
    private fun getUserLocation() {

        if (checkPermissions()) {
            val cancellationToken = CancellationTokenSource()


            val currentLocationTask: Task<Location>? = mFusedLocationClient?.getCurrentLocation(
                PRIORITY_HIGH_ACCURACY,
                cancellationToken.token
            )

            currentLocationTask?.addOnCompleteListener { task: Task<Location> ->
                if (task.isSuccessful) {
                    GlobalScope.launch(Dispatchers.IO) {
                        val result: Location = task.result
                        "Location (success): ${result.latitude}, ${result.longitude}"
                        mLocation = result
                        val lc = LatLng(mLocation?.latitude ?: 0.0, mLocation?.longitude ?: 0.0)
                        this@LocationPickFragment.requireActivity().runOnUiThread {
                            mGoogleMap?.clear()
                            mGoogleMap?.addMarker(
                                MarkerOptions()
                                    .position(lc)
                                    .title("Your Location")
                            )
                            mGoogleMap?.moveCamera(CameraUpdateFactory.newLatLngZoom(lc, 5f))
                        }
                        val addressList =
                            mGeoCoder?.getFromLocation(mLocation?.latitude ?: 0.0, mLocation?.longitude ?: 0.0, 1)
                        if (addressList != null && addressList.count() > 0) {
                            val firstAddress = addressList.firstOrNull()
                            locality = firstAddress?.locality ?: ""
                            val country: String = firstAddress?.countryName ?: ""
                            val city: String = firstAddress?.adminArea ?: ""
                            this@LocationPickFragment.requireActivity().runOnUiThread {
                                binding.cityET.setText("$locality,$city")
                                binding.codeET.setText(country)
                                mBinding.shareTV.isEnabled = true

                            }

                        }

                    }
                } else {
                    task.exception?.printStackTrace()
                }
            }
        }
    }
}